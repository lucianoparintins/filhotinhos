package com.filhotinhos.app.estoque.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ItemProduto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    @OneToOne
    private Produto produto;
    private double preco;
    private LocalDateTime dataCadastro;
    
    @ManyToOne
    @JoinColumn(name = "movimentacao_estoque_id")
    private MovimentacaoEstoque movimentacaoEstoque;
    
    private int quantidade; // Novo campo para quantidade de itens
}
