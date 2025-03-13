package com.filhotinhos.app.estoque.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    private MovimentacaoEstoque movimentacaoEstoque;

}
