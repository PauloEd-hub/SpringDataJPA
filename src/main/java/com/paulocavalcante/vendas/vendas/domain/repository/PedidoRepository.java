package com.paulocavalcante.vendas.vendas.domain.repository;

import com.paulocavalcante.vendas.vendas.domain.entity.Cliente;
import com.paulocavalcante.vendas.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

    List<Pedido> findByCliente(Cliente cliente);


    @Query(" select p from Pedido p left join fetch p.itens where p.id = :id")
     Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
