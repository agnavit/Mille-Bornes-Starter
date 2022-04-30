package nc.unc.gl.borne.partie;

import lombok.Data;
import nc.unc.gl.borne.Observer;
import nc.unc.gl.borne.carte.Carte;
import nc.unc.gl.borne.carte.PileCarte;
import nc.unc.gl.borne.carte.TypePile;
import nc.unc.gl.borne.jeuComplet.JeuComplet;
import nc.unc.gl.borne.joueur.Joueur;

import java.util.ArrayList;
import java.util.List;

@Data
public class Partie {

    private final List<Observer> observers = new ArrayList<>();

    public void addObserveur(Observer obs) {
        this.observers.add(obs);
    }

    public void removeObserveur(Observer obs) {
        this.observers.remove(obs);
    }

    private ArrayList<Joueur> listejoueur = new ArrayList<Joueur>();
    private int nbJoueurMax;
    private PileCarte pioche;
    private PileCarte defausse;
    private int id;
    private JeuComplet jeuComplet;

    public Partie(){
    }

    public Partie(ArrayList<Joueur> listejoueur,int nbJoueurMax, int id){
        this.listejoueur = listejoueur;
        this.nbJoueurMax = nbJoueurMax;
        this.pioche = new PileCarte().melangerPioche();
        this.jeuComplet = new JeuComplet();
        this.defausse = new PileCarte();;
        this.id = id;
    }

    public void creerPartieObserver(Joueur joueur) {
        ArrayList<Joueur> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(joueur);
        Partie partie = new Partie(listeJoueurs, 2, 1);
        this.observers.forEach(obs -> obs.update(partie));
    }
}
