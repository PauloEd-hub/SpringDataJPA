package com.paulocavalcante.vendas.vendas.rest.controller;


import com.paulocavalcante.vendas.vendas.domain.entity.ItemPedido;
import com.paulocavalcante.vendas.vendas.domain.entity.Pedido;
import com.paulocavalcante.vendas.vendas.rest.dto.InformacaoItemPedidoDTO;
import com.paulocavalcante.vendas.vendas.rest.dto.InformacoesPedidosDTO;
import com.paulocavalcante.vendas.vendas.rest.dto.PedidoDTO;
import com.paulocavalcante.vendas.vendas.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;


    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto) {
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();

    }

    @GetMapping("/{id}")
    public InformacoesPedidosDTO getById(@PathVariable Integer id) {
        return pedidoService
                .obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    private InformacoesPedidosDTO converter(Pedido pedido) {
        return InformacoesPedidosDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .items(converter(pedido.getItens()))
                .build();

    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
        if(CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens.stream().map(
                item -> InformacaoItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }

}
