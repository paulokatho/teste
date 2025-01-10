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

import com.teste.pedidos.dto.ArtigoDTO;
import com.teste.pedidos.services.ArtigoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/artigo")
@Tag(name = "Artigo", description = "Controlador para gerenciar dados do artigo")
public class ArtigoController {

	@Autowired
	private ArtigoService service;
	
	private static final Logger logger = LoggerFactory.getLogger(ArtigoController.class);
	
    @PostMapping
    @Operation(summary = "Salva dados do artigo", description = "Método para salvar dados do artigo")
    @ApiResponse(responseCode = "201", description = "Artigo gravado com sucesso")
    @ApiResponse(responseCode = "400", description = "Artigo já está gravado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<ArtigoDTO> create(@RequestBody ArtigoDTO dto) {
            	
    	if(dto == null || dto.getId() != null) {
    		logger.error("Erro na requisição para guardar artigo.");
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	
    	try {
    		ArtigoDTO responseDTO = service.guardar(dto);
    		return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);			
		} catch (Exception e) {
			logger.error("Erro na requisição para guardar artigo.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}        
    }
    
    @GetMapping("{id}")
    @Operation(summary = "Obtém dados do artigo", description = "Método para obter dados do artigo")
    @ApiResponse(responseCode = "200", description = "Artigo encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Artigo não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
	public ResponseEntity<ArtigoDTO> obterPorId(@PathVariable Long id) {		
		
    	if(id == null) {
    		logger.error("Erro na requisição para obter artigo por id.");
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	
    	try {
    		ArtigoDTO responseDTO = service.obterPorId(id);
    		return new ResponseEntity<>(responseDTO, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Erro. Artigo não encontrado.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}    
	}
	
    @GetMapping
    @Operation(summary = "Obtém todos os artigos", description = "Método para todos os artigos")
    @ApiResponse(responseCode = "200", description = "Artigo encontrado com sucesso")
    @ApiResponse(responseCode = "404", description = "Artigo não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
	public ResponseEntity<List<ArtigoDTO>> obterTodos() throws Exception {		
		
    	try {
    		List<ArtigoDTO> responseDTO = service.obterTodos();
    		return new ResponseEntity<>(responseDTO, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Erro na requisição para obter todos os artigos.");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}   
	}
	
    @PutMapping("{id}")
    @Operation(summary = "Atualiza dados do artigo", description = "Método para atualizar dados do artigo")
    @ApiResponse(responseCode = "200", description = "Artigo atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro na requisição")
    @ApiResponse(responseCode = "404", description = "Artigo não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<ArtigoDTO> atualizar(@PathVariable Long id,
    											@RequestBody ArtigoDTO dto){
        
    	if(dto == null) {
    		logger.error("Erro na requisição para guardar artigo.");
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	
    	try {
    		service.atualizar(dto);
    		ArtigoDTO responseDTO = service.obterPorId(id);
    		return new ResponseEntity<>(responseDTO, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Erro ao atualizar artigo. Artigo não encontrado.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
    }
    
    @DeleteMapping("{id}")
    @Operation(summary = "Atualiza dados do artigo", description = "Método para atualizar dados do artigo")
    @ApiResponse(responseCode = "200", description = "Artigo excluído com sucesso")
    @ApiResponse(responseCode = "400", description = "Erro na requisição")
    @ApiResponse(responseCode = "404", description = "Artigo não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<ArtigoDTO> excluir(@PathVariable Long id) {
        
    	if(id == null) {
    		logger.error("Erro na requisição para excluir artigo.");
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    	}
    	
    	try {
    		//Exclui logicamente
    		ArtigoDTO dto= service.excluir(id);    		
    		return new ResponseEntity<>(dto, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Erro para excluir logicamente artigo. Artigo não encontrado.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
    }
    
    
}
