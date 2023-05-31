package com.utfpr.sdleilao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.utfpr.sdleilao.resource.SseWebMvcController;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;

public class Cliente {

    @JsonProperty
    private Integer id;
    @JsonProperty
    private String nome;

    @JsonIgnore
    private SseEmitter emissor;

    public Cliente(String nome) {
        this.emissor = null;
        this.nome = nome;
    }

    public Cliente() {
        this.emissor = null;
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

    public SseEmitter getEmissor() {
        return emissor;
    }

    public void setEmissor(SseEmitter emissor) {
        this.emissor = emissor;
    }

    @Override
    public String toString() {
        return "Cliente {" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                '}';
    }
}
