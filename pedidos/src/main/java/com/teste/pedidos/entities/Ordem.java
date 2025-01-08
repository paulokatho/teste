package com.teste.pedidos.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ordem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Ordem {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private int quantidade;

    @Column(nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "artigo_id")
    private Artigo artigo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
