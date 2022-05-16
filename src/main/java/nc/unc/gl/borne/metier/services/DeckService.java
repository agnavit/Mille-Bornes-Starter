package nc.unc.gl.borne.metier.services;

import nc.unc.gl.borne.metier.classes.Deck;
import nc.unc.gl.borne.metier.classes.carte.Carte;
import nc.unc.gl.borne.metier.classes.Joueur;
public class DeckService {

    static final int TAILLE_MAX = 7;
    static final int TAILLE_MIN = 6;

    public Deck ajouter(Carte carteAjoute, Joueur joueur){
        if(joueur.getMain().getMainJoueur().size() == 7){
            throw new IllegalArgumentException("Erreur: la main d'un joueur peut contenir au plus " + TAILLE_MAX + " cartes!");
        }
        joueur.getMain().getMainJoueur().add(carteAjoute);
        return joueur.getMain();
    }

    public Deck enlever(Carte carteEnlever, Joueur joueur){
        if(joueur.getMain().getMainJoueur().size() == 6){
            throw new IllegalArgumentException("Erreur: la main d'un joueur peut contenir au moins " + TAILLE_MIN + " cartes!");
        }
        joueur.getMain().getMainJoueur().remove(carteEnlever);
        return joueur.getMain();
    }

    public boolean contains(Carte carteAChercher, Joueur joueur){
        for (Carte carte : joueur.getMain().getMainJoueur()) {
            if (carte.equals(carteAChercher)) {
                return true;
            }
        }
        return false;
    }
}
