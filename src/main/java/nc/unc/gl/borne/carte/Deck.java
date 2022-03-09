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

    /**
     * Main d'un joueur
     */
    private ArrayList<Carte> mainJoueur;

    /**
     * Constructeur
     * @param mainJoueur la main
     */
    public Deck(ArrayList<Carte> mainJoueur) {
        if(mainJoueur.size() != 6){
            throw new IllegalArgumentException("Erreur: la main d'un joueur doit contenir " + TAILLE_MIN + " cartes!");
        }
        this.mainJoueur = mainJoueur;
    }

    public ArrayList<Carte> getMainJoueur() {
        return mainJoueur;
    }

    public void setMainJoueur(ArrayList<Carte> mainJoueur) {
        this.mainJoueur = mainJoueur;
>>>>>>> Ajout Deck, JeuComplet, PileCarte
    }

    /**
     * Ajouter une carte
     * @param carteAjoute la carte à ajouter
     */
    public void ajouter(Carte carteAjoute){
        this.mainJoueur.add(carteAjoute);
    }

    /**
     * Enelver une carte
     * @param carteEnlever la carte à enlever
     */
    public void enlever(Carte carteEnlever){
        this.mainJoueur.remove(carteEnlever);
    }
}
