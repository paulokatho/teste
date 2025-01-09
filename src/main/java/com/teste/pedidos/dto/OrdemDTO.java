package com.teste.pedidos.dto;

import java.time.LocalDateTime;

import com.teste.pedidos.entities.Artigo;
import com.teste.pedidos.entities.Usuario;

public class OrdemDTO {

	private Long id;
    private LocalDateTime dataCriacao;
    private int quantidade;
    private boolean status;
    private Artigo artigo;
    private Usuario usuario;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Artigo getArtigo() {
		return artigo;
	}
	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
     
}
