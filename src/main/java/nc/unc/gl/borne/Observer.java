package nc.unc.gl.borne;

import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.partie.Partie;

import java.util.ArrayList;

public interface Observer {

    void update(Partie party);

    void updateListBox(ArrayList<Partie> listParty);

    void updateWindow(Joueur player);
}
