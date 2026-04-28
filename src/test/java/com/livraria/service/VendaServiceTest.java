package com.livraria.service;

import com.livraria.model.Produto;
import com.livraria.model.VendaEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class VendaServiceTest {

    @Autowired
    private VendaService service;

    @Test
    void deveRegistrarERecuperarProduto() {
        Produto produto = new Produto();
        produto.setCodigo(100);
        produto.setTipoProduto(VendaEnum.CANETA);
        produto.setDescricao("Caneta Azul");
        produto.setQuantidade(50);
        produto.setPreco(1.50);

        service.registrarProduto(produto);
        var produtos = service.recuperarPorTipo(VendaEnum.CANETA);
        
        assertFalse(produtos.isEmpty());
        assertEquals(50, service.verificarEstoque(VendaEnum.CANETA));
    }

    @Test
    void deveRealizarVendaComSucesso() {
        Produto produto = new Produto();
        produto.setTipoProduto(VendaEnum.LAPIS);
        produto.setQuantidade(10);
        service.registrarProduto(produto);

        boolean sucesso = service.realizarVenda(VendaEnum.LAPIS, 3);
        
        assertTrue(sucesso);
        assertEquals(7, service.verificarEstoque(VendaEnum.LAPIS));
    }
}
