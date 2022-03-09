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
    private HashMap<TypePile, PileCarte> plateau;

    /**
     * Constructeur
     *
     * NB: Créer chaque clé dans le constructeur et initialiser la pile associée à vide
     */
    public Plateau() {
        this.plateau = new HashMap<>();
        this.plateau.put(TypePile.VITESSE, new PileCarte());
        this.plateau.put(TypePile.BATAILLE, new PileCarte());
        this.plateau.put(TypePile.BORNES, new PileCarte());
        this.plateau.put(TypePile.BOTTES, new PileCarte());
    }

    public HashMap<TypePile, PileCarte> getPlateau() {
        return plateau;
    }

    public void setPlateau(HashMap<TypePile, PileCarte> plateau) {
        this.plateau = plateau;
    }


    public PileCarte getPile(TypePile cle){
        if (!plateau.containsKey(cle)){
            throw new IllegalArgumentException("Erreur : la clé entrée n'est pas présente dans le plateau!");
        }
        return this.plateau.get(cle);
    }

    public Plateau ajouterCartePlateau(TypePile cle, Carte carte){
        this.getPile(cle).empiler(carte);
        return this;
    }

    public Carte enleverCartePlateau(TypePile cle){
        return this.getPile(cle).depiler();
    }

    public int getTaillePile(TypePile cle){
        return this.getPile(cle).taille();
    }

    public void enleverCartesAttaqueEtParadePile(TypePile cle,PileCarte defausse){
        // On enleve deux fois : pour enlever la carte attaque et la carte parade
        for(int i=0; i<2; i++){
            defausse.empiler(this.getPile(cle).depiler());
        }
    }

    @Override
    public String toString() {
        return "Plateau{" +
            "plateau=" + plateau +
            '}';
    }
}
