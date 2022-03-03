package nc.unc.gl.borne.joueur;

import nc.unc.gl.borne.carte.*;

import java.util.List;

/**
 * Description d'un joueur
 */
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

    public String getPseudo() {
        return pseudo;
    }


    public int getAge() {
        return age;
    }


    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public Deck getMain() {
        return main;
    }

    public void setMain(Deck main) {
        this.main = main;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void jeter(Carte carteChoisie){
        //TODO
    }

    public void poser(Carte carteChoisie){
        //TODO
    }

    public void attaquer(Carte carteAttaque, Joueur jouerAttaque){
        //TODO
    }
}
