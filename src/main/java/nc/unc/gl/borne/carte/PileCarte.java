package nc.unc.gl.borne.carte;

import java.util.Stack;

public class PileCarte {

    private Stack<Carte> pileCarte;

    public PileCarte(Stack<Carte> pileCarte) {
        this.pileCarte = pileCarte;
    }

    public Stack<Carte> getPileCarte() {
        return pileCarte;
    }

    public void setPileCarte(Stack<Carte> pileCarte) {
        this.pileCarte = pileCarte;
    }
}
