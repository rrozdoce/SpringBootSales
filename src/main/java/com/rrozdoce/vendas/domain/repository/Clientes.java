package com.rrozdoce.vendas.domain.repository;

import com.rrozdoce.vendas.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// O JpaRepository ja tem as operacoes encapsuladas dentro dele
public interface Clientes extends JpaRepository<Cliente, Integer> {

   @Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery = true) // " select c from Cliente c where c.nome like :nome " -> HQL, para mudar pra SQL coloque nativeQuery=true
   List<Cliente> econtrarPorNome(@Param("nome") String nome); // colocar o nome do parametro dentro de @Param para se referir a ':nome'

   @Query(" delete from Cliente c where c.nome =:nome ")
   @Modifying // dizer q vai fazer att na tabela, seja ela de deleção ou registro
   void deleteByNome(String nome);

   boolean existsByNome(String nome);

   @Query(" select c from Cliente c left join fetch c.pedidos p  where c.id =:id ")
   Cliente findClienteFetchPedidos(@Param("id") Integer id);



}
