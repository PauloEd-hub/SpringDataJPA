package com.paulocavalcante.vendas.vendas.exception;

public class PedidoNaoEncontradoException extends RuntimeException{

    public PedidoNaoEncontradoException() {
        super("Pedido não encontrado");
    }
}
