package com.teste.pedidos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pedidos.entities.Artigo;
import com.teste.pedidos.repositories.ArtigoRepository;

@Service
public class ArtigoService {
	
	@Autowired
	private ArtigoRepository repository;
	
	public void saveArtigo(Artigo artigo) {
		repository.save(artigo);
	}
	
	public Artigo obterArtigo(Long id) throws Exception {		
		return repository.findById(id).orElseThrow(() -> new Exception("Artigo não encontrado"));
	}

}
