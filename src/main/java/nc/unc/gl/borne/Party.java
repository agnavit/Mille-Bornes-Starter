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

    public void createParty(Joueur player) {
        System.out.println(player.getPseudo() + " veux créer une partie " + this.hashCode());
        this.players.add(player);
        this.observers.forEach(obs -> obs.updateParty(this));
    }

    public void joinParty(String hostPlayer, Joueur guestPlayer) {
        System.out.println(
            guestPlayer.getPseudo() + " rejoint la partie de " + hostPlayer + " " + this.hashCode()
        );
        this.players.add(guestPlayer);
        System.out.println("Lancement partie");
        System.out.println("Liste des joueurs :");
        for (Joueur player: this.players) {
            System.out.println(player.getPseudo());
        }

        this.observers.forEach(obs -> obs.updateParty(this));
    }

    public void leaveParty(){

    }
}
