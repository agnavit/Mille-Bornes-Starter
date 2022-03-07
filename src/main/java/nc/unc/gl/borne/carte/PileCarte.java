package nc.unc.gl.borne.carte;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Description d'une pile de carte
 */
public class PileCarte {

    /**
     * Ensemble de carte représentée dans une liste, implémenté sous forme de pile
     *  sortie <-  [ 0 - - - - - (len-1) ]  <- entrée
     */
    private ArrayList<Carte> pileCarte;

    /**
     * Constructeur vide
     */
    public PileCarte() {
        this.pileCarte = new ArrayList<>();
    }

    /**
     * Constructeur par initialisation
     * @param pileCarte liste des cartes de la pile
     */
    public PileCarte(ArrayList<Carte> pileCarte) {
        this.pileCarte = pileCarte;
    }

    /**
     * Récupère la valeur de l'attribut pileCarte
     * @return pileCarte
     */
    public ArrayList<Carte> getPileCarte() {
        return pileCarte;
    }


    /**
     * Modifie la valeur de l'attribut pileCarte
     * @param pileCarte la nouvelle valeur
     */
    public void setPileCarte(ArrayList<Carte> pileCarte) {
        this.pileCarte = pileCarte;
    }

    /**
     * Renvoie la taille de la pile de carte
     * @return la taille
     */
    public int taille(){
        return this.pileCarte.size();
    }

    /**
     * Recupère la carte d'une pile
     * @param carteARecupere la carte à récupérer
     * @return la carte entrée en paramètre
     */
    public Carte getCarte(Carte carteARecupere){
        for (Carte carte : this.pileCarte) {
            if (carte == carteARecupere) {
                return carte;
            }
        }
        throw new IllegalArgumentException("Erreur : la carte demandée n'est pas présente dans la pile!");
    }

    /**
     * Renvoie la carte d'une pile à un indice donné
     * @param i l'indice de la carte à renvoyer
     * @return la carte à l'indice i
     */
    public Carte getCarte(int i){
        return this.pileCarte.get(i);
    }

    /**
     * Ajouter une carte dans la pile
     * @param carteChoisie la carte à ajouter
     */
    public PileCarte ajouter(Carte carteChoisie){
        this.pileCarte.add(carteChoisie);
        return this;
    }

    /**
     * Enlève une carte : celle à l'indice 0 de la liste
     */
    public PileCarte enlever(){
        if(this.pileCarte.size() == 0){
            throw new IllegalArgumentException("Erreur : on ne peut pas enlever une carte, la pile est déjà vide!");
        }
        this.pileCarte.remove(0);
        return this;
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
}
