package com.filhotinhos.app.estoque.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filhotinhos.app.estoque.model.Produto;
import com.filhotinhos.app.estoque.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoControler {
    
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listarTodos(){
        return produtoService.getProdutoRepository().findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto){
        produto.setDataCadastro(LocalDateTime.now());
        return produtoService.getProdutoRepository().save(produto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        Produto produto = produtoService.buscarPorId(id);
        produtoService.getProdutoRepository().delete(produto);
    }
}
