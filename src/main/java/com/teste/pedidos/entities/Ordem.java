package com.teste.pedidos.entities;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "ordem")
public class Ordem {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private boolean status;
    
    @Column(nullable = false, updatable = false, unique = true)
    private String transacaoId;
    
    @Column(nullable = false)
    private String situacaoPedido;

    @ManyToOne
    @JoinColumn(name = "artigo_id")
    private Artigo artigo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
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

	public String getTransacaoId() {
		return transacaoId;
	}

	public void setTransacaoId(String transacao) {
		this.transacaoId = transacao;
	}

	public String getSituacaoPedido() {
		return situacaoPedido;
	}

	public void setSituacaoPedido(String situacaoPedido) {
		this.situacaoPedido = situacaoPedido;
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

	public Ordem(Long id, LocalDateTime dataCriacao, int quantidade, boolean status, Artigo artigo, Usuario usuario) {
		super();
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.quantidade = quantidade;
		this.status = status;
		this.artigo = artigo;
		this.usuario = usuario;
	}

	public Ordem() {
		super();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ordem other = (Ordem) obj;
		return Objects.equals(id, other.id);
	}
    
    @PrePersist
    public void prePersist() {
    	this.transacaoId = UUID.randomUUID().toString();
        this.dataCriacao = LocalDateTime.now();
    }
}
