package com.teste.pedidos.services;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.teste.pedidos.dto.OrdemDTO;
import com.teste.pedidos.entities.Email;
import com.teste.pedidos.enums.EmailUtilsEnum;
import com.teste.pedidos.enums.StatusEmailEnum;
import com.teste.pedidos.repositories.EmailRepository;

import jakarta.transaction.Transactional;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository repository;
	
	@Autowired
	private JavaMailSender emailSender;
	
	private static final Logger logger = LoggerFactory.getLogger(EmailService.class);
	
	@SuppressWarnings("finally")
	@Transactional
    public Email sendEmail(Email emailModel) {		
		emailModel.setSendDateEmail(LocalDateTime.now());
		
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
 
            emailSender.send(message);            
            logger.info("E-mail enviado com sucesso.");

            emailModel.setStatusEmail(StatusEmailEnum.SENT);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmailEnum.ERROR);
            logger.error("Erro ao enviar e-mail. Verifique o status ERROR na base de dados.");
        } finally {
        	logger.info("E-mail gravado com sucesso.");
        	return repository.save(emailModel);
		}
    }
	
	@SuppressWarnings("finally")
	@Transactional
    public Email sendEmailPedido(OrdemDTO dto) {
		Email emailModel = new Email(); 
		emailModel.setSendDateEmail(LocalDateTime.now());
		
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            emailModel = prepararEmail(dto);
            message.setFrom(emailModel.getEmailFrom());
            message.setTo(emailModel.getEmailTo());
            message.setSubject(emailModel.getSubject());
            message.setText(emailModel.getText());
 
            emailSender.send(message);            
            logger.info("E-mail enviado com sucesso.");

            emailModel.setStatusEmail(StatusEmailEnum.SENT);
        } catch (MailException e){
            emailModel.setStatusEmail(StatusEmailEnum.ERROR);
            logger.error("Erro ao enviar e-mail. Verifique o status ERROR na base de dados.");
        } finally {
        	logger.info("E-mail gravado com sucesso.");
        	return repository.save(emailModel);
		}
    }
	
	public Email prepararEmail(OrdemDTO dto) {
		Email email = new Email();
		
		email.setOwnerRef(EmailUtilsEnum.OWNERREF.getValue());
		email.setEmailFrom(EmailUtilsEnum.EMAILFROM.getValue());
		email.setEmailTo(dto.getUsuario().getEmail());
		email.setSubject(EmailUtilsEnum.SUBJECT.getValue());
		email.setText(EmailUtilsEnum.TEXT.getValue());
		
		return email;
	}
}
