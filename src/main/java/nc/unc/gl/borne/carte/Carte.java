package nc.unc.gl.borne.carte;

import lombok.Data;
import nc.unc.gl.borne.carte.enumerations.NomCarte;
import nc.unc.gl.borne.carte.enumerations.TypeCarte;

import java.util.Locale;

@Data
public class Carte {
    private NomCarte nom;
    private TypeCarte type;
    private int identifiant;

    public Carte(NomCarte nom, TypeCarte type, int identifiant) {
        this.nom = nom;
        this.type = type;
        this.identifiant = identifiant;
    }

    public TypeCarte getType() {
        return type;
    }

    public NomCarte getNom() {return nom;}

    public int getIdentifiant() {return identifiant;}

    public String getStringImage(){
        String nomString = String.valueOf(nom).toLowerCase(Locale.ROOT);
        String typeString = String.valueOf(type).toLowerCase(Locale.ROOT);
        return typeString+"_"+nomString+".jpeg";
    }

    @Override
    public String toString() {
        return "Carte{" +
            "nom=" + nom +
            ", type=" + type +
            ", identifiant=" + identifiant +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carte carte = (Carte) o;
        return nom == carte.nom && type == carte.type;
    }
}
