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

    public List<Produto> listarTodos(){
        return produtoRepository.findAll();
    }


    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Produto Não Encontrado")
        );
    }

    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }

    public void deletar(Long id){   
        produtoRepository.deleteById(id);
    }


    
}