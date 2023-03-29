package com.paulocavalcante.vendas.vendas.domain.repository;

import com.paulocavalcante.vendas.vendas.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsPedidos extends JpaRepository<ItemPedido, Integer> {
}
