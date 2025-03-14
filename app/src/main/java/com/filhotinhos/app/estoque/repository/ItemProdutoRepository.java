package com.filhotinhos.app.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filhotinhos.app.estoque.model.ItemProduto;

public interface ItemProdutoRepository extends JpaRepository<ItemProduto,Long> {
    
}
