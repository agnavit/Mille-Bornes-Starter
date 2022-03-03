package nc.unc.gl.borne.carte;

/**
 * Description d'une carte du jeu du 1000 bornes
 *
 */

public class Carte {
    /**
     * Nom
     */
    private NomCarte nom;

    /**
     * Type
     */
    private TypeCarte type;

    /**
     * Numéro (unique)
     */
    private int numero;

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

    public NomCarte getNom() {
        return nom;
    }

    public TypeCarte getType() {
        return type;
    }

    public int getNumero() {
        return numero;
    }


}
