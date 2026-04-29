package com.papelaria.controller;

import com.papelaria.model.Produto;
import com.papelaria.model.VendaEnum;
import com.papelaria.service.VendaService;
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

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return service.registrarProduto(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        return service.atualizarProduto(id, produto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (service.removerProduto(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
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
