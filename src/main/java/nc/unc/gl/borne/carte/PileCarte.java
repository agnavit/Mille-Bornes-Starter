package nc.unc.gl.borne.carte;

import java.util.ArrayList;

public class PileCarte {

    /**
     * Ensemble de carte représentée dans une liste, implémentée sous forme de pile
     *  sortie <-  [ 0 - - - - - (len-1) ]  <- entrée
     */
    private ArrayList<Carte> pileCarte;

    public PileCarte() {
        this.pileCarte = new ArrayList<>();
    }

    public ArrayList<Carte> getPileCarte() {
        return pileCarte;
    }

    public void setPileCarte(ArrayList<Carte> pileCarte) {
        this.pileCarte = pileCarte;
    }

    public void ajouter(Carte carteChoisie){
        //TODO
    }

    public void enlever(){
        //TODO
    }

}
