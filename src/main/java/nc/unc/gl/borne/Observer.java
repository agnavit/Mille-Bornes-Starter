package nc.unc.gl.borne;

import nc.unc.gl.borne.partie.Partie;

import java.util.ArrayList;

public interface Observer {

    void update(Partie partie);

    void updateListBox(ArrayList<Partie> listePartie);
}
