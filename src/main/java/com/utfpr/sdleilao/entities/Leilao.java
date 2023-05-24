package com.utfpr.sdleilao.entities;

import java.util.*;

public class Leilao extends Thread {

    private Integer idLeilao;
    private Cliente criador;
    private Produto produto;
    private Map<String, Cliente> clientes;
    private ArrayList<Lance> lances;

    private Lance lanceRecebido;
    private Lance lanceAtual;
    private final Integer duracao;
    // func notificar
    private boolean isActive;


    public Leilao(Integer idLeilao, Cliente criador, Produto produto, Integer duracao) {
        this.idLeilao = idLeilao;
        this.criador = criador;
        this.produto = produto;
        this.clientes = new HashMap<>();
        this.lances = new ArrayList<>();
        this.lanceRecebido = null;
        this.lanceAtual = new Lance(null, produto.getPrecoMinimo());
        this.duracao = duracao;
        this.clientes.put(criador.getNome(), criador);
        this.isActive = true;
    }

    public Leilao() {
        this.idLeilao = -1;
        this.criador = null;
        this.produto = null;
        this.clientes = null;
        this.lances = null;
        this.lanceRecebido = null;
        this.lanceAtual = null;
        this.duracao = -1;
        this.isActive = false;
    }

    public void iniciarLeilao() {
        this.start();
    }

    public void run() {
        long inicio = System.currentTimeMillis();
        long atual = 0;
        do {
            if (this.lanceRecebido != null) {
                this.lances.add(this.lanceRecebido);
                this.lanceAtual = this.lanceRecebido;
                this.lanceRecebido = null;
            }
            atual = System.currentTimeMillis();
        } while (atual - inicio < this.duracao);
        this.isActive = false;
    }

    public boolean darLance(Lance lance) {
        if (!this.isActive) {
            return false;
        }
        if (lance.getValor() <= this.lanceAtual.getValor()) {
            return false;
        }

        Cliente cliente = lance.getCliente();
        Cliente clienteRegistrado = this.clientes.get(cliente.getNome());

        if (clienteRegistrado != null) {
            this.clientes.put(cliente.getNome(), cliente);
        }
        this.lances.add(lance);
        this.lanceRecebido = lance;
        return true;
    }

}