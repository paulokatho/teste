package com.teste.pedidos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pedidos.repositories.OrdemRepository;

@Service
public class OrdemService {
	
	@Autowired
	private OrdemRepository repository;

}
