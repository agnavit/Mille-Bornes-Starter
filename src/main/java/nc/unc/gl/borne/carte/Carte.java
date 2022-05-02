package nc.unc.gl.borne.carte;

import lombok.Data;

import java.util.Locale;

/**
 * Description d'une carte du jeu du 1000 bornes
 *
 */
@Data
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

    public TypeCarte getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Carte{" +
            "nom=" + nom +
            ", type=" + type +
            ", numero=" + numero +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carte carte = (Carte) o;
        return nom == carte.nom && type == carte.type;
    }

    public String getStringImage(){
        String nomString = String.valueOf(nom).toLowerCase(Locale.ROOT);
        String typeString = String.valueOf(type).toLowerCase(Locale.ROOT);
        return typeString+"_"+nomString+".jpeg";
    }
}
