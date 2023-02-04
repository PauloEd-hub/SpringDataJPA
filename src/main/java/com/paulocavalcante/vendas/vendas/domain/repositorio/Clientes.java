package com.paulocavalcante.vendas.vendas.domain.repositorio;

import com.paulocavalcante.vendas.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Clientes extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNome(String nome);

     boolean existsByNome(String nome);
}
