package com.teste.pedidos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.pedidos.entities.Artigo;

public interface ArtigoRepository extends JpaRepository<Artigo, Long> {
	
	Optional<Artigo> findByNome(String nome);

	Optional<Artigo> findById(Long id);

}
