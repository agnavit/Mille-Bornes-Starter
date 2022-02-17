package nc.unc.gl.borne.classes.carte;

/**
 * Description d'une carte du jeu du 1000 bornes
 *
 * ----------------REFLEXION ----------------
 * ---L'image de la carte on la met où ??----------------
 * ---Dans une nouvelle classe ?
 * ---Peut-être faire dépendre l'image de TypeCarte et NomCarte
 * ---Par ex avec un dico dans un dico: [TypeCarte -> [NomCarte -> imageCarte]] ?
 *
 */

public class Carte {
    /**
     * Nom
     */
    protected NomCarte nom;

    /**
     * Type
     */
    protected TypeCarte type;

    /**
     * Numéro (unique)
     */
    protected int numero;

    /**
     * Constructeur
     * @param nom nom de la carte
     * @param type type de la carte
     * @param numero numéro de la carte
     */
    public Carte(NomCarte nom, TypeCarte type, int numero) {
        this.nom = nom;
        this.type = type;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Carte{" +
            "nom=" + nom +
            ", type=" + type +
            ", numero=" + numero +
            '}';
    }
}
