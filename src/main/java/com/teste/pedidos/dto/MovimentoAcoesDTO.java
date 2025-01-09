package com.teste.pedidos.dto;

import java.time.LocalDateTime;

import com.teste.pedidos.entities.Artigo;

public class MovimentoAcoesDTO {

	private Long id;
    private LocalDateTime dataCriacao;
    private int quantidade;
    private Artigo artigo;
    
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
	public Artigo getArtigo() {
		return artigo;
	}
	public void setArtigo(Artigo artigo) {
		this.artigo = artigo;
	}
    
}
