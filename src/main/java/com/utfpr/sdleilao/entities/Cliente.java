package com.utfpr.sdleilao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public class Cliente {

    @JsonProperty
    private Integer id;
    @JsonProperty
    private String nome;

    @JsonIgnore
    private SseEmitter connection;

    public Cliente(String nome) {
        this.connection = null;
        this.nome = nome;
    }

    public Cliente() {
        this.connection = null;
        this.nome = null;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public SseEmitter getConnection() {
        return this.connection;
    }

    public void setConnection(SseEmitter connection) {
        this.connection = connection;
    }

    @Override
    public String toString() {
        return "Cliente {" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                '}';
    }
}
