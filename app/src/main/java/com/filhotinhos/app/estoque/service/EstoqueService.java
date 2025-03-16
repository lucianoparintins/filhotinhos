package com.filhotinhos.app.estoque.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.filhotinhos.app.estoque.model.Produto;
import com.filhotinhos.app.estoque.repository.ProdutoRepository;

@Service
public class EstoqueService {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    public void adicionar(Long idProduto, Integer quantidade) {
        Produto produto = produtoService.buscarPorId(idProduto);
        produto.setQuantidade(produto.getQuantidade() + quantidade);

        produtoRepository.save(produto);
    }

    public void remover(Long idProduto, Integer quantidade) {
        Produto produto = produtoService.buscarPorId(idProduto);
        
        if (produto.getQuantidade() < quantidade) {
            throw new RuntimeException("Quantidade em estoque insuficiente");
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);
        
        produtoRepository.save(produto); 
    }

    public List<Produto> listarProdutosEmEstoque() {
        return produtoService.listarProdutosComQuantidadeEstoque();
    }
    
}
