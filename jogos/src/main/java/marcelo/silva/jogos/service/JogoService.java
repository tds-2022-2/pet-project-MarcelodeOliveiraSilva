package marcelo.silva.jogos.service;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Service;

import marcelo.silva.jogos.domain.Jogo;
import marcelo.silva.jogos.persistence.JogoRepository;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;

    public JogoService(JogoRepository jogoRepository) {
        this.jogoRepository = jogoRepository;
    }
    
    public Jogo newJogo(Jogo newJogo) {

        jogoRepository.save(null, newJogo);

        return newJogo;
    }

    public void deleteJogo(UUID id) {

        Jogo m = this.find(id);
        if(m==null)
            throw new IllegalArgumentException("Jogo nao encontrado!");
        
            jogoRepository.delete(id);

    }   

    public void updateJogo(UUID id, Jogo jogo) {

        if(this.find(id)==null)
            throw new IllegalArgumentException("Jogo nao encontrado!");

        jogoRepository.save(id, jogo);
    } 

    public Jogo find(UUID id) {
        return jogoRepository.findById(id);
    }

    public ArrayList<Jogo> listJogo() {
        
        return jogoRepository.findAll();
    }


}
