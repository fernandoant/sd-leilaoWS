package com.utfpr.sdleilao.entities;

public class DadosLeilao {

    private final Cliente criador;
    private final Produto produto;
    private final Integer duracao;

    public DadosLeilao(Cliente criador, Produto produto, Integer duracao) {
        this.criador = criador;
        this.produto = produto;
        this.duracao = duracao;
    }

    public DadosLeilao() {
        this.criador = null;
        this.produto = null;
        this.duracao = null;
    }

    public Cliente getCriador() {
        return criador;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getDuracao() {
        return duracao;
    }

    @Override
    public String toString() {
        return "DadosLeilao { " +
                "criador = " + criador +
                ", produto = " + produto +
                ", duracao = " + duracao +
                " }";
    }
}
