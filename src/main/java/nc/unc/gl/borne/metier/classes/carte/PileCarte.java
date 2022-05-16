package nc.unc.gl.borne.metier.classes.carte;

import lombok.Data;
import nc.unc.gl.borne.metier.classes.carte.enumerations.NomCarte;
import nc.unc.gl.borne.metier.classes.carte.enumerations.TypeCarte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

@Data
/**
 * Ensemble de carte représentée dans une liste, implémenté sous forme de pile
 *   [ 0 - - - - - (len-1) ]  <- empiler / -> dépiler
 */
public class PileCarte {


    private ArrayList<Carte> pileCarte;
    public PileCarte() {
        this.pileCarte = new ArrayList<>();
    }
    public ArrayList<Carte> getPileCarte() {
        return pileCarte;
    }

    public Carte getCarte(Carte carteARecupere){
        for (Carte carte : this.pileCarte) {
            if (carte == carteARecupere) {
                return carte;
            }
        }
        throw new IllegalArgumentException("Erreur : la carte demandée n'est pas présente dans la pile!");
    }

    public Carte getCarte(int i){
        return this.pileCarte.get(i);
    }

    public Carte getSommet(){
        return this.getCarte(this.getPileCarte().size()-1);
    }

    public int getTaille(){
        return this.pileCarte.size();
    }

    public PileCarte empiler(Carte carteAAjouter){
        this.pileCarte.add(carteAAjouter);
        return this;
    }

    public Carte depiler(){
        if(this.pileCarte.size() == 0){
            throw new IllegalArgumentException("Erreur : on ne peut pas enlever une carte, la pile est déjà vide!");
        }
        Carte carteDepile = this.getSommet();
        this.pileCarte.remove(this.getPileCarte().size()-1);
        return carteDepile;
    }

    public void melanger(){
        Collections.shuffle(this.pileCarte);
    }

    public int getNbCartePile(Carte carteACompter){
        int res = 0;
        for (Carte carte : pileCarte) {
            if (carte.equals(carteACompter)) {
                res += 1;
            }
        }
        return res;
    }

    public boolean contientCarte(NomCarte nomCarte, TypeCarte typeCarte){
        for(Carte carte : pileCarte) {
            if (carte.getType() == typeCarte && carte.getNom() == nomCarte) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "PileCarte{" +
            "pileCarte=" + pileCarte +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PileCarte pileCarte1 = (PileCarte) o;
        return (Objects.equals(pileCarte, pileCarte1.pileCarte));
    }

    public boolean estVide(){
        return pileCarte.size() == 0;
    }
}
