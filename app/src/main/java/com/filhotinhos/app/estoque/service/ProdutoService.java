package com.filhotinhos.app.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filhotinhos.app.estoque.model.Produto;
import com.filhotinhos.app.estoque.repository.ProdutoRepository;
import com.filhotinhos.app.exception.ResourceNotFoundException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Produto Não Encontrado")
        );
    }

    public List<Produto> listarProdutosComQuantidadeEstoque() {
        List<Produto> produtos = produtoRepository.findAll();
        for (Produto produto : produtos) {
            if (produto.getQuantidadeEstoque() <= 0) {
                produtos.remove(produto);
            }
        }
        return produtos;
    }

    @Autowired
    public ProdutoRepository getProdutoRepository() {
        return this.produtoRepository;
    }

}