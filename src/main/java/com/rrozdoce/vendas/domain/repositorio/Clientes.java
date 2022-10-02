package com.rrozdoce.vendas.domain.repositorio;

import com.rrozdoce.vendas.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// quando voce anota com @Repository o spring ve que voce vai fazer operacoes na base de dados
// @Repository tem o diferencial que ele traduz as excecoes que ocorrem no banco
@Repository
public class Clientes {

    private static String INSERT = "INSERT INTO CLIENTE (NOME) VALUES(?) ";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE ";


    // tem alguns metodos que permitem a gente fazer operacoes na base de dados
   @Autowired
   private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente) {
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public List<Cliente> obterTodos(){
        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
    }

    private RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() { // RowMapper mapeia o resultado do banco p/ uma classe
            @Override
            public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {

                Integer id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                return new Cliente(id, nome);
            }
        };
    }

}
