package com.teste.pedidos.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.pedidos.dto.UsuarioDTO;
import com.teste.pedidos.services.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/usuario")
@Tag(name = "Usuário", description = "Controlador para gerenciar dados do usuário")
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@PostMapping
	@Operation(summary = "Salva dados do usuario", description = "Método para salvar dados do usuario")
	@ApiResponse(responseCode = "201", description = "Usuario gravado com sucesso")
	@ApiResponse(responseCode = "400", description = "Usuario já está gravado")
	@ApiResponse(responseCode = "500", description = "Erro no servidor")
	public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO dto) {
				
		if(dto == null || dto.getId() != null) {
			logger.error("Erro na requisição para guardar usuário.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		try {
			UsuarioDTO responseDTO = service.guardar(dto);
			return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);			
		} catch (Exception e) {
			logger.error("Erro na requisição para guardar usuário.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}        
	}

	@GetMapping("{id}")
	@Operation(summary = "Obtém dados do usuario", description = "Método para obter dados do usuario")
	@ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso")
	@ApiResponse(responseCode = "404", description = "Usuario não encontrado")
	@ApiResponse(responseCode = "500", description = "Erro no servidor")
	public ResponseEntity<UsuarioDTO> obterPorId(@PathVariable Long id) {		
		
		if(id == null) {
			logger.error("Erro na requisição para guardar usuário.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		try {
			UsuarioDTO responseDTO = service.obterPorId(id);
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Erro na requisição para obter usuário por id.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}    
	}

	@GetMapping("{email}/{status}")
	@Operation(summary = "Obtém dados do usuario por email e status", description = "Método para obter dados do usuario por email e status")
	@ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso")
	@ApiResponse(responseCode = "404", description = "Usuario não encontrado")
	@ApiResponse(responseCode = "500", description = "Erro no servidor")
	public ResponseEntity<UsuarioDTO> obterPorEmailEStatus(@PathVariable String email, @PathVariable boolean status) {		
		
		if(email == null) {
			logger.error("Erro na requisição para obter usuário por id.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		try {
			UsuarioDTO responseDTO = service.obterPorEmailEStatus(email, status);
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Erro na requisição para obter usuário por stauts e email. Usuário não encontrado");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}    
	}
	
	@GetMapping
	@Operation(summary = "Obtém todos os usuarios", description = "Método para todos os usuarios")
	@ApiResponse(responseCode = "200", description = "Usuario encontrado com sucesso")
	@ApiResponse(responseCode = "404", description = "Usuario não encontrado")
	@ApiResponse(responseCode = "500", description = "Erro no servidor")
	public ResponseEntity<List<UsuarioDTO>> obterTodos() throws Exception {		
		
		try {
			List<UsuarioDTO> responseDTO = service.obterTodos();
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Erro na requisição para obter todos os usuários.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}   
	}

	@PutMapping("{id}")
	@Operation(summary = "Atualiza dados do usuario", description = "Método para atualizar dados do usuario")
	@ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
	@ApiResponse(responseCode = "400", description = "Erro na requisição")
	@ApiResponse(responseCode = "404", description = "Usuario não encontrado")
	@ApiResponse(responseCode = "500", description = "Erro no servidor")
	public ResponseEntity<UsuarioDTO> atualizar(@PathVariable Long id,
												@RequestBody UsuarioDTO dto){
		
		if(dto == null) {
			logger.error("Erro na requisição para atualizar usuário.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		try {
			service.atualizar(dto);
			UsuarioDTO responseDTO = service.obterPorId(id);
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Erro na requisição para atualizar usuário. Usuário não encontrado.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping("{id}")
	@Operation(summary = "Atualiza dados do usuario", description = "Método para atualizar dados do usuario")
	@ApiResponse(responseCode = "200", description = "Usuario excluído com sucesso")
	@ApiResponse(responseCode = "400", description = "Erro na requisição")
	@ApiResponse(responseCode = "404", description = "Usuario não encontrado")
	@ApiResponse(responseCode = "500", description = "Erro no servidor")
	public ResponseEntity<UsuarioDTO> excluir(@PathVariable Long id) {
		
		if(id == null) {
			logger.error("Erro na requisição para guardar usuário.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		
		try {
			//Exclui logicamente
			UsuarioDTO dto = service.excluir(id);    		
			return new ResponseEntity<>(dto, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Erro na requisição para excluir usuário logicamente. Usuário não encontrado.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
}
