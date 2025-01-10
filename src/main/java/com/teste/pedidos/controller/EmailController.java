package com.teste.pedidos.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.teste.pedidos.dto.EmailDTO;
import com.teste.pedidos.entities.Email;
import com.teste.pedidos.services.EmailService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/email")
@Tag(name = "E-mail", description = "Controlador para gerenciar dados de e-mail")
public class EmailController {

	@Autowired
    EmailService emailService;
	
	@Operation(summary = "Envia emails", description = "Método para enviar email e gravar na base de dados")
	@ApiResponse(responseCode = "201", description = "Usuario gravado com sucesso")
	@ApiResponse(responseCode = "500", description = "Erro no servidor")
    @PostMapping("/sending-email")
    public ResponseEntity<Email> sendingEmail(@RequestBody @Valid EmailDTO emailDto) {
        Email emailModel = new Email();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }
}
