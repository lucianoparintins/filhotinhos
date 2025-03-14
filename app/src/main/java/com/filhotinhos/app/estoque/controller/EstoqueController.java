package com.filhotinhos.app.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.filhotinhos.app.estoque.model.ItemProduto;
import com.filhotinhos.app.estoque.model.MovimentacaoEstoque;
import com.filhotinhos.app.estoque.service.EstoqueService;

public class EstoqueController {
    
    @Autowired
    private EstoqueService estoqueService;

    @PostMapping("/entrada")
    public MovimentacaoEstoque adicionar(@RequestBody List<ItemProduto> itensProdutos){
        return estoqueService.adicionar(itensProdutos);
    }

    @PostMapping("/saida")
    public MovimentacaoEstoque retirar(@RequestBody List<ItemProduto> itensProdutos){
        return estoqueService.retirar(itensProdutos);
    }
}