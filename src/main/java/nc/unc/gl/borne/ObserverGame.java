package nc.unc.gl.borne;

import nc.unc.gl.borne.partie.Partie;

public interface ObserverGame {

    void updateWindowParty(String idPartie);

    void updatePoser(Partie partie);

    void updateJeter(Partie partie);

    void updateScore(Partie partie);

}
