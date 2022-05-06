package nc.unc.gl.borne.Deck;

import lombok.Data;
import nc.unc.gl.borne.carte.Carte;

import java.util.ArrayList;

@Data
public class Deck {
    private ArrayList<Carte> mainJoueur;

    public Deck() {
        mainJoueur = new ArrayList<Carte>();
    }

    public int getTaille(){
        return mainJoueur.size();
    }

    @Override
    public String toString() {
        return "Deck{" +
            "mainJoueur=" + mainJoueur +
            '}';
    }
}
