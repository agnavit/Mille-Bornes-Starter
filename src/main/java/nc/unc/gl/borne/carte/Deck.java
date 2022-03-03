package nc.unc.gl.borne.carte;

import java.util.ArrayList;

public class Deck {

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
    }
}
