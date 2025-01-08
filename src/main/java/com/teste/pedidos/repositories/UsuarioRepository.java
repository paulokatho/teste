package com.teste.pedidos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teste.pedidos.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
