package nc.unc.gl.borne.joueur;

import lombok.Data;
import nc.unc.gl.borne.Deck.Deck;
import nc.unc.gl.borne.plateau.Plateau;

@Data
public class Joueur {

    private int id;
    private String pseudo;
    private int age;
    private Plateau plateau;
    private Deck main;
    private int score;

    public Joueur() {
    }

    public Joueur(int id, String pseudo, int age) {
        this.id = id;
        this.pseudo = pseudo;
        this.age = age;
        this.plateau = new Plateau();
        this.score = 0;
        this.main = new Deck();
    }
}
