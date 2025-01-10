package com.teste.pedidos.dto;

import java.time.LocalDateTime;

import com.teste.pedidos.enums.StatusEmailEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public class EmailDTO {

    private Long emailId;
    @NotNull
    private String ownerRef;
    @Email
    @NotNull
    private String emailFrom;
    @NotNull
    private String emailTo;
    @NotNull
    private String subject;
    @NotNull
    private String text;
    @NotNull
    private LocalDateTime sendDateEmail;
  
    private StatusEmailEnum statusEmail;
    
	public Long getEmailId() {
		return emailId;
	}
	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}
	public String getOwnerRef() {
		return ownerRef;
	}
	public void setOwnerRef(String ownerRef) {
		this.ownerRef = ownerRef;
	}
	public String getEmailFrom() {
		return emailFrom;
	}
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}
	public String getEmailTo() {
		return emailTo;
	}
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public LocalDateTime getSendDateEmail() {
		return sendDateEmail;
	}
	public void setSendDateEmail(LocalDateTime sendDateEmail) {
		this.sendDateEmail = sendDateEmail;
	}
	public StatusEmailEnum getStatusEmail() {
		return statusEmail;
	}
	public void setStatusEmail(StatusEmailEnum statusEmail) {
		this.statusEmail = statusEmail;
	}
    
}
