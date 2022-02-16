package nc.unc.gl.borne;

import java.util.List;

/**
 * 
 * Ensemble de carte (une main, une pioche...)
 *
 */
public class CompositionCarte {

	/**
	 * un jeu est un essemble de carte
	 */
	public List<Carte> jeu;
	
	/**
	 * la taille de jeu
	 */
	public int taille;
	
	/**
	 * Constructeur
	 * @param jeu, une liste de carte
	 * @param taille, la taille du jeu
	 */
	public CompositionCarte(List<Carte> jeu, int taille) {
		this.jeu = jeu;
		this.taille = taille;
	}
	
	/**
	 * Permet d'enlever une carte dans un jeu
	 * @param carte
	 */
	public void enlever(Carte carte) {
		this.jeu.remove(carte);
	}
	
	/**
	 * Permet d'ajouter une carte dans un jeu
	 * @param carte
	 */
	public void ajouter(Carte carte) {
		this.jeu.add(carte);
	}
	
}
