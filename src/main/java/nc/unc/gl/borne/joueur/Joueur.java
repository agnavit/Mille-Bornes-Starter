package nc.unc.gl.borne.joueur;

import nc.unc.gl.borne.carte.Carte;
import nc.unc.gl.borne.carte.PileCarte;
import nc.unc.gl.borne.carte.TypeCarte;

import java.util.List;

public class Joueur {
	
	/**
	 * id d'un Joueur
	 */
	public int id;
	
	/**
	 * Pseudo d'un Joueur
	 */
	public String pseudo;
	
	/**
	 * Âge d'un Joueur
	 */
	public int age;
	
	/**
	 * Plateau représentant les cartes qui sont posées devant le Joueur 
	 */
	public List<Carte> plateau;
	
	/**
	 * Les cartes que le Joueur a en main
	 */
	public List<Carte> main;
	
	/**
	 * Contruteur Joueur
	 * @param pseudo
	 * @param age
	 */
	public Joueur(String pseudo, int age) {
		this.pseudo = pseudo;
		this.age = age;
	}
	
	public void setMain(List<Carte> main) {
		this.main = main;
	}
	
	public void setPlateau(List<Carte> plateau) {
		this.plateau = plateau;
	}
	
	/**
	 * A discuter avec l'équipe :
	 * Ne serait-il pas plus simple de mettre les méthode piocher(), jeter() 
	 * et poser() dans CompositionCarte ?
	 */
	public void piocher() {
		
	}
	
	public void jeter() {
		
	}
	
	public void poser() {
		
	}
	
	/**
	 * Permet à un joueur d'attaquer un autre joueur
	 * @param joueur
	 * @param carte
	 * @return true si la carte est de type attaque false sinon
	 */
	public boolean attaquer(Joueur joueur, Carte carte) {
		if (carte.type != TypeCarte.ATTAQUE) {
			return false;
		}
		joueur.plateau.add(carte);
		return true;
	}
	
}
