package com.filhotinhos.app.estoque.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MovimentacaoEstoque {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataCadastro;
    
    @OneToMany(mappedBy = "movimentacaoEstoque")
    private List<ItemProduto> itensProduto;

    private TipoMovimentacao tipoMovimentacao;

}
