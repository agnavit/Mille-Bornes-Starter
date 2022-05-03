package nc.unc.gl.borne;

import nc.unc.gl.borne.partie.Partie;
import nc.unc.gl.borne.views.GameView;

import java.util.ArrayList;

public interface Observer {

    void update(Partie partie);

    void updateListBox(ArrayList<Partie> listePartie);

    void updateFenetre(GameView gameView);
}
