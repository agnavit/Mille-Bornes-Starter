package nc.unc.gl.borne.carte;

import java.util.ArrayList;

public class Deck {

<<<<<<< refs/remotes/origin/main
    public int minimumSize;
    public ArrayList<Carte> deckPlayer;

    public Deck(ArrayList<Carte> deckPlayer){
        this.deckPlayer = deckPlayer;
    }

    public void setDeckPlayer(ArrayList<Carte> deckPlayer) {
        this.deckPlayer = deckPlayer;
    }

    public ArrayList<Carte> getDeckPlayer() {
        return deckPlayer;
=======
    static final int TAILLE_MIN = 6;
    static final int TAILLE_MAX = 7;

    private ArrayList<Carte> mainJoueur;


    /**
     * Constructeur
     * @param mainJoueur la main
     */
    public Deck(ArrayList<Carte> mainJoueur) {
        this.mainJoueur = mainJoueur;
    }

    public ArrayList<Carte> getMainJoueur() {
        return mainJoueur;
    }

    public void setMainJoueur(ArrayList<Carte> mainJoueur) {
        this.mainJoueur = mainJoueur;
>>>>>>> Ajout Deck, JeuComplet, PileCarte
    }
}
