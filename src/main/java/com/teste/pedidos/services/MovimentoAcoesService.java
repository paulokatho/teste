package com.teste.pedidos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pedidos.repositories.MovimentoAcoesRepository;

@Service
public class MovimentoAcoesService {
	
	@Autowired
	private MovimentoAcoesRepository repository;

}
