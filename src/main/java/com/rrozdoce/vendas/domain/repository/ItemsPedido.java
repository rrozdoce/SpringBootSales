package com.rrozdoce.vendas.domain.repository;

import com.rrozdoce.vendas.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedido extends JpaRepository<Pedido, Integer> {

}
