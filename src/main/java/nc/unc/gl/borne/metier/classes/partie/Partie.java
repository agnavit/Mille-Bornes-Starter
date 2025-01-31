package nc.unc.gl.borne.metier.classes.partie;

import lombok.Data;
import nc.unc.gl.borne.MilleBornesApplication;
import nc.unc.gl.borne.metier.classes.Observer;
import nc.unc.gl.borne.metier.classes.carte.PileCarte;
import nc.unc.gl.borne.metier.classes.Joueur;
import nc.unc.gl.borne.metier.services.PartieService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Data
public class Partie {

    private final List<Observer> observers = new CopyOnWriteArrayList<>();
    int i = 0;
    private ArrayList<Joueur> listejoueur = new ArrayList<Joueur>();
    private int nbJoueurMax;
    private PileCarte pioche;
    private PileCarte defausse;
    private String id;
    public PartieService partieService = new PartieService();

    public void addObserveur(Observer obs) {
        this.observers.add(obs);
    }

    public void removeObserveur(Observer obs) {
        this.observers.remove(obs);
    }

    public Partie(){
    }

    public Partie(ArrayList<Joueur> listejoueur,int nbJoueurMax, String id){
        this.listejoueur = listejoueur;
        this.nbJoueurMax = nbJoueurMax;
        this.pioche = new PileCarte();
        this.defausse = new PileCarte();
        this.id = id;
    }

    public Partie creerPartieObserver(Joueur joueur) {
        ArrayList<Joueur> listeJoueurs = new ArrayList<>();
        listeJoueurs.add(joueur);
        Partie partie = new Partie(listeJoueurs, 2, String.valueOf(i));
        i+=1;
        MilleBornesApplication.addPartieList(partie);
        this.observers.forEach(obs -> obs.update(partie));
        return partie;
    }

    public void suppPartieObserver(Joueur joueur, ArrayList<Partie> listePartie) {
        for (Partie partie : listePartie) {
            if (partie.getListejoueur().get(0).getPseudo().equals(joueur.getPseudo())) {
                listePartie.remove(partie);
                break;
            }
        }
        this.observers.forEach(obs -> obs.updateListBox(listePartie));
    }

    public void modifFenetreLancementPartie() {
        this.observers.forEach(obs -> obs.updateWindow());
    }
}
