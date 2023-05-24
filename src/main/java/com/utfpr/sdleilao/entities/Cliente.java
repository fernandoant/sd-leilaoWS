package com.utfpr.sdleilao.entities;

public class Cliente {

    private Integer id;
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

    @Override
    public String toString() {
        return "Cliente {" +
                "id = " + id +
                ", nome = '" + nome + '\'' +
                '}';
    }
}
