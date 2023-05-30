package com.utfpr.sdleilao.resource;

import com.utfpr.sdleilao.entities.*;
import com.utfpr.sdleilao.services.Servidor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import static org.springframework.http.codec.ServerSentEvent.builder;

/*
    /leilao -> POST = Criar Leilão, GET = Listar Leilões
    /leilao/id -> POST = Dar lance
    /cliente -> POST = Cadastrar cliente
*/

@CrossOrigin
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class LeilaoResource {

    private final Servidor servidor;

    public LeilaoResource(Servidor servidor) {
        this.servidor = servidor;
    }

    @GetMapping(path="/leilao")
    public ResponseEntity<ArrayList<Leilao>> listar() {

        System.out.println("Requisição recebida: Listar Leilões");

        return ResponseEntity.ok().body(servidor.listarLeiloes());
    }

    @PostMapping(path="/cliente")
    public ResponseEntity<Cliente> cadastrar(@RequestBody Cliente cliente) {

        System.out.println("Requisição recebida: Cadastrar Cliente");
        System.out.println("Body da requisição: " + cliente);

        Cliente result = servidor.cadastrarUsuario(cliente);
        if (result != null) {
            return ResponseEntity.ok().body(result);
        }
        else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping(path="/leilao/{idLeilao}")
    public ResponseEntity<Lance> darLance(@PathVariable Integer idLeilao, @RequestBody Lance lance) {

        System.out.println("Requisição recebida: Dar Lance");
        System.out.println("Body da requisição:");
        System.out.println("idLeilao: " + idLeilao);
        System.out.println("Lance: " + lance);

        boolean result = servidor.darLance(idLeilao, lance);
        if (result) {
            return ResponseEntity.ok().body(lance);
        }
        else {
            return ResponseEntity.badRequest().body(lance);
        }
    }

    @PostMapping(path="/leilao")
    public ResponseEntity<ResponseData> inserir(@RequestBody DadosLeilao dadosLeilao) {

        System.out.println("Requisição recebida: Inserir Leilão");
        System.out.println("Body da requisição:");
        System.out.println("dadosLeilao: " + dadosLeilao);

        Cliente criador = dadosLeilao.getCriador();
        Produto produto = dadosLeilao.getProduto();
        Integer duracao = dadosLeilao.getDuracao();

        Leilao leilao = servidor.criarLeilao(criador, produto, duracao);

        if (leilao != null) {
            return ResponseEntity.ok().body(
                    new ResponseData<>("Success", leilao.getLeilaoItem())
            );
        }
        else {
            return ResponseEntity.internalServerError().body(
                    new ResponseData<>("Failure", "Houve um problema ao cadastrar o leilão")
            );
        }
    }

    /*
    @GetMapping(path="/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamFlux() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> "Flux - " + LocalTime.now().toString());
    }

    @GetMapping(path="/stream-sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<String>> streamEvents() {
        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data("SSE - " + LocalTime.now().toString())
                        .build());
    }

     */
}

class ResponseData<T> {
    private final String status;
    private final T data;

    ResponseData(String status, T data) {
        this.status = status;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}