package com.utfpr.sdleilao.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LeilaoItem {

    private final Integer idLeilao;
    private final DadosLeilao dadosLeilao;
    @JsonIgnore
    private final Map<String, Cliente> clientes;
    private final ArrayList<Lance> lances;

    @JsonIgnore
    private Lance lanceRecebido;
    @JsonIgnore
    private Lance lanceAtual;
    private boolean isActive;

    public LeilaoItem(Integer idLeilao, Cliente criador, Produto produto, Integer duracao) {
        this.idLeilao = idLeilao;
        this.dadosLeilao = new DadosLeilao(criador, produto, duracao);
        this.clientes = new HashMap<>();
        this.lances = new ArrayList<>();
        this.lanceRecebido = null;
        this.lanceAtual = new Lance(null, produto.getPrecoMinimo());
        this.clientes.put(criador.getNome(), criador);
        this.isActive = true;
    }

    public LeilaoItem() {
        this.idLeilao = -1;
        this.dadosLeilao = new DadosLeilao(null, null, -1);
        this.clientes = null;
        this.lances = null;
        this.lanceRecebido = null;
        this.lanceAtual = null;
        this.isActive = false;
    }

    public Integer getIdLeilao() {
        return idLeilao;
    }

    public Cliente getCriador() {
        return dadosLeilao.getCriador();
    }

    public Produto getProduto() {
        return dadosLeilao.getProduto();
    }

    public Integer getDuracao() {
        return dadosLeilao.getDuracao();
    }

    public Map<String, Cliente> getClientes() {
        return clientes;
    }

    public void addCliente(Cliente cliente) {
        this.clientes.put(cliente.getNome(), cliente);
    }

    public Cliente searchCliente(String nome) {
        return this.clientes.get(nome);
    }

    public ArrayList<Lance> getLances() {
        return lances;
    }

    public void addLance(Lance lance) {
        this.lances.add(lance);
    }

    public Lance getLanceRecebido() {
        return lanceRecebido;
    }

    public void setLanceRecebido(Lance lance) {
        this.lanceRecebido = lance;
    }

    public Lance getLanceAtual() {
        return lanceAtual;
    }

    public void setLanceAtual(Lance lance) {
        this.lanceAtual = lance;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

}
