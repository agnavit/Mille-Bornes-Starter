package nc.unc.gl.borne.carte;

import java.util.ArrayList;

public class Deck {

    public int minimumSize;
    public ArrayList<Carte> deckPlayer;

    public Deck(ArrayList<Carte> deckPlayer){
        this.deckPlayer = deckPlayer;
    }

    public void setDeckPlayer(ArrayList<Carte> deckPlayer) {
        this.deckPlayer = deckPlayer;
    }

    public ArrayList<Carte> getDeckPlayer() {
        return deckPlayer;
    }
}
