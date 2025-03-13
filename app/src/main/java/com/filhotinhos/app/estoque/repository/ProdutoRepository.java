package com.filhotinhos.app.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filhotinhos.app.estoque.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    
}
