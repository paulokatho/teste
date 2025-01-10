package com.teste.pedidos.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.pedidos.entities.MovimentoAcoes;

public interface MovimentoAcoesRepository extends JpaRepository<MovimentoAcoes, Long>{

	List<MovimentoAcoes> findByStatus(boolean status);
	
	Optional<MovimentoAcoes> findByTransacaoId(String transacaoId);
}
