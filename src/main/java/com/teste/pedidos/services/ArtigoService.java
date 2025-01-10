package com.teste.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pedidos.dto.ArtigoDTO;
import com.teste.pedidos.entities.Artigo;
import com.teste.pedidos.mapstruct.ArtigoMapper;
import com.teste.pedidos.repositories.ArtigoRepository;

@Service
public class ArtigoService {
	
	@Autowired
	private ArtigoRepository repository;
	
	@Autowired(required = true)
	private ArtigoMapper mapper;
	
	private static final Logger logger = LoggerFactory.getLogger(ArtigoService.class);
	
	public ArtigoDTO guardar(ArtigoDTO dto) throws Exception {		
		Artigo artigo = repository.save(mapper.paraArtigo(dto));
		
		logger.info("Artigo gravado com sucesso.");
		
		return mapper.paraArtigoDTO(artigo);
	}
	
	public ArtigoDTO obterPorId(Long id) throws Exception {		
		Artigo artigo = repository.findById(id).orElseThrow(() -> new Exception("Artigo não encontrado"));
		return mapper.paraArtigoDTO(artigo);
	}
	
	public List<ArtigoDTO> obterTodos() throws Exception {		
		List<Artigo> artigo = repository.findAll();		
		//Retorna também os Artigos com status = false
		return mapper.paraListaArtigoDTO(artigo);
	}
	
    public void atualizar(ArtigoDTO dto) throws Exception { 	
        Optional<Artigo> artigoEntity = repository.findById(dto.getId());
        if (artigoEntity.isPresent()) {
            Artigo artigo = artigoEntity.get();
            artigo.setNome(dto.getNome());
            artigo.setStatus(dto.isStatus());
            artigo.setQuantidade(dto.getQuantidade());
            
            repository.save(artigo);
            
            logger.info("Artigo atualizado com sucesso.");
        } else {
        	logger.error("Artigo não encontrado.");
        	throw new Exception("Artigo não encontrado");
        }
    }
    
    public ArtigoDTO atualizarQuantidade(ArtigoDTO dto) throws Exception { 	
        Optional<Artigo> artigoEntity = repository.findById(dto.getId());
        if (artigoEntity.isPresent()) {
            Artigo artigo = artigoEntity.get();
            artigo.setQuantidade(dto.getQuantidade());
            
            ArtigoDTO dtoResponse = mapper.paraArtigoDTO(repository.save(artigo)); 
            
            logger.info("Quantidade do artigo atualizado com sucesso.");
            
            return dtoResponse;
        } else {
        	logger.error("Artigo não encontrado.");
        	throw new Exception("Artigo não encontrado");
        }
    }

    public ArtigoDTO excluir(Long id) throws Exception { 
        Optional<Artigo> artigoEntity = repository.findById(id);
        Artigo entity = artigoEntity.get();
        if (artigoEntity.isPresent()) {
        	entity.setStatus(false);
        	
        	entity = repository.save(entity);
        	logger.info("Artigo excluído logicamente com sucesso.");
        	
        	return mapper.paraArtigoDTO(entity);
        } else {
        	logger.error("Artigo não encontrado.");
        	throw new Exception("Artigo não encontrado");
        }
    }

}
