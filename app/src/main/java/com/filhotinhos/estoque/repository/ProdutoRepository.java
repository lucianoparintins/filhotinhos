package com.filhotinhos.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filhotinhos.estoque.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    
}
