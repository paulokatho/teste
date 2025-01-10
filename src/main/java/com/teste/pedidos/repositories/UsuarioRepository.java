package com.teste.pedidos.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.teste.pedidos.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	@Query(value = " SELECT u FROM Usuario u WHERE u.email LIKE CONCAT('%', :email, '%') AND u.status = :status")
	Optional<Usuario> obterPorEmailEStatus(@Param("email") String email, @Param("status") boolean status);

	//OBSERVAÇÃO
	//Podemos utilizar ummétodo derivado do Spring Data JPA, mas utilizei jpql para fins didáticos
	//Optional<Usuario> findByEmailContainingAndStatus(String email, boolean status);
}
