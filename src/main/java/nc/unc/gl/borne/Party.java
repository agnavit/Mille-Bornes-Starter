package nc.unc.gl.borne;

import nc.unc.gl.borne.joueur.Joueur;

import java.util.ArrayList;
import java.util.List;

public class Party {

    private final List<Observer> observers = new ArrayList<>();
    private final List<Joueur> players = new ArrayList<>();

    public void addObserver(Observer obs) {
        this.observers.add(obs);
    }

    public void removeObserver(Observer obs) {
        this.observers.remove(obs);
    }

    public void createParty() {
        System.out.println("Un utilisateur veux jouer " + this.hashCode());
        this.observers.forEach(obs -> obs.updatePartie(this));
    }

    public void joinParty() {
        System.out.println("Un utilisateur veux rejoindre une partie " + this.hashCode());
        this.observers.forEach(obs -> obs.updatePartie(this));
    }

    public void leaveParty(){

    }
}
