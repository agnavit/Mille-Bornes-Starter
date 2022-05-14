package nc.unc.gl.borne.partie;

import lombok.Data;
import nc.unc.gl.borne.MilleBornesApplication;
import nc.unc.gl.borne.Observer;
import nc.unc.gl.borne.carte.PileCarte;
import nc.unc.gl.borne.joueur.Joueur;

import java.util.ArrayList;
import java.util.List;

@Data
public class Partie {

    private final List<Observer> observers = new ArrayList<>();
    private ArrayList<Joueur> players = new ArrayList<Joueur>();
    private int maxPlayers;
    private PileCarte pioche;
    private PileCarte defausse;
    private String id;
    
    public PartieService partieService = new PartieService();

    int i = 0;

    public Partie(){
    }

    public Partie(ArrayList<Joueur> players, int maxPlayers, String id){
        this.players = players;
        this.maxPlayers = maxPlayers;
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

            if (partie.getPlayers().get(0).getPseudo().equals(joueur.getPseudo())) {
                listePartie.remove(partie);
                break;
            }
        }
        this.observers.forEach(obs -> obs.updateListBox(listePartie));
    }

    public void modifFenetreLancementPartie() {
        this.observers.forEach(obs -> obs.updateWindow());
    }

    public void addObserver(Observer obs) {
        this.observers.add(obs);
    }

    public void removeObserver(Observer obs) {
        this.observers.remove(obs);
    }


}
