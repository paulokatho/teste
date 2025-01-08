package com.teste.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.pedidos.entities.Ordem;

public interface OrdemRepository extends JpaRepository<Ordem, Long>{

}
