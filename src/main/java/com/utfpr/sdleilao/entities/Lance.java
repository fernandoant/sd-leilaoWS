package com.utfpr.sdleilao.entities;

public class Lance {

    private Cliente cliente;
    private Double valor;

    public Lance(Cliente cliente, Double valor) {
        this.cliente = cliente;
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

    @Override
    public String toString() {
        return "Lance {" +
                "cliente =" + cliente +
                ", valor =" + valor +
                '}';
    }
}
