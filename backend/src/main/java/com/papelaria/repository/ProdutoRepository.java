package com.papelaria.repository;

import com.papelaria.model.Produto;
import com.papelaria.model.VendaEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByTipoProduto(VendaEnum tipoProduto);
}
