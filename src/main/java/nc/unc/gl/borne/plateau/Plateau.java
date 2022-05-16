package nc.unc.gl.borne.plateau;

import nc.unc.gl.borne.carte.PileCarte;
import nc.unc.gl.borne.carte.enumerations.TypePile;

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
    private HashMap<TypePile, PileCarte> plateau;

    public Plateau() {
        this.plateau = new HashMap<>();
        this.plateau.put(TypePile.VITESSE, new PileCarte());
        this.plateau.put(TypePile.BATAILLE, new PileCarte());
        this.plateau.put(TypePile.BORNES, new PileCarte());
        this.plateau.put(TypePile.BOTTES, new PileCarte());
    }

    public HashMap<TypePile, PileCarte> getPlateau() {
        return this.plateau;
    }

    public PileCarte getPile(TypePile cle){
        if (!plateau.containsKey(cle)){
            throw new IllegalArgumentException("Erreur : la clé entrée n'est pas présente dans le plateau!");
        }
        return this.plateau.get(cle);
    }

    @Override
    public String toString() {
        return "Plateau{" +
            "plateau=" + plateau +
            '}';
    }
}
