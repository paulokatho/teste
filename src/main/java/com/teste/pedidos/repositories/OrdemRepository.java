package com.teste.pedidos.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.pedidos.entities.Ordem;

public interface OrdemRepository extends JpaRepository<Ordem, Long>{

	List<Ordem> findByStatus(boolean status);
}
