package com.paulocavalcante.vendas.vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacoesPedidosDTO {

    private Integer codigo;
    private String cpf;
    private String nomeCliente;
    private BigDecimal total;
    private String dataPedido;
    private String status;
    private List<InformacaoItemPedidoDTO> items;
}
