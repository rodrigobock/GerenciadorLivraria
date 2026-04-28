package com.livraria.controller;

import com.livraria.model.Produto;
import com.livraria.model.VendaEnum;
import com.livraria.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private VendaService service;

    @GetMapping
    public List<Produto> listarTodos() {
        return service.listarTodos();
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return service.registrarProduto(produto);
    }

    @GetMapping("/tipo/{tipo}")
    public List<Produto> buscarPorTipo(@PathVariable VendaEnum tipo) {
        return service.recuperarPorTipo(tipo);
    }

    @PostMapping("/venda")
    public ResponseEntity<String> realizarVenda(@RequestParam VendaEnum tipo, @RequestParam int quantidade) {
        boolean sucesso = service.realizarVenda(tipo, quantidade);
        if (sucesso) {
            return ResponseEntity.ok("Venda realizada com sucesso!");
        }
        return ResponseEntity.badRequest().body("Estoque insuficiente ou quantidade inválida.");
    }
}
