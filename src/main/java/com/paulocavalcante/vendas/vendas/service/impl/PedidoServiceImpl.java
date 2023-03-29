package com.paulocavalcante.vendas.vendas.service.impl;

import com.paulocavalcante.vendas.vendas.domain.entity.Cliente;
import com.paulocavalcante.vendas.vendas.domain.entity.ItemPedido;
import com.paulocavalcante.vendas.vendas.domain.entity.Pedido;
import com.paulocavalcante.vendas.vendas.domain.entity.Produto;
import com.paulocavalcante.vendas.vendas.domain.repository.ClienteRepository;
import com.paulocavalcante.vendas.vendas.domain.repository.ItemsPedidos;
import com.paulocavalcante.vendas.vendas.domain.repository.PedidoRepository;
import com.paulocavalcante.vendas.vendas.domain.repository.ProdutoRepository;
import com.paulocavalcante.vendas.vendas.exception.RegraNegocioException;
import com.paulocavalcante.vendas.vendas.rest.dto.ItemsPedidoDTO;
import com.paulocavalcante.vendas.vendas.rest.dto.PedidoDTO;
import com.paulocavalcante.vendas.vendas.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clientesRepository;
    private final ProdutoRepository produtosRepository;
    private final ItemsPedidos itemsPedidosRepository;


    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente).orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItemsPedidoDTOS());
        pedidoRepository.save(pedido);
        itemsPedidosRepository.saveAll(itemsPedido);

        pedido.setItens(itemsPedido);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
    }


    public List<ItemPedido> converterItems(Pedido pedido, List<ItemsPedidoDTO> items) {
        if(items.isEmpty()) {
            throw new RegraNegocioException("Não é possível realizar um pedido sem items");
        }

        return items.stream().map(dto -> {
            Integer idProduto = dto.getProduto();
            Produto produto = produtosRepository.findById(idProduto)
                    .orElseThrow(() -> new RegraNegocioException("Código de produto inválido" + idProduto));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);

            return itemPedido;
        }).collect(Collectors.toList());


    }
}
