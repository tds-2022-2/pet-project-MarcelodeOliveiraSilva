package marcelo.silva.jogos.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import marcelo.silva.jogos.domain.Jogo;
import marcelo.silva.jogos.service.JogoService;

import java.net.URI;
import java.util.ArrayList;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;



@RestController // Spring/Injeção de Dependência
@RequestMapping(
    path = "/api/jogos/", 
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class JogoController {

    private final JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    @PostMapping
    public ResponseEntity<?> newJogo(@RequestBody Jogo jogo) {

        Jogo newJogo = jogoService.newJogo(jogo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newJogo.getId()).toUri();

        return ResponseEntity.status(HttpStatus.CREATED).header("Location", uri.toString()).build();

    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateJogo(
        @PathVariable(value = "id") UUID id,
        @RequestBody Jogo jogo) {

            jogoService.updateJogo(id, jogo);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> findJogo(@PathVariable("id") UUID id) {

        Jogo jogo = jogoService.find(id);

        if (jogo != null) {
            return ResponseEntity.ok(jogo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJogo (@PathVariable(value = "id") UUID id) {

        try {
            jogoService.deleteJogo(id);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    public ArrayList<Jogo> listJogos() {
        
        return jogoService.listJogos();

    }
    
}
