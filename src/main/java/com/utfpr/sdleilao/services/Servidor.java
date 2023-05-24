package com.utfpr.sdleilao.services;

import com.utfpr.sdleilao.entities.Cliente;
import com.utfpr.sdleilao.entities.Leilao;
import com.utfpr.sdleilao.entities.Produto;
import com.utfpr.sdleilao.entities.Lance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class Servidor {

    private HashMap<String, Cliente> clientes;
    private ArrayList<Leilao> leiloes;

    public Servidor() {
        clientes = new HashMap<>();
        leiloes = new ArrayList<>();
    }

    public boolean cadastrarUsuario(Cliente cliente) {
        this.clientes.putIfAbsent(cliente.getNome(), cliente);
        return this.clientes.get(cliente.getNome()) != null;
    }

    public ArrayList<Leilao> listarLeiloes() {
        return this.leiloes;
    }
    public boolean criarLeilao(Cliente criador, Produto produto, Integer duracao) {
        Integer idLeilao = this.leiloes.size() + 1;
        if (produto.getPrecoMinimo() <= 0) {
            return false;
        }
        if (duracao <= 0) {
            return false;
        }
        Leilao leilao = new Leilao(idLeilao, criador, produto, duracao);
        leilao.iniciarLeilao();
        this.leiloes.add(leilao);
        return true;
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

}