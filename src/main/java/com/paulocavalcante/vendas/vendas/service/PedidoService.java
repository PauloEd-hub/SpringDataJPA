package com.paulocavalcante.vendas.vendas.service;

import com.paulocavalcante.vendas.vendas.domain.entity.Pedido;
import com.paulocavalcante.vendas.vendas.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
}
