package nc.unc.gl.borne.partie;

import lombok.Data;
import nc.unc.gl.borne.Observer;
import nc.unc.gl.borne.carte.Carte;
import nc.unc.gl.borne.carte.PileCarte;
import nc.unc.gl.borne.carte.TypePile;
import nc.unc.gl.borne.jeuComplet.JeuComplet;
import nc.unc.gl.borne.joueur.Joueur;

import java.util.ArrayList;
import java.util.List;

@Data
public class Partie {

    private final List<Observer> observers = new ArrayList<>();

    public void addObserveur(Observer obs) {
        this.observers.add(obs);
    }

    public void removeObserveur(Observer obs) {
        this.observers.remove(obs);
    }

    private ArrayList<Joueur> listejoueur = new ArrayList<Joueur>();
    private int nbJoueurMax;
    private PileCarte pioche;
    private PileCarte defausse;
    private int id;
    private JeuComplet jeuComplet;

    public Partie(){
    }

    public Partie(ArrayList<Joueur> listejoueur,int nbJoueurMax, int id){
        this.listejoueur = listejoueur;
        this.nbJoueurMax = nbJoueurMax;
        this.pioche = new PileCarte().melangerPioche();
        this.jeuComplet = new JeuComplet();
        this.defausse = new PileCarte();;
        this.id = id;
    }

    public void creerPartieObserver() {
        this.observers.forEach(obs -> obs.update());
    }

    //ANTHONY **********

    private final List<Observer> observer = new ArrayList<>();
    private final List<Joueur> players = new ArrayList<>();

    public void createParty(Joueur player) {
        System.out.println(player.getPseudo() + " veux créer une partie " + this.hashCode());
        this.players.add(player);
        this.observers.forEach(obs -> obs.update());
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

        this.observers.forEach(obs -> obs.update());
    }

}
