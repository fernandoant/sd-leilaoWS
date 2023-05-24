package com.utfpr.sdleilao.resource;

import com.utfpr.sdleilao.entities.Cliente;
import com.utfpr.sdleilao.entities.Lance;
import com.utfpr.sdleilao.entities.Leilao;
import com.utfpr.sdleilao.entities.Produto;
import com.utfpr.sdleilao.services.Servidor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path="/leilao")
public class LeilaoResource {

    private final Servidor servidor;

    public LeilaoResource(Servidor servidor) {
        this.servidor = servidor;
    }

    @GetMapping
    public ResponseEntity<ArrayList<Leilao>> listar() {
        return ResponseEntity.ok().body(servidor.listarLeiloes());
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {
        boolean result = servidor.cadastrarUsuario(cliente);
        if (result) {
            return ResponseEntity.ok().body(cliente);
        }
        else {
            return ResponseEntity.badRequest().body(cliente);
        }
    }

    @PostMapping
    public ResponseEntity<Lance> darLance(@RequestBody Integer idLeilao, @RequestBody Lance lance) {
        boolean result = servidor.darLance(idLeilao, lance);
        if (result) {
            return ResponseEntity.ok().body(lance);
        }
        else {
            return ResponseEntity.badRequest().body(lance);
        }
    }

    @PostMapping
    public ResponseEntity<Leilao> inserir(@RequestBody Cliente criador, @RequestBody Produto produto, @RequestBody Integer duracao) {
        boolean result = servidor.criarLeilao(criador, produto, duracao);
        if (result) {
            return ResponseEntity.ok().body(new Leilao());
        }
        else {
            return ResponseEntity.badRequest().body(new Leilao());
        }
    }

}
