package com.filhotinhos.app.estoque.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.filhotinhos.app.estoque.model.Produto;

@SpringBootTest
@AutoConfigureMockMvc
public class EstoqueServiceTest {

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private ProdutoService produtoService;

    @BeforeEach
    public void setUp() {
        produtoService.getProdutoRepository().deleteAll();
    }

    @Test
    public void testAdicionar() {

    
        Produto produto = new Produto();
        produto.setQuantidadeEstoque(10);

        produto = produtoService.getProdutoRepository().save(produto);

        estoqueService.adicionar(produto.getId(), 5);

        produto = produtoService.buscarPorId(produto.getId());

        assertEquals(15, produto.getQuantidadeEstoque());
        
    }

    @Test
    public void testRemover() {
        Produto produto = new Produto();
        produto.setQuantidadeEstoque(10);

        produto = produtoService.getProdutoRepository().save(produto);

        estoqueService.remover(produto.getId(), 5);

        produto = produtoService.buscarPorId(produto.getId()); 

        assertEquals(5, produto.getQuantidadeEstoque());
    }

    @Test
    public void testRemoverQuantidadeInsuficiente() {
        Produto produto = new Produto();
        produto.setQuantidadeEstoque(10);

        produto = produtoService.getProdutoRepository().save(produto);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            estoqueService.remover(1L, 15);
        });

        assertEquals("Quantidade em estoque insuficiente", exception.getMessage());
    }

    @Test
    public void testListarProdutosEmEstoque() {
        Produto produto1 = new Produto();
        produto1.setQuantidadeEstoque(10);
        produto1 = produtoService.getProdutoRepository().save(produto1);

        Produto produto2 = new Produto();
        produto2.setQuantidadeEstoque(20);
        produto2 = produtoService.getProdutoRepository().save(produto2);

        List<Produto> result = estoqueService.listarProdutosEmEstoque();

        assertEquals(2, result.size());
        assertEquals(10, result.get(0).getQuantidadeEstoque());
        assertEquals(20, result.get(1).getQuantidadeEstoque());
    }
}
