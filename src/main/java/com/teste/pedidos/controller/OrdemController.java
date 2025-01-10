package com.teste.pedidos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.pedidos.dto.OrdemDTO;
import com.teste.pedidos.services.OrdemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/ordem")
@Tag(name = "Ordem", description = "Controlador para gerenciar dados do pedido")
public class OrdemController {

	@Autowired
	private OrdemService service;
	
	@PostMapping
	@Operation(summary = "Salva dados do pedido", description = "Método para salvar dados do pedido")
	@ApiResponse(responseCode = "201", description = "Ordem gravado com sucesso")
	@ApiResponse(responseCode = "400", description = "Ordem já está gravado")
	@ApiResponse(responseCode = "500", description = "Erro no servidor")
	public ResponseEntity<OrdemDTO> create(@RequestBody OrdemDTO dto) {
				
		System.out.println("Entrou no controller: ");
		if(dto == null || dto.getId() != null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		try {
			OrdemDTO responseDTO = service.guardar(dto);
			return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}        
	}

	@GetMapping("{id}")
	@Operation(summary = "Obtém dados do pedido", description = "Método para obter dados do pedido")
	@ApiResponse(responseCode = "200", description = "Ordem encontrado com sucesso")
	@ApiResponse(responseCode = "404", description = "Ordem não encontrado")
	@ApiResponse(responseCode = "500", description = "Erro no servidor")
	public ResponseEntity<OrdemDTO> obterPorId(@PathVariable Long id) {		
		
		if(id == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		try {
			OrdemDTO responseDTO = service.obterPorId(id);
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}    
	}

	@GetMapping("/status/{status}")
	@Operation(summary = "Obtém dados do pedido por status", description = "Método para obter dados do pedido por email e status")
	@ApiResponse(responseCode = "200", description = "Ordem encontrado com sucesso")
	@ApiResponse(responseCode = "404", description = "Ordem não encontrado")
	@ApiResponse(responseCode = "500", description = "Erro no servidor")
	public ResponseEntity<List<OrdemDTO>> obterPorStatus(@PathVariable boolean status) {		
		
		try {
			List<OrdemDTO> responseDTO = service.obterPorStatus(status);
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}    
	}

	@GetMapping
	@Operation(summary = "Obtém todos os pedidos", description = "Método para todos os pedidos")
	@ApiResponse(responseCode = "200", description = "Ordem encontrado com sucesso")
	@ApiResponse(responseCode = "404", description = "Ordem não encontrado")
	@ApiResponse(responseCode = "500", description = "Erro no servidor")
	public ResponseEntity<List<OrdemDTO>> obterTodos() throws Exception {		
		
		try {
			List<OrdemDTO> responseDTO = service.obterTodos();
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}   
	}

	@PutMapping("{id}")
	@Operation(summary = "Atualiza status do pedido para false", description = "Método para atualizar dados do pedido para false")
	@ApiResponse(responseCode = "200", description = "Ordem atualizado com sucesso")
	@ApiResponse(responseCode = "400", description = "Erro na requisição")
	@ApiResponse(responseCode = "404", description = "Ordem não encontrado")
	@ApiResponse(responseCode = "500", description = "Erro no servidor")
	public ResponseEntity<OrdemDTO> atualizar(@PathVariable Long id,
												@RequestBody OrdemDTO dto){
		
		if(dto == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		try {
			//Atualiza somente o status = false
			service.atualizar(dto);
			OrdemDTO responseDTO = service.obterPorId(id);
			return new ResponseEntity<>(responseDTO, HttpStatus.OK);	
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
}
