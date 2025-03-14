package com.filhotinhos.app.estoque.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.filhotinhos.app.estoque.model.ItemProduto;
import com.filhotinhos.app.estoque.model.MovimentacaoEstoque;
import com.filhotinhos.app.estoque.model.Produto;
import com.filhotinhos.app.estoque.model.TipoMovimentacao;
import com.filhotinhos.app.estoque.service.EstoqueService;
import com.filhotinhos.app.estoque.service.ProdutoService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class EstoqueControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private ProdutoService produtoService;

    @InjectMocks
    private EstoqueController estoqueController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(estoqueController).build();
        resetDatabase();
    }

    private void resetDatabase() {
    }

    @Test
    public void testAdicionar() throws Exception {
        Produto produto = new Produto();
        produto.setNome("Camiseta");
        produto.setDescricao("Camiseta branca");
        produto.setDataCadastro(null);
        
        when(produtoService.salvar(produto)).thenReturn(produto);

        List<ItemProduto> itens = new ArrayList<>();

        ItemProduto itemProduto1 = new ItemProduto();
        itemProduto1.setDataCadastro(null);
        itemProduto1.setProduto(produto);
        itemProduto1.setPreco(10.0);
        itens.add(itemProduto1);

        ItemProduto itemProduto2= new ItemProduto();
        itemProduto2.setDataCadastro(null);
        itemProduto2.setProduto(produto);
        itemProduto2.setPreco(10.0);
        itens.add(itemProduto2);
        

        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
        movimentacaoEstoque.setDataCadastro(null);
        movimentacaoEstoque.setItensProduto(itens);
        movimentacaoEstoque.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
        movimentacaoEstoque.setQuantidade(2);
        movimentacaoEstoque.setValorTotal(20.0);

        when(estoqueService.adicionar(itens)).thenReturn(movimentacaoEstoque);

        mockMvc.perform(post("/entrada")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(itens)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(movimentacaoEstoque)));
    }

    @Test
    public void testAdicionarEmptyList() throws Exception {
        mockMvc.perform(post("/entrada")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Collections.emptyList())))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRetirar() throws Exception {
        ItemProduto itemProduto = new ItemProduto();
        // ...configure itemProduto...

        MovimentacaoEstoque movimentacaoEstoque = new MovimentacaoEstoque();
        // ...configure movimentacaoEstoque...

        when(estoqueService.retirar(Arrays.asList(itemProduto))).thenReturn(movimentacaoEstoque);

        mockMvc.perform(post("/saida")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Arrays.asList(itemProduto))))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(movimentacaoEstoque)));
    }

    @Test
    public void testRetirarEmptyList() throws Exception {
        mockMvc.perform(post("/saida")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(Collections.emptyList())))
                .andExpect(status().isBadRequest());
    }
}
