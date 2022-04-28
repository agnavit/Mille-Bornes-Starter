package nc.unc.gl.borne;

import lombok.Data;
import nc.unc.gl.borne.carte.TypePile;
import nc.unc.gl.borne.joueur.Joueur;

import java.util.ArrayList;

@Data
public class Partie {

    private ArrayList<Joueur> liste_joueur = new ArrayList<Joueur>();
    private int nb_Joueur_Max;
    private TypePile pioche;
    private TypePile defausse;
    private int id;

    public Partie(ArrayList<Joueur> liste_joueur, TypePile pioche, TypePile defausse, int id){
        this.liste_joueur = liste_joueur;
        this.pioche = pioche;
        this.defausse = defausse;
        this.id = id;
    }

}
