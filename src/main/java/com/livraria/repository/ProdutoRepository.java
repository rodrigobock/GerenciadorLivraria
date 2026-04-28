package com.livraria.repository;

import com.livraria.model.Produto;
import com.livraria.model.VendaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByTipoProduto(VendaEnum tipoProduto);
}
