package com.utfpr.sdleilao.entities;

public class Produto {

    private String nome;
    private String descricao;
    private Double precoMinimo;

    public Produto(String nome, String descricao, Double precoMinimo) {
        this.nome = nome;
        this.descricao = descricao;
        this.precoMinimo = precoMinimo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPrecoMinimo() {
        return precoMinimo;
    }

    public void setPrecoMinimo(Double precoMinimo) {
        this.precoMinimo = precoMinimo;
    }

    @Override
    public String toString() {
        return "Produto {" +
                "nome = '" + nome + '\'' +
                ", descricao = '" + descricao + '\'' +
                ", preco = " + precoMinimo +
                '}';
    }
}
