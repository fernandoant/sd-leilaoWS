package com.utfpr.sdleilao.entities;

import com.utfpr.sdleilao.services.Servidor;
import java.util.Collection;

public class Leilao {

    private final LeilaoItem leilaoItem;
    private final Thread thread;


    public Leilao(Integer idLeilao, Cliente criador, Produto produto, Integer duracao) {
        this.leilaoItem = new LeilaoItem(idLeilao, criador, produto, duracao);
        this.thread = new Thread(this::run);
    }

    public Leilao() {
        this.leilaoItem = new LeilaoItem(-1, null, null, -1);
        this.thread = new Thread(this::run);
    }

    public void iniciarLeilao() {
        this.thread.start();
    }

    public void run() {
        long inicio = System.currentTimeMillis();
        Integer duracao = this.leilaoItem.getDuracao() * 1000;
        long atual = 0;
        try {
            do {
                Thread.sleep(1000);
                Lance lanceRecebido = this.leilaoItem.getLanceRecebido();
                if (lanceRecebido != null) {
                    leilaoItem.addLance(lanceRecebido);
                    this.leilaoItem.setLanceAtual(lanceRecebido);
                    this.leilaoItem.setLanceRecebido(null);
                    System.out.println("Lance recebido! Registrado lance no valor de R$" + this.leilaoItem.getLanceAtual().getValor());
                }
                atual = System.currentTimeMillis();
            } while (atual - inicio < duracao);
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
        leilaoItem.setActive(false);
        Collection<Cliente> clientes = getLeilaoItem().getClientes().values();
        Servidor.sendEvent(clientes, "notifyEndLeilao", leilaoItem);
    }

    public boolean darLance(Lance lance) {
        if (!leilaoItem.isActive()) {
            System.out.println("darLance: leilão inativo");
            return false;
        }
        if (lance.getValor() <= leilaoItem.getLanceAtual().getValor()) {
            return false;
        }

        Cliente cliente = lance.getCliente();
        Cliente isClienteRegistrado = leilaoItem.searchCliente(cliente.getNome());

        if (isClienteRegistrado != null) {
            this.leilaoItem.addCliente(cliente);
        }
        this.leilaoItem.setLanceRecebido(lance);

        Collection<Cliente> clientes = getLeilaoItem().getClientes().values();

        Servidor.sendEvent(clientes, "notifyNewLance", lance);

        return true;
    }

    public LeilaoItem getLeilaoItem() {
        return this.leilaoItem;
    }
}