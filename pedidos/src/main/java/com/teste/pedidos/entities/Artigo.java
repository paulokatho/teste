package com.teste.pedidos.entities;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="artigo")
@Table(name="artigo")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Artigo {

	@Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 150)
    private String nome;

    @Column(nullable = false)
    private boolean status;
	
}
