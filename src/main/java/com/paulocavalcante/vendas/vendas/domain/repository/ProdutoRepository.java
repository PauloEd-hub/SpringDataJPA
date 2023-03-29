package com.paulocavalcante.vendas.vendas.domain.repository;

import com.paulocavalcante.vendas.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
