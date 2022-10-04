package com.rrozdoce.vendas.domain.repository;

import com.rrozdoce.vendas.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {

}
