package com.paulocavalcante.vendas.vendas.domain.repositorio;

import com.paulocavalcante.vendas.vendas.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {


    private static String INSERT = "insert into cliente (nome) values (?)";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE";

    @Autowired
    private JdbcTemplate jdbcTemplate;
    public Cliente salvar(Cliente cliente) {
        jdbcTemplate.update( INSERT, new Object[]{cliente.getNome()} );
        return cliente;

    }

    public List<Cliente> obterTodos() {
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("id");
                String nome = rs.getString("nome");
                return new Cliente(id, nome);
            }
        });
    }
}
