package nc.unc.gl.borne.carte;

import java.util.HashMap;

public class Plateau {

    /**
     * Plateau d'un joueur
     * Chaque clé de dictionnaire correspond au nom de la pile
     *      - pile vitesse
     *      - pile bataille
     *      - pile bornes
     *      - pile bottes
     * Et la valeur correspond à la pile de carte associée
     */
    private HashMap<String, PileCarte> plateau;

    /**
     * Constructeur
     *
     * NB: Créer chaque clé dans le constructeur et initialiser la pile associée à vide
     */
    public Plateau() {
        this.plateau = new HashMap<>();
    }

    public HashMap<String, PileCarte> getPlateau() {
        return plateau;
    }

    public void setPlateau(HashMap<String, PileCarte> plateau) {
        this.plateau = plateau;
    }
}
