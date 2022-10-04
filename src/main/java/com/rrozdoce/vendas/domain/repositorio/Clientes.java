package com.rrozdoce.vendas.domain.repositorio;

import com.rrozdoce.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// O JpaRepository ja tem as operacoes encapsuladas dentro dele
public interface Clientes extends JpaRepository<Cliente, Integer> {

    // select c from Cliente c where c.nome like :nome => a funcao abaixo
    List<Cliente> findByNomeLike(String nome); // spring data vai transformar esse metodo em uma query, o 'Like' é igual o like do SQL

    List<Cliente> findByNomeOrId(String nome, Integer id);

    // One chama um metodo que retorna apenas um registro, se retornar mais de um registro ele da erro
    //Cliente findOneByNome(String );

    boolean existsByNome(String nome);

}
