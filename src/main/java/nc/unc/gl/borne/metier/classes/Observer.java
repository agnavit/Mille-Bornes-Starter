package nc.unc.gl.borne.metier.classes;

import nc.unc.gl.borne.metier.classes.partie.Partie;

import java.util.ArrayList;

public interface Observer {

    void update(Partie party);

    void updateListBox(ArrayList<Partie> listParty);

    void updateWindow();
}
