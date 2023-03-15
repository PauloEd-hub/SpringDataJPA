package com.paulocavalcante.vendas.vendas.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemsPedidoDTO {

    private Integer produto;
    private Integer quantidade;
}
