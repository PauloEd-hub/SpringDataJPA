package com.paulocavalcante.vendas.vendas.service;

import com.paulocavalcante.vendas.vendas.domain.entity.Pedido;
import com.paulocavalcante.vendas.vendas.domain.enums.StatusPedido;
import com.paulocavalcante.vendas.vendas.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatusPedido(Integer id, StatusPedido statusPedido);
}
