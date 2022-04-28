package nc.unc.gl.borne.partie;

import lombok.Data;
import nc.unc.gl.borne.carte.Carte;
import nc.unc.gl.borne.carte.PileCarte;
import nc.unc.gl.borne.carte.TypePile;
import nc.unc.gl.borne.jeuComplet.JeuComplet;
import nc.unc.gl.borne.joueur.Joueur;

import java.util.ArrayList;

@Data
public class Partie {

    private ArrayList<Joueur> listejoueur = new ArrayList<Joueur>();
    private int nbJoueurMax;
    private ArrayList<Carte> pioche;
    private PileCarte defausse;
    private int id;
    private JeuComplet jeuComplet = new JeuComplet();

    public Partie(ArrayList<Joueur> listejoueur,int nbJoueurMax, PileCarte pioche, PileCarte defausse, int id){
        this.listejoueur = listejoueur;
        this.nbJoueurMax = nbJoueurMax;
        this.pioche = pioche.melangerPioche(jeuComplet);
        this.defausse = defausse;
        this.id = id;
    }

}
