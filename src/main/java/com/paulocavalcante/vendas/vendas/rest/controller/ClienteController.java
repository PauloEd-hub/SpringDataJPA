package com.paulocavalcante.vendas.vendas.rest.controller;


import com.paulocavalcante.vendas.vendas.domain.entity.Cliente;
import com.paulocavalcante.vendas.vendas.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clientes;


    @GetMapping("/{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return clientes.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente) {
        return clientes.save(cliente);

    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        clientes.findById(id).map(cliente -> {
            clientes.delete(cliente);
            return cliente;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Cliente não encontrado"));




    }

    @PutMapping("/edita/{id}")
    public void update(@PathVariable Integer id, @RequestBody Cliente cliente) {
        clientes
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }


    @GetMapping
    public List<Cliente> find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example);

    }
}
