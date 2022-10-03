package com.rrozdoce.vendas.domain.repositorio;

import com.rrozdoce.vendas.domain.entity.Cliente;
import org.hibernate.procedure.spi.ParameterRegistrationImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// quando voce anota com @Repository o spring ve que voce vai fazer operacoes na base de dados
// @Repository tem o diferencial que ele traduz as excecoes que ocorrem no banco
@Repository
public class Clientes {

    private static String INSERT = "INSERT INTO CLIENTE (NOME) VALUES(?) ";
    private static String SELECT_ALL = "SELECT * FROM CLIENTE ";
    private static String UPDATE = "UPDATE CLIENTE SET NOME = ? WHERE ID = ? ";
    private static String DELETE = "DELETE FROM CLIENTE WHERE ID = ? ";

    // tem alguns metodos que permitem a gente fazer operacoes na base de dados
   @Autowired
   private JdbcTemplate jdbcTemplate;

   @Autowired
   private EntityManager entityManager; // é a interface que faz todas as operacoes na base com as entidades

    @Transactional // falar que o metodo vai geral uma transacao na base de dados
    public Cliente salvar(Cliente cliente) {
        entityManager.persist(cliente); // entity manager vai reconhecer cada uma das colunas e vai 'persistir'
        return cliente;
    }

    public Cliente atualizar(Cliente cliente) {
        jdbcTemplate.update(UPDATE, new Object[]{
           cliente.getNome(),
           cliente.getId()
        });
        return cliente;
    }

    public void deletar(Cliente cliente) {
       deletar(cliente.getId());
    }

    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{id});
    }
    public List<Cliente> buscarPorNome(String nome){
        return jdbcTemplate.query(
                SELECT_ALL.concat(" where nome like ? "),
                obterClienteMapper(),
                new Object[]{"%" + nome + "%"}
                );
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
