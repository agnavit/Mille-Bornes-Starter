package nc.unc.gl.borne.carte;

import java.util.ArrayList;

public class Deck {

    static final int TAILLE_MAX = 7;
    static final int TAILLE_MIN = 6;

    /**
     * Main d'un joueur
     */
    private ArrayList<Carte> mainJoueur;

    /**
     * Constructeur vide
     */
    public Deck() {
        mainJoueur = new ArrayList<Carte>();
    }

    public ArrayList<Carte> getMainJoueur() {
        return mainJoueur;
    }

    public void setMainJoueur(ArrayList<Carte> mainJoueur) {
        this.mainJoueur = mainJoueur;
    }

    /**
     * Ajouter une carte
     * @param carteAjoute la carte à ajouter
     */
    public Deck ajouter(Carte carteAjoute){
        if(mainJoueur.size() == 7){
            throw new IllegalArgumentException("Erreur: la main d'un joueur ne doit pas contenir au plus " + TAILLE_MAX + " cartes!");
        }
        this.mainJoueur.add(carteAjoute);
        return this;
    }

    /**
     * Enlever une carte
     * @param carteEnlever la carte à enlever
     */
    public Deck enlever(Carte carteEnlever){
        if(mainJoueur.size() == 6){
            throw new IllegalArgumentException("Erreur: la main d'un joueur doit contenir au moins " + TAILLE_MIN + " cartes!");
        }
        this.mainJoueur.remove(carteEnlever);
        return this;
    }

    public int taille(){
        return this.mainJoueur.size();
    }

    public boolean contains(Carte carteAChercher){
        for (Carte carte : this.mainJoueur) {
            if (carte.equals(carteAChercher)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Deck{" +
            "mainJoueur=" + mainJoueur +
            '}';
    }
}
