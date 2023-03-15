package com.paulocavalcante.vendas.vendas.service.impl;

import com.paulocavalcante.vendas.vendas.domain.repository.Pedidos;
import com.paulocavalcante.vendas.vendas.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }
}
