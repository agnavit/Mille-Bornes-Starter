package nc.unc.gl.borne.metier.classes.partie;

import nc.unc.gl.borne.metier.classes.ObserverGame;
import nc.unc.gl.borne.metier.classes.Joueur;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Game {

    Joueur j1;
    Joueur j2;

    public Game() {
    }

    private final List<ObserverGame> observersPartie = new CopyOnWriteArrayList<>();

    public void addObserveur(ObserverGame obsPartie) {
        this.observersPartie.add(obsPartie);
    }

    public void removeObserveur(ObserverGame obsPartie) {
        this.observersPartie.remove(obsPartie);
    }

    public void updateFenetreEnGame() {
        this.observersPartie.forEach(obs -> obs.updateWindowEnGame());
    }

    public void updateWinner(Joueur player) {
        this.observersPartie.forEach(obs -> obs.updateWinner(player));
    }
}
