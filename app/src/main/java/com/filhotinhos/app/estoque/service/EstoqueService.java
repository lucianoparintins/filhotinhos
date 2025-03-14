package com.filhotinhos.app.estoque.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filhotinhos.app.estoque.model.ItemProduto;
import com.filhotinhos.app.estoque.model.MovimentacaoEstoque;
import com.filhotinhos.app.estoque.model.TipoMovimentacao;
import com.filhotinhos.app.estoque.repository.MovimentacaoEstoqueRepository;


@Service
public class EstoqueService {

    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    public MovimentacaoEstoque adicionar(List<ItemProduto> itensProdutos){
        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
        movimentacaoEstoque.setItensProduto(itensProdutos);
        movimentacaoEstoque.setDataCadastro(LocalDateTime.now());
        movimentacaoEstoque.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
        movimentacaoEstoque.setQuantidade(itensProdutos.size());
        movimentacaoEstoque.setValorTotal(valorTotal(itensProdutos));

        return movimentacaoEstoqueRepository.save(movimentacaoEstoque);
    }

    public MovimentacaoEstoque retirar(List<ItemProduto> itensProdutos){
        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
        movimentacaoEstoque.setItensProduto(itensProdutos);
        movimentacaoEstoque.setDataCadastro(LocalDateTime.now());
        movimentacaoEstoque.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentacaoEstoque.setQuantidade(itensProdutos.size());
        movimentacaoEstoque.setValorTotal(valorTotal(itensProdutos));

        return movimentacaoEstoqueRepository.save(movimentacaoEstoque);
    }

    private double valorTotal(List<ItemProduto> itensProdutos){
        double valorTotal = 0;
        for(ItemProduto itemProduto : itensProdutos){
            valorTotal += itemProduto.getPreco() ;
        }
        return valorTotal;
    }
    
}
