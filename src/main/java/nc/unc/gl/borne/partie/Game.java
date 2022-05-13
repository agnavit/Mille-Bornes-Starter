package nc.unc.gl.borne.partie;

import nc.unc.gl.borne.ObserverGame;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public Game() {

    }

    private final List<ObserverGame> observersPartie = new ArrayList<>();

    public void addObserveurPartie(ObserverGame obsPartie) {
        this.observersPartie.add(obsPartie);
    }

    public void removeObserveurPartie(ObserverGame obsPartie) {
        this.observersPartie.remove(obsPartie);
    }

    public void getAllPlayer(String idPartie) {
        this.observersPartie.forEach(obs -> obs.updateWindowParty(idPartie));
    }
}
