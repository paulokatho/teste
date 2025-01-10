package com.teste.pedidos.services;

import java.util.List;
import java.util.Optional;

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
	
	public ArtigoDTO guardar(ArtigoDTO dto) throws Exception {		
		Artigo artigo = repository.save(mapper.paraArtigo(dto));
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
        } else {
        	throw new Exception("Artigo não encontrado");
        }
    }
    
    public ArtigoDTO atualizarQuantidade(ArtigoDTO dto) throws Exception { 	
        Optional<Artigo> artigoEntity = repository.findById(dto.getId());
        if (artigoEntity.isPresent()) {
            Artigo artigo = artigoEntity.get();
            artigo.setQuantidade(dto.getQuantidade());
            
            ArtigoDTO dtoResponse = mapper.paraArtigoDTO(repository.save(artigo)); 
            
            return dtoResponse;
        } else {
        	throw new Exception("Artigo não encontrado");
        }
    }

    public void excluir(Long id) throws Exception { 
        Optional<Artigo> artigoEntity = repository.findById(id);
        if (artigoEntity.isPresent()) {
        	Artigo entity = artigoEntity.get();
        	entity.setStatus(false);
            repository.save(entity);            
        } else {
        	throw new Exception("Artigo não encontrado");
        }
    }

}
