package com.utfpr.sdleilao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Lance {

    @JsonProperty
    private Cliente cliente;
    @JsonProperty
    private Integer idLeilao;
    @JsonProperty
    private Double valor;

    public Lance(Cliente cliente, Integer idLeilao, Double valor) {
        this.cliente = cliente;
        this.idLeilao = idLeilao;
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public void setIdLeilao(Integer idLeilao) {
        this.idLeilao = idLeilao;
    }

    public Integer getIdLeilao() {
        return this.idLeilao;
    }

    @Override
    public String toString() {
        return "Lance {" +
                "cliente =" + cliente +
                ", valor =" + valor +
                '}';
    }
}
