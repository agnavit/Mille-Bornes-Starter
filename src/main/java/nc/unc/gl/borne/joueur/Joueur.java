package nc.unc.gl.borne.joueur;

import lombok.Data;
import nc.unc.gl.borne.carte.*;
import nc.unc.gl.borne.plateau.Plateau;

/**
 * Description d'un joueur
 */
@Data
public class Joueur {

    /**
     * Identifiant
     */
    private int id;

    /**
     * Pseudo
     */
    private String pseudo;

    /**
     * Âge
     */
    private int age;

    /**
     * Plateau représentant les cartes qui sont posées devant le joueur
     */
    private Plateau plateau;

    /**
     * Les cartes que le joueur a en main
     */
    private Deck main;

    /**
     * Le score
     */
    private int score;

    /**
     * Constructeur
     * @param id id
     * @param pseudo pseudo
     * @param age age
     */
    public Joueur(int id, String pseudo, int age) {
        this.id = id;
        this.pseudo = pseudo;
        this.age = age;
        this.plateau = new Plateau();
        this.score = 0;
    }

}
