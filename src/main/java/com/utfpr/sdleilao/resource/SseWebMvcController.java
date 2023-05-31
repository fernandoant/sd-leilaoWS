package com.utfpr.sdleilao.resource;

import com.utfpr.sdleilao.entities.Cliente;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import static org.springframework.web.servlet.mvc.method.annotation.SseEmitter.event;
@CrossOrigin(origins = "*")
@RestController
public class SseWebMvcController {
    private static HashMap<String, SseEmitter> emissores = new HashMap<String, SseEmitter>();
    @GetMapping(path="/sse/{prefix}", produces= MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter createConnection(@PathVariable String prefix){
        SseEmitter newEmitter = new SseEmitter(-1L);
        emissores.put(prefix,newEmitter);
        System.out.println(newEmitter);
        System.out.println(prefix);
        return newEmitter;
    }

    public void addEmitter(Cliente cliente)
    {
        emissores.put(cliente.getNome(),cliente.getEmissor());
    }
    public boolean getEmitter(Cliente cliente)
    {
        boolean emissorCadastrado = emissores.containsKey(cliente.getNome());
        if(emissorCadastrado)
        {
            SseEmitter emissor = emissores.get(cliente.getNome());
            cliente.setEmissor(emissor);
        }
        return emissorCadastrado;
    }
    public void sendEvents(Cliente cliente, String evento, Object msg){
        SseEmitter emissor = cliente.getEmissor();
        if(emissor == null) return;
        SseEmitter.SseEventBuilder objEvento;
        if(evento.equals("criarLeilao"))
        {
            objEvento = event().name(evento).data(msg,MediaType.APPLICATION_JSON);
        } else {
            objEvento = event().name(evento).data(msg);
        }

        try {
            emissor.send(objEvento);

        }catch(Exception e)
        {
            emissores.remove(cliente.getNome());
            emissor.completeWithError(e);
            System.out.println("Cliente: " +  cliente.getNome() + " -> Exception: " +  e);
        }
    }

}
