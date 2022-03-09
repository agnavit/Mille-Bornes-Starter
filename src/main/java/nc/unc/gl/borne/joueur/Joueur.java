package nc.unc.gl.borne.joueur;

import nc.unc.gl.borne.carte.*;

/**
 * Description d'un joueur
 */
public class Joueur {

    /**
     * Identifiant
     */
    private int id;

    /**
     * Pseudo
     */
    private String pseudo;

    /**
     * Âge
     */
    private int age;

    /**
     * Plateau représentant les cartes qui sont posées devant le joueur
     */
    private Plateau plateau;

    /**
     * Les cartes que le joueur a en main
     */
    private Deck main;

    /**
     * Le score
     */
    private int score;

    /**
     * Constructeur
     * @param id id
     * @param pseudo pseudo
     * @param age age
     */
    public Joueur(int id, String pseudo, int age) {
        this.id = id;
        this.pseudo = pseudo;
        this.age = age;
        this.plateau = new Plateau();
        this.score = 0;
    }

    public int getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getAge() {
        return age;
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPlateau(Plateau plateau) {
        this.plateau = plateau;
    }

    public Deck getMain() {
        return main;
    }

    public void setMain(Deck main) {
        this.main = main;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Jeter une carte de la main dans la défausse
     * @param carteChoisie la carte à jeter
     * @param defausse la défausse de la partie
     */
    public void jeter(Carte carteChoisie, PileCarte defausse){
        this.main.enlever(carteChoisie);
        defausse.empiler(carteChoisie);
    }

    /**
     * Poser sur son plateau une carte vitesse : c'est-à-dire contrer une attaque vitesse
     * Lors de cette action la carte attaque-vitesse et la carte parade-vitesse sont jetées dans la défausse
     * @param carteVitesse la carte vitesse à poser
     * @param defausse la defausse de la partie
     */
    public void poserCarteVitesse(Carte carteVitesse, PileCarte defausse){
        //Si la carte vitesse est de type attaque
        if(carteVitesse.getType().equals(TypeCarte.ATTAQUE)){
            throw new IllegalArgumentException("Erreur: on ne peut pas poser une carte Vitesse-Attaque sur notre" +
                " plateau!");
        }
        // Si la pile Vitesse est vide -> Erreur
        if(plateau.getTaillePile("Vitesse") == 0){
            throw new IllegalArgumentException("Erreur: la pile vitesse est vide, on ne peut pas poser une carte " +
                "Vitesse-Parade!");
        }
        plateau.ajouterCartePlateau("Vitesse", carteVitesse);
        plateau.enleverCartesAttaqueEtParadePile("Vitesse", defausse);
    }

    /**
     * Poser sur son plateau une carte parade (sans être une carte parade-vitesse, cas pris en compte précédemment)
     * Si une attaque de nom autre que feu est contrée, alors l'attaque et la parade sont jetées dans la défausse
     * Pas besoin d'un feu vert pour redémarrer après avoir contré une attaque
     * @param carteBataille la carte parade à poser
     * @param defausse la défausse de la partie
     */
    public void poserCarteParade(Carte carteBataille, PileCarte defausse){
        Carte derniereCarte = plateau.getPile("Bataille").getSommet();
        if(derniereCarte.getNom() != carteBataille.getNom()){
            throw new IllegalArgumentException("Erreur: on ne peut pas poser une carte parade d'un nom différent" +
                " que celui la première carte sur la pile bataille!");
        }
        if(derniereCarte.getType() != TypeCarte.ATTAQUE){
            throw new IllegalArgumentException("Erreur: on ne peut pas poser une carte parade si la première carte" +
                " sur le pile bataille n'est pas une carte attaque");
        }
        plateau.ajouterCartePlateau("Bataille", carteBataille);

        if(carteBataille.getNom() != NomCarte.FEU){
            plateau.enleverCartesAttaqueEtParadePile("Bataille", defausse);
        }
    }


    // Retourner boolean ?
    // Pas pris en compte la boucle

    /**
     * Poser une carte sur le plateau
     * @param carteChoisie la carte à poser
     * @param defausse la défausse (dans le cas où une attaque est contrée : la carte choisie est donc de type parade)
     */
    public void poser(Carte carteChoisie, PileCarte defausse){
        if(carteChoisie.getNom() == NomCarte.VITESSE){
            poserCarteVitesse(carteChoisie, defausse);
        }
        if(carteChoisie.getType() == TypeCarte.PARADE){
            poserCarteParade(carteChoisie, defausse);
        }
        if(carteChoisie.getType() == TypeCarte.BORNE){
            plateau.ajouterCartePlateau("Bornes", carteChoisie);
            //TODO : ajouter le score => PB: comment accéder au score de la carte?
        }
        if(carteChoisie.getType() == TypeCarte.BOTTE){
            plateau.ajouterCartePlateau("Bottes", carteChoisie);
            //TODO : on rejoue
        }
        this.main.enlever(carteChoisie);
    }

    public void attaquer(Carte carteAttaque, Joueur jouerAttaque){
        //TODO
    }
}
