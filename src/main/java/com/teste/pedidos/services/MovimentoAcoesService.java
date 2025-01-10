package com.teste.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pedidos.dto.MovimentoAcoesDTO;
import com.teste.pedidos.entities.MovimentoAcoes;
import com.teste.pedidos.mapstruct.MovimentoAcoesMapper;
import com.teste.pedidos.repositories.MovimentoAcoesRepository;

@Service
public class MovimentoAcoesService {
	
	@Autowired
	private MovimentoAcoesRepository repository;

	@Autowired(required = true)
	private MovimentoAcoesMapper mapper;

	public MovimentoAcoesDTO guardar(MovimentoAcoesDTO dto) throws Exception {		
		MovimentoAcoes movimentoAcoes = repository.save(mapper.paraMovimentoAcoes(dto));
		return mapper.paraMovimentoAcoesDTO(movimentoAcoes);
	}

	public MovimentoAcoesDTO obterPorId(Long id) throws Exception {		
		MovimentoAcoes movimentoAcoes = repository.findById(id).orElseThrow(() -> new Exception("Movimento Ações não encontrado"));
		return mapper.paraMovimentoAcoesDTO(movimentoAcoes);
	}
	
	public List<MovimentoAcoesDTO> obterPorStatus(boolean status) throws Exception {
		List<MovimentoAcoes> ma = repository.findByStatus(status);
		return mapper.paraListaMovimentoAcoesDTO(ma);
	}

	public List<MovimentoAcoesDTO> obterTodos() throws Exception {		
		List<MovimentoAcoes> movimentoAcoes = repository.findAll();		
		//Retorna também os MovimentoAcoes com status = false
		return mapper.paraListaMovimentoAcoesDTO(movimentoAcoes);
	}

	public void atualizar(MovimentoAcoesDTO dto) throws Exception { 	
		Optional<MovimentoAcoes> movimentoAcoesEntity = repository.findById(dto.getId());
		if (movimentoAcoesEntity.isPresent()) {
			MovimentoAcoes movimentoAcoes = movimentoAcoesEntity.get();
			movimentoAcoes.setQuantidade(dto.getQuantidade());
			repository.save(movimentoAcoes);
		} else {
			throw new Exception("Movimento Ações encontrado");
		}
	}
	
	public void atualizarStatus(String transacaoId) throws Exception { 	
		MovimentoAcoes movimentoAcoesEntity = repository.findByTransacaoId(transacaoId).orElseThrow(() -> new Exception("Movimento Ações não encontrado"));
		movimentoAcoesEntity.setStatus(false);
		
		repository.save(movimentoAcoesEntity);
	}

	public void excluir(Long id) throws Exception { 
		Optional<MovimentoAcoes> movimentoAcoesEntity = repository.findById(id);
		if (movimentoAcoesEntity.isPresent()) {
			MovimentoAcoes entity = movimentoAcoesEntity.get();
			entity.setStatus(false);
			repository.save(entity);            
		} else {
			throw new Exception("Movimento Ações encontrado");
		}
	}
}
