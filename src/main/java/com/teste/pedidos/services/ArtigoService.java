package com.teste.pedidos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pedidos.repositories.ArtigoRepository;

@Service
public class ArtigoService {
	
	@Autowired
	private ArtigoRepository repository;

}
