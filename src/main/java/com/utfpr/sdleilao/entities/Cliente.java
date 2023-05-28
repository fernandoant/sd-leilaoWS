package com.utfpr.sdleilao.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cliente {

    @JsonProperty
    private Integer id;
    @JsonProperty
    private String nome;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente() {
        this.nome = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdCliente(Integer idCliente) {
        this.id = idCliente;
    }

    @Override
    public String toString() {
        return "Cliente {" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                '}';
    }
}
