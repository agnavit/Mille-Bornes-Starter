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
    private HashMap<String, PileCarte> plateau;

    /**
     * Constructeur
     *
     * NB: Créer chaque clé dans le constructeur et initialiser la pile associée à vide
     */
    public Plateau() {
        this.plateau = new HashMap<>();
        this.plateau.put("Vitese", new PileCarte());
        this.plateau.put("Bataille", new PileCarte());
        this.plateau.put("Bornes", new PileCarte());
        this.plateau.put("Bottes", new PileCarte());
    }

    public HashMap<String, PileCarte> getPlateau() {
        return plateau;
    }

    public void setPlateau(HashMap<String, PileCarte> plateau) {
        this.plateau = plateau;
    }


    public PileCarte getPile(String cle){
        if (!plateau.containsKey(cle)){
            throw new IllegalArgumentException("Erreur : la clé entrée n'est pas présente dans le plateau!");
        }
        return this.plateau.get(cle);
    }

    public void ajouterCartePlateau(String cle, Carte carte){
        this.plateau.get(cle).empiler(carte);
    }

    public void enleverCartePlateau(String cle){
        this.plateau.get(cle).depiler();
    }

    public int getTaillePile(String cle){
        return this.getPile(cle).taille();
    }

    public void enleverCartesAttaqueEtParadePile(String cle,PileCarte defausse){
        // On enleve deux fois : pour enlever la carte attaque et la carte parade
        for(int i=0; i<2; i++){
            defausse.empiler(this.plateau.get(cle).getCarte(0));
            this.enleverCartePlateau(cle);
        }
    }
}
