package com.filhotinhos.app.estoque.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.filhotinhos.app.estoque.model.Produto;
import com.filhotinhos.app.estoque.repository.ProdutoRepository;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProdutoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProdutoRepository produtoRepository;

    @BeforeEach
    public void setup() {
        // Limpa o banco de dados antes de cada teste
        produtoRepository.deleteAll();
    }

    @Test
    public void testListarProdutos() throws Exception {
        // Insere um produto no banco de dados
        Produto produto = new Produto();
        produto.setNome("Camiseta");
        produto.setDescricao("Camiseta branca");
        produtoRepository.save(produto);

        // Testa a rota GET /api/produtos
        mockMvc.perform(MockMvcRequestBuilders.get("/api/produtos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Camiseta"))
                .andExpect(jsonPath("$[0].descricao").value("Camiseta branca"));
    }

    @Test
    public void testCriarProduto() throws Exception {
        // Cria um JSON para o novo produto
        String produtoJson = "{ \"nome\": \"Calça Jeans\", \"descricao\": \"Calça azul\" }";

        // Testa a rota POST /api/produtos
        mockMvc.perform(MockMvcRequestBuilders.post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(produtoJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Calça Jeans"))
                .andExpect(jsonPath("$.descricao").value("Calça azul"));
    }

    @Test
    public void testBuscarProdutoPorId() throws Exception {
        // Insere um produto no banco de dados
        Produto produto = new Produto();
        produto.setNome("Tênis");
        produto.setDescricao("Tênis esportivo");
        produto = produtoRepository.save(produto);

        // Testa a rota GET /api/produtos/{id}
        mockMvc.perform(MockMvcRequestBuilders.get("/api/produtos/" + produto.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Tênis"))
                .andExpect(jsonPath("$.descricao").value("Tênis esportivo"));
    }

    @Test
    public void testDeletarProduto() throws Exception {
        // Insere um produto no banco de dados
        Produto produto = new Produto();
        produto.setNome("Boné");
        produto.setDescricao("Boné preto");
        produto = produtoRepository.save(produto);

        // Testa a rota DELETE /api/produtos/{id}
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/produtos/" + produto.getId()))
                .andExpect(status().isOk());

        // Verifica se o produto foi realmente deletado
        mockMvc.perform(MockMvcRequestBuilders.get("/api/produtos/" + produto.getId()))
                .andExpect(status().isNotFound());
    }
}
