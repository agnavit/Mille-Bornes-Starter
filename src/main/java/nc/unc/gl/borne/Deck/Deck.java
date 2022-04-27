package nc.unc.gl.borne.Deck;

import lombok.Data;
import nc.unc.gl.borne.carte.Carte;

import java.util.ArrayList;

@Data
public class Deck {

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

    @Override
    public String toString() {
        return "Deck{" +
            "mainJoueur=" + mainJoueur +
            '}';
    }
}
