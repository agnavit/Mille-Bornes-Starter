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
        if(carteVitesse.getType() == TypeCarte.ATTAQUE){
            throw new IllegalArgumentException("Erreur: on ne peut pas poser une carte Vitesse-Attaque sur notre" +
                " plateau!");
        }
        // Si la pile Vitesse est vide -> Erreur
        if(plateau.getTaillePile(TypePile.VITESSE) == 0){
            throw new IllegalArgumentException("Erreur: la pile vitesse est vide, on ne peut pas poser une carte " +
                "Vitesse-Parade!");
        }

        if(carteVitesse.getType() == TypeCarte.PARADE){
            this.setPlateau(this.getPlateau().ajouterCartePlateau(TypePile.VITESSE, carteVitesse));
            plateau.enleverCartesAttaqueEtParadePile(TypePile.VITESSE, defausse);
        }
        else{
            throw new IllegalArgumentException("Erreur: la carte vitesse n'as pas un type de carte correct!");
        }

    }

    /**
     * Poser sur son plateau une carte parade (sans être une carte parade-vitesse, cas pris en compte précédemment)
     * Si une attaque de nom autre que feu est contrée, alors l'attaque et la parade sont jetées dans la défausse
     * Pas besoin d'un feu vert pour redémarrer après avoir contré une attaque
     * @param carteParade la carte parade à poser
     * @param defausse la défausse de la partie
     */
    public void poserCarteParade(Carte carteParade, PileCarte defausse){
        Carte derniereCarte = plateau.getPile(TypePile.BATAILLE).getSommet();

        // Si la carte parade est de type différent de la carte sur la pile bataille
        if(derniereCarte.getNom() != carteParade.getNom()){
            throw new IllegalArgumentException("Erreur: on ne peut pas poser une carte parade d'un nom différent" +
                " que celui la première carte sur la pile bataille!");
        }

        // Si il n'y a pas d'attaque à parer (la carte sur la pile bataille n'est pas une attaque)
        if(derniereCarte.getType() != TypeCarte.ATTAQUE){
            throw new IllegalArgumentException("Erreur: on ne peut pas poser une carte parade si la première carte" +
                " sur le pile bataille n'est pas une carte attaque");
        }

        /* Si la carte a pour nom feu : on garde sur la pile bataille uniquement la parade : le feu vert
        et on jette l'attaque : le feu rouge
         */
        if(carteParade.getNom() == NomCarte.FEU){
            defausse.empiler(plateau.enleverCartePlateau(TypePile.BATAILLE));
            this.setPlateau(this.getPlateau().ajouterCartePlateau(TypePile.BATAILLE, carteParade));
        }
        // Sinon on jette les deux cartes
        else{
            this.setPlateau(this.getPlateau().ajouterCartePlateau(TypePile.BATAILLE, carteParade));
            plateau.enleverCartesAttaqueEtParadePile(TypePile.BATAILLE, defausse);
        }
    }

    public void poserCarteBorne(Carte carteBorne){
        this.setPlateau(this.getPlateau().ajouterCartePlateau(TypePile.BORNES, carteBorne));
        switch (carteBorne.getNom()) {
            case VINGT_CINQ -> this.setScore(this.getScore() + 25);
            case CINQUANTE -> this.setScore(this.getScore() + 50);
            case SOIXANTE_QUINZE -> this.setScore(this.getScore() + 75);
            case CENT -> this.setScore(this.getScore() + 100);
            case DEUX_CENTS -> this.setScore(this.getScore() + 200);
            default -> throw new IllegalArgumentException("Erreur: la carte borne n'as pas un nom de carte correct " +
                "(valeur en km)!");
        }

    }

    public void poserCarteBotte(Carte carteBotte){
        this.setPlateau(this.getPlateau().ajouterCartePlateau(TypePile.BOTTES, carteBotte));
        //TODO : on rejoue
    }


    // Retourner boolean ?
    // Pas pris en compte la boucle

    /**
     * Poser une carte sur le plateau
     * @param carteChoisie la carte à poser
     * @param defausse la défausse (dans le cas où une attaque est contrée : la carte choisie est donc de type parade)
     */
    public void poser(Carte carteChoisie, PileCarte defausse){
        if(carteChoisie.getType() == TypeCarte.ATTAQUE){
            throw new IllegalArgumentException("Erreur: on ne peut pas utiliser une carte attaque sur son plateau!");
        }
        if(carteChoisie.getNom() == NomCarte.VITESSE){
            poserCarteVitesse(carteChoisie, defausse);
        }
        // Le feu vert n'est pas pris en compte lorsqu'il est posé sur une carte parade : faire sous fonction poserFeuVert()
        else if(carteChoisie.getType() == TypeCarte.PARADE){
            poserCarteParade(carteChoisie, defausse);
        }
        else if(carteChoisie.getType() == TypeCarte.BORNE){
            poserCarteBorne(carteChoisie);

        }
        else if(carteChoisie.getType() == TypeCarte.BOTTE){
            poserCarteBotte(carteChoisie);
        }
        this.setMain(this.main.enlever(carteChoisie));
    }

    public void attaquer(Carte carteAttaque, Joueur jouerAttaque){
        //TODO
    }
}
