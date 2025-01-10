package com.teste.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.pedidos.dto.UsuarioDTO;
import com.teste.pedidos.entities.Usuario;
import com.teste.pedidos.mapstruct.UsuarioMapper;
import com.teste.pedidos.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired(required = true)
	private UsuarioMapper mapper;

	public UsuarioDTO guardar(UsuarioDTO dto) throws Exception {		
		Usuario usuario = repository.save(mapper.paraUsuario(dto));
		return mapper.paraUsuarioDTO(usuario);
	}

	public UsuarioDTO obterPorId(Long id) throws Exception {		
		Usuario usuario = repository.findById(id).orElseThrow(() -> new Exception("Usuario não encontrado"));
		return mapper.paraUsuarioDTO(usuario);
	}
	
	public UsuarioDTO obterPorEmailEStatus(String email, boolean status) throws Exception {
		Usuario usuario = repository.obterPorEmailEStatus(email, status).orElseThrow(() -> new Exception("Usuario não encontrado"));
		return mapper.paraUsuarioDTO(usuario);
	}

	public List<UsuarioDTO> obterTodos() throws Exception {		
		List<Usuario> usuario = repository.findAll();		
		//Retorna também os Usuarios com status = false
		return mapper.paraListaUsuarioDTO(usuario);
	}

	public void atualizar(UsuarioDTO dto) throws Exception { 	
		Optional<Usuario> usuarioEntity = repository.findById(dto.getId());
		if (usuarioEntity.isPresent()) {
			Usuario usuario = usuarioEntity.get();
			usuario.setNome(dto.getNome());
			usuario.setEmail(dto.getEmail());
			usuario.setStatus(dto.isStatus());
			repository.save(usuario);
		} else {
			throw new Exception("Usuario não encontrado");
		}
	}

	public void excluir(Long id) throws Exception { 
		Optional<Usuario> usuarioEntity = repository.findById(id);
		if (usuarioEntity.isPresent()) {
			Usuario entity = usuarioEntity.get();
			entity.setStatus(false);
			repository.save(entity);            
		} else {
			throw new Exception("Usuario não encontrado");
		}
	}
}
