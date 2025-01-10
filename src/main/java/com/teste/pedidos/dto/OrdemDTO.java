package com.teste.pedidos.dto;

import java.time.LocalDateTime;

public class OrdemDTO {

	private Long id;
    private LocalDateTime dataCriacao;
    private int quantidade;
    private boolean status;
    private String transacaoId;
    private String situacaoPedido;
    private ArtigoDTO artigo;
    private UsuarioDTO usuario;
	
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
	public String getTransacaoId() {
		return transacaoId;
	}
	public void setTransacaoId(String transacaoId) {
		this.transacaoId = transacaoId;
	}
	public String getSituacaoPedido() {
		return situacaoPedido;
	}
	public void setSituacaoPedido(String situacaoPedido) {
		this.situacaoPedido = situacaoPedido;
	}
	public ArtigoDTO getArtigo() {
		return artigo;
	}
	public void setArtigo(ArtigoDTO artigo) {
		this.artigo = artigo;
	}
	public UsuarioDTO getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
//	public ArtigoDTO getArtigoDTO() {
//		return artigo;
//	}
//	public void setArtigoDTO(ArtigoDTO artigo) {
//		this.artigo = artigo;
//	}
//	public UsuarioDTO getUsuarioDTO() {
//		return usuario;
//	}
//	public void setUsuarioDTO(UsuarioDTO usuario) {
//		this.usuario = usuario;
//	}
//     
}
