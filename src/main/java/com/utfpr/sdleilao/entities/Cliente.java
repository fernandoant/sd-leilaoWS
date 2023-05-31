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
    private SseEmitter emissor = null;
    @JsonIgnore
    private final SseWebMvcController sseController;

    public Cliente(String nome) {
        sseController = new SseWebMvcController();
        this.nome = nome;
    }

    public Cliente() {
        sseController = new SseWebMvcController();
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

    public SseEmitter getEmissor() { return emissor; }

    public void setEmissor(SseEmitter emissor) {this.emissor = emissor; }

    public void sendEvents(String evento, Object msg){
        sseController.sendEvents(this,evento,msg);
    }

    @Override
    public String toString() {
        return "Cliente {" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                '}';
    }
}
