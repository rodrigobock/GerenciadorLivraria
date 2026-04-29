package com.papelaria.service;

import com.papelaria.model.Produto;
import com.papelaria.model.VendaEnum;
import com.papelaria.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    @Autowired
    private ProdutoRepository repository;

    public Produto registrarProduto(Produto produto) {
        return repository.save(produto);
    }

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Optional<Produto> atualizarProduto(Long id, Produto dados) {
        return repository.findById(id).map(existente -> {
            existente.setCodigo(dados.getCodigo());
            existente.setTipoProduto(dados.getTipoProduto());
            existente.setDescricao(dados.getDescricao());
            existente.setQuantidade(dados.getQuantidade());
            existente.setMarca(dados.getMarca());
            existente.setPreco(dados.getPreco());
            existente.setCor(dados.getCor());
            existente.setTamanho(dados.getTamanho());
            return repository.save(existente);
        });
    }

    public boolean removerProduto(Long id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    public List<Produto> recuperarPorTipo(VendaEnum tipo) {
        return repository.findByTipoProduto(tipo);
    }

    @Transactional
    public boolean realizarVenda(VendaEnum tipo, int quantidade) {
        List<Produto> produtos = repository.findByTipoProduto(tipo);
        
        int estoqueTotal = produtos.stream()
                .mapToInt(Produto::getQuantidade)
                .sum();

        if (quantidade > 0 && estoqueTotal >= quantidade) {
            // Lógica simples: desconta do primeiro produto encontrado com estoque
            // Em um sistema real, poderíamos ter lógica de FIFO ou escolher lote específico
            for (Produto p : produtos) {
                if (p.getQuantidade() >= quantidade) {
                    p.setQuantidade(p.getQuantidade() - quantidade);
                    repository.save(p);
                    return true;
                }
            }
        }
        return false;
    }

    public int verificarEstoque(VendaEnum tipo) {
        return repository.findByTipoProduto(tipo).stream()
                .mapToInt(Produto::getQuantidade)
                .sum();
    }
}
