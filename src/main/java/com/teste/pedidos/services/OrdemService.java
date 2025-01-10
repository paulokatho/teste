package com.teste.pedidos.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pedidos.dto.ArtigoDTO;
import com.teste.pedidos.dto.OrdemDTO;
import com.teste.pedidos.entities.MovimentoAcoes;
import com.teste.pedidos.entities.Ordem;
import com.teste.pedidos.enums.SituacaoPedidoEnum;
import com.teste.pedidos.mapstruct.ArtigoMapper;
import com.teste.pedidos.mapstruct.MovimentoAcoesMapper;
import com.teste.pedidos.mapstruct.OrdemMapper;
import com.teste.pedidos.repositories.OrdemRepository;

@Service
public class OrdemService {
	
	@Autowired
	private OrdemRepository repository;
	
	@Autowired
	private MovimentoAcoesService movimentoAcoesService;
	
	@Autowired
	private ArtigoService artigoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EmailService emailService;

	@Autowired(required = true)
	private OrdemMapper mapper;
	
	@Autowired(required = true)
	private MovimentoAcoesMapper maMapper;
	
	@Autowired
	private ArtigoMapper artigoMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(OrdemService.class);

	public OrdemDTO guardar(OrdemDTO dto) throws Exception {
		
		ArtigoDTO artigoDTO =  artigoService.obterPorId(dto.getArtigo().getId());			
		if(artigoDTO.getQuantidade() < dto.getQuantidade()) {
			logger.error("Erro ao guardar Pedido. Não temos a quantidade necessária para esse pedido.");
			throw new Exception("Não temos a quantidade necessária para esse pedido. Disponível: " + dto.getQuantidade());
		}
		dto.setSituacaoPedido(SituacaoPedidoEnum.PROCESSANDO.toString());
		dto.setDataCriacao(LocalDateTime.now());
		dto.setStatus(true);
		Ordem ordem = repository.save(mapper.paraOrdem(dto));
		
		logger.info("Pedido gravado com sucesso.");
		
		MovimentoAcoes ma = new MovimentoAcoes();
		ma.setArtigo(artigoMapper.paraArtigo(dto.getArtigo()));
		ma.setDataCriacao(ordem.getDataCriacao());
		ma.setQuantidade(dto.getQuantidade());
		ma.setStatus(true);
		ma.setTransacaoId(ordem.getTransacaoId());
		
		movimentoAcoesService.guardar(maMapper.paraMovimentoAcoesDTO(ma));
		
		int novaQuantidade = artigoDTO.getQuantidade()- ordem.getQuantidade();
		artigoDTO.setQuantidade(novaQuantidade);
		artigoDTO = artigoService.atualizarQuantidade(artigoDTO);
		
		OrdemDTO ordemDTO = mapper.paraOrdemDTO(ordem); 
		ordemDTO.setArtigo(artigoDTO);
		
		ordemDTO.setUsuario(usuarioService.obterPorId(dto.getUsuario().getId()));

		//Seta pedido como concluído
		ordem.setSituacaoPedido(SituacaoPedidoEnum.CONCLUÍDO.toString());
		repository.save(ordem);
		
		//Prepara e envia email
		emailService.sendEmailPedido(ordemDTO);
		
		logger.info("Pedido concoluído.");
		
		return ordemDTO; 
	}

	public OrdemDTO obterPorId(Long id) throws Exception {		
		Ordem ordem = repository.findById(id).orElseThrow(() -> new Exception("Ordem não encontrada"));
		return mapper.paraOrdemDTO(ordem);
	}
	
	public List<OrdemDTO> obterPorStatus(boolean status) throws Exception {
		List<Ordem> ma = repository.findByStatus(status);
		return mapper.paraListaOrdemDTO(ma);
	}

	public List<OrdemDTO> obterTodos() throws Exception {		
		List<Ordem> ordem = repository.findAll();		
		//Retorna também os Ordens com status = false
		return mapper.paraListaOrdemDTO(ordem);
	}

	public void atualizar(OrdemDTO dto) throws Exception { 	
		Optional<Ordem> ordemEntity = repository.findById(dto.getId());
		if (ordemEntity.isPresent()) {
			//Atualiza o status para false e retorna a quantidade para o artigo
			Ordem ordem = ordemEntity.get();
			ordem.setStatus(false);
			ordem.setSituacaoPedido(SituacaoPedidoEnum.PENDENTE.toString());
			
			repository.save(ordem);
			
			logger.info("Pedido atualizado com sucesso.");
			
			ArtigoDTO artigoDTO = artigoService.obterPorId(dto.getArtigo().getId());
			int novaQuantidade = ordem.getQuantidade() + artigoDTO.getQuantidade();
			artigoDTO.setQuantidade(novaQuantidade);
			
			movimentoAcoesService.atualizarStatus(dto.getTransacaoId());
			
			artigoService.atualizarQuantidade(dto.getArtigo());			
		} else {
			throw new Exception("Ordem não encontrada");
		}
	}

	public void excluir(Long id) throws Exception { 
		Optional<Ordem> ordemEntity = repository.findById(id);
		if (ordemEntity.isPresent()) {
			Ordem entity = ordemEntity.get();
			
			movimentoAcoesService.excluir(id);
			
			entity.setStatus(false);
			entity.setSituacaoPedido(SituacaoPedidoEnum.CANCELADO.toString());
			
			repository.save(entity);
			logger.info("Ordem excluída logicamente com sucesso.");
		} else {
			throw new Exception("Ordem não encontrada");
		}
	}
	
}
