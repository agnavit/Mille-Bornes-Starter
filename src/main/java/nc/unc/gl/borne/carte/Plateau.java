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

    // ---------- A-t-on besoin de getter et setter ici ? ----------

    /**
     * Getter du plateau
     * @return plateau
     */
    public HashMap<TypePile, PileCarte> getPlateau() {
        return plateau;
    }

    /**
     * Setter du plateau
     * @param plateau le nouveau plateau
     */
    public void setPlateau(HashMap<TypePile, PileCarte> plateau) {
        this.plateau = plateau;
    }


    /**
     * Récupère une pile du plateau
     * @param cle la clé de la pile à récupérer
     * @return la pile voulue
     */
    public PileCarte getPile(TypePile cle){
        if (!plateau.containsKey(cle)){
            throw new IllegalArgumentException("Erreur : la clé entrée n'est pas présente dans le plateau!");
        }
        return this.plateau.get(cle);
    }

    /**
     * Ajoute une carte au plateau
     * @param cle la clé de la pile à modifier
     * @param carte la carte à ajouter
     * @return le plateau
     */
    public Plateau ajouterCartePlateau(TypePile cle, Carte carte){
        this.getPile(cle).empiler(carte);
        return this;
    }

    /**
     * Enlève une carte au plateau
     * @param cle la clé de la pile à dépiler
     * @return la carte dépilée
     */
    public Carte enleverCartePlateau(TypePile cle){
        return this.getPile(cle).depiler();
    }

    /**
     * Récupère la taille d'une pile du plateau
     * @param cle la clé de la pile
     * @return la taille de la pile
     */
    public int getTaillePile(TypePile cle){
        return this.getPile(cle).taille();
    }

    /**
     * Enlève 2 cartes d'affilé d'une pile
     * Sera utilisée uniquement sur les piles TypePile.BATAILLE et TypePile.VITESSE
     * @param cle la clé de la pile
     * @param defausse le défusse
     */
    public void enleverCartesAttaqueEtParadePile(TypePile cle, PileCarte defausse){
        // On enleve deux fois : pour enlever la carte attaque et la carte parade
        if(cle != TypePile.BATAILLE && cle !=TypePile.VITESSE){
            throw new IllegalArgumentException("Erreur : le type de pile pour utiliser cette fonction doit être Bataille ou Vitesse");
        }
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
