package com.teste.pedidos.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name="movimento_acoes")
@Table(name="movimento_acoes")
public class MovimentoAcoes {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "artigo_id")
    private Artigo artigo;
    
    @Column(nullable = false)
    private boolean status;

	@Column(nullable = false, updatable = false, unique = true)
    private String transacaoId;

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

	public MovimentoAcoes(Long id, LocalDateTime dataCriacao, int quantidade, Artigo artigo) {
		super();
		this.id = id;
		this.dataCriacao = dataCriacao;
		this.quantidade = quantidade;
		this.artigo = artigo;
	}

	public MovimentoAcoes() {
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
		MovimentoAcoes other = (MovimentoAcoes) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
