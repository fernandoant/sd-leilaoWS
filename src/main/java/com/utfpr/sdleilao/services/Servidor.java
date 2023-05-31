package com.utfpr.sdleilao.services;

import com.utfpr.sdleilao.entities.Cliente;
import com.utfpr.sdleilao.entities.Leilao;
import com.utfpr.sdleilao.entities.Produto;
import com.utfpr.sdleilao.entities.Lance;
import com.utfpr.sdleilao.resource.SseWebMvcController;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class Servidor {

    private final HashMap<String, Cliente> clientes;
    private final ArrayList<Leilao> leiloes;

    public Servidor() {
        clientes = new HashMap<>();
        leiloes = new ArrayList<>();
    }

    public Cliente cadastrarUsuario(Cliente cliente) {
        cliente.setId(this.clientes.size() + 1);

        if (this.clientes.get(cliente.getNome()) != null) {
            return null;
        }

        this.clientes.put(cliente.getNome(), cliente);
        return cliente;
    }

    public ArrayList<Leilao> listarLeiloes() {
        return this.leiloes;
    }
    public Leilao criarLeilao(Cliente criador, Produto produto, Integer duracao) {
        Integer idLeilao = this.leiloes.size() + 1;
        if (produto.getPrecoMinimo() <= 0) {
            return null;
        }
        if (duracao <= 0) {
            return null;
        }
        if (this.clientes.get(criador.getNome()) == null) {
            return null;
        }
        Leilao leilao = new Leilao(idLeilao, criador, produto, duracao);
        leilao.iniciarLeilao();
        this.leiloes.add(leilao);

        sendEvent(this.clientes.values(), "newLeilao", leilao);

        return leilao;
    }

    public boolean darLance(Integer idLeilao, Lance lance) {
        if (idLeilao <= 0 || idLeilao > this.leiloes.size()) {
            return false;
        }
        String nomeCliente = lance.getCliente().getNome();
        Cliente clienteRegistrado = this.clientes.get(nomeCliente);
        if (clienteRegistrado == null) {
            return false;
        }

        Leilao leilao = this.leiloes.get(idLeilao - 1);

        return leilao.darLance(lance);
    }

    public static void sendEvent(Collection<Cliente> clientes, String event, Object msg) {
        for (Cliente cliente: clientes) {
            SseWebMvcController.sendEvents(cliente, event, msg);
        }
    }

}
