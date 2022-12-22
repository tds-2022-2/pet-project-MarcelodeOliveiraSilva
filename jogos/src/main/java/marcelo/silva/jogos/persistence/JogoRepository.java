package marcelo.silva.jogos.persistence;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import marcelo.silva.jogos.domain.Jogo;


@Repository
public class JogoRepository {


    ArrayList<Jogo> jogos = new ArrayList<>();

    public void save(UUID id, Jogo jogo) {
        this.delete(id);
        jogos.add(jogo);
    }

    public ArrayList<Jogo> findAll() {
        return jogos;
    }

    public Jogo findById(UUID id) {
        for (Jogo m : jogos) {
            if(m.getId().equals(id))
                return m;
        }
        return null;
    }

    public void delete(UUID id) {
        for (int i = 0; i < jogos.size(); i++) {
            if(jogos.get(i).getId().equals(id)){
                jogos.remove(i);
            }
        }
    }
    
}
