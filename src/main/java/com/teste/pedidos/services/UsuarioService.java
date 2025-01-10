package com.teste.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);
	
	public UsuarioDTO guardar(UsuarioDTO dto) throws Exception {		
		Usuario usuario = repository.save(mapper.paraUsuario(dto));
		
		logger.info("Usuário gravado com sucesso.");
		
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
			
			logger.info("Usuário atualizado com sucesso.");
		} else {
			logger.error("Usuário não encontrado.");
			throw new Exception("Usuario não encontrado");
		}
	}

	public UsuarioDTO excluir(Long id) throws Exception { 
		Optional<Usuario> usuarioEntity = repository.findById(id);
		if (usuarioEntity.isPresent()) {
			Usuario entity = usuarioEntity.get();
			entity.setStatus(false);
			
			logger.info("Usuário excluído com sucesso.");
			
			return mapper.paraUsuarioDTO(repository.save(entity));
		} else {
			logger.error("Usuário não encontrado.");
			throw new Exception("Usuario não encontrado");
		}
	}
}
