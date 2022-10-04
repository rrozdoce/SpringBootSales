package com.rrozdoce.vendas.domain.entity;

import javax.persistence.*;
import java.util.Set;

// mostrar q a entidade é JPA, colocar TABLE se os nomes forem diferentes
@Entity
//@Table(name = "tb_cliente", schema = "vendas")
@Table(name =  "cliente")
public class Cliente {

    @Id // define a primary key da entidade
    @GeneratedValue(strategy = GenerationType.AUTO) // AUTO_INCREMENT
    @Column(name = "id") // fazer as definicoes da coluna, colocar name, SE os nomes nao batarem com o banco
    private Integer id;

    @Column(name = "nome", length = 100) // pode colocar se ela é unica etc... like that
    private String nome;

    @OneToMany(mappedBy = "") // Essa entidade nao tem nenhuma chave p/ os pedidos, quem tem a chave é a tabela de pedidos
    private Set<Pedido> pedidos; // set evita alguns erros que podem ocorrer no Hibernate, mas voce pode usar Collection, List etc...

    public Cliente() {
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
