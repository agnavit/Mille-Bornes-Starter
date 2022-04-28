package nc.unc.gl.borne.partie;

import lombok.Data;
import nc.unc.gl.borne.carte.PileCarte;
import nc.unc.gl.borne.carte.TypePile;
import nc.unc.gl.borne.joueur.Joueur;

import java.util.ArrayList;

@Data
public class Partie {

    private ArrayList<Joueur> listejoueur = new ArrayList<Joueur>();
    private int nbJoueurMax;
    private PileCarte pioche;
    private PileCarte defausse;
    private int id;

    public Partie(ArrayList<Joueur> listejoueur, PileCarte pioche, PileCarte defausse, int id){
        this.listejoueur = listejoueur;
        this.pioche = pioche;
        this.defausse = defausse;
        this.id = id;
        //TODO mélanger la pioche
    }

}
