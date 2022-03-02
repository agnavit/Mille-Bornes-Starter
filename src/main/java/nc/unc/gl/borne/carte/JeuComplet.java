package nc.unc.gl.borne.carte;

import java.util.ArrayList;

public class JeuComplet {

    private ArrayList<Carte> jeuComplet;


    public JeuComplet(ArrayList<Carte> jeuComplet) {
        this.jeuComplet = jeuComplet;
    }

    public ArrayList<Carte> getJeuComplet() {
        return jeuComplet;
    }

    public void setJeuComplet(ArrayList<Carte> jeuComplet) {
        this.jeuComplet = jeuComplet;
    }
}
