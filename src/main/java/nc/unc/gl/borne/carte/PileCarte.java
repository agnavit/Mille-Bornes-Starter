package nc.unc.gl.borne.carte;

import lombok.Data;
import nc.unc.gl.borne.jeuComplet.JeuComplet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Description d'une pile de carte
 */
@Data
public class PileCarte {

    /**
     * Ensemble de carte représentée dans une liste, implémenté sous forme de pile
     *   [ 0 - - - - - (len-1) ]  <- empiler / -> dépiler
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
     * Renvoie la carte du sommet de la pile (la carte qui sera dépilé)
     * @return la dernière carte de la liste PileCarte
     */
    public Carte getSommet(){
        return this.getCarte(this.getPileCarte().size()-1);
    }

    /**
     * Ajouter une carte dans la pile
     * @param carteChoisie la carte à ajouter
     */
    public PileCarte empiler(Carte carteChoisie){
        this.pileCarte.add(carteChoisie);
        return this;
    }

    /**
     * Enlève une carte : celle à l'indice 0 de la liste
     */
    public Carte depiler(){
        if(this.pileCarte.size() == 0){
            throw new IllegalArgumentException("Erreur : on ne peut pas enlever une carte, la pile est déjà vide!");
        }
        Carte carteDepile = this.getSommet();
        this.pileCarte.remove(this.getPileCarte().size()-1);
        return carteDepile;
    }

    /**
     * Mélange le JeuComplet pour former une pioche
     */
    public PileCarte melangerPioche(){
        Collections.shuffle(this.pileCarte);
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

    /**
     * Compte le nombre de carte d'une carte passée en paramètre
     * @param carteACompter la carte à analyser
     * @return le nombre de cette carte dans la pile
     */
    public int getNbCartePile(Carte carteACompter){
        int res = 0;
        for (int i = 0; i<pileCarte.size(); i++) {
            if(pileCarte.get(i).equals(carteACompter)){
                res += 1;
            }
        }
        return res;
    }

    public int getTaille(){
        return this.pileCarte.size();
    }

    /**
     * Fonction test @Anthony permet de récup la dernière carte de la pile
     * @return carte
     */
    public Carte getLastCard(){
        int sizePile = this.pileCarte.size();
        System.out.println(this.pileCarte.get(sizePile-1));
        return this.pileCarte.get(sizePile-1);
    }
}
