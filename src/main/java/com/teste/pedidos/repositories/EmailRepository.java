package com.teste.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.pedidos.entities.Email;

public interface EmailRepository extends JpaRepository<Email, Long>{

	
}
