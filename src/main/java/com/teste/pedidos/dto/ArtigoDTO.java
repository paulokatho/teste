package com.teste.pedidos.dto;

import java.util.Objects;

public class ArtigoDTO {

	private Long id;
	private String nome;
	private boolean status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public ArtigoDTO(Long id, String nome, boolean status) {
		super();
		this.id = id;
		this.nome = nome;
		this.status = status;
	}
	public ArtigoDTO() {
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
		ArtigoDTO other = (ArtigoDTO) obj;
		return Objects.equals(id, other.id);
	}
	
}
