package nc.unc.gl.borne.joueur;

import nc.unc.gl.borne.carte.*;
import nc.unc.gl.borne.plateau.PlateauService;

public class JoueurService {

    public PlateauService plateauService = new PlateauService();

    /**
     * Jeter une carte de la main dans la défausse
     * @param carteChoisie la carte à jeter
     * @param defausse la défausse de la partie
     */
    public void jeter(Carte carteChoisie, PileCarte defausse, Joueur joueur){
        joueur.getMain().enlever(carteChoisie);
        defausse.empiler(carteChoisie);
    }

    /**
     * Poser sur son plateau une carte vitesse : c'est-à-dire contrer une attaque vitesse
     * Lors de cette action la carte attaque-vitesse et la carte parade-vitesse sont jetées dans la défausse
     * @param carteVitesse la carte vitesse à poser
     * @param defausse la defausse de la partie
     */
    public void poserCarteVitesse(Carte carteVitesse, PileCarte defausse, Joueur joueur){
        //Si la carte vitesse est de type attaque
        if(carteVitesse.getType() == TypeCarte.ATTAQUE){
            throw new IllegalArgumentException("Erreur: on ne peut pas poser une carte Vitesse-Attaque sur notre" +
                " plateau!");
        }
        // Si la pile Vitesse est vide -> Erreur
        if(plateauService.getTaillePile(TypePile.VITESSE, joueur) == 0){
            throw new IllegalArgumentException("Erreur: la pile vitesse est vide, on ne peut pas poser une carte " +
                "Vitesse-Parade!");
        }

        if(carteVitesse.getType() == TypeCarte.PARADE){
            joueur.setPlateau(plateauService.ajouterCartePlateau(TypePile.VITESSE, carteVitesse, joueur));
            plateauService.enleverCartesAttaqueEtParadePile(TypePile.VITESSE, defausse, joueur);
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
    public void poserCarteParade(Carte carteParade, PileCarte defausse, Joueur joueur){
        Carte derniereCarte = joueur.getPlateau().getPile(TypePile.BATAILLE).getSommet();

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
            defausse.empiler(plateauService.enleverCartePlateau(TypePile.BATAILLE, joueur));
            joueur.setPlateau(plateauService.ajouterCartePlateau(TypePile.BATAILLE, carteParade, joueur));
        }
        // Sinon on jette les deux cartes
        else{
            joueur.setPlateau(plateauService.ajouterCartePlateau(TypePile.BATAILLE, carteParade, joueur));
            plateauService.enleverCartesAttaqueEtParadePile(TypePile.BATAILLE, defausse, joueur);
        }
    }

    public void poserCarteBorne(Carte carteBorne, Joueur joueur){
        joueur.setPlateau(plateauService.ajouterCartePlateau(TypePile.BORNES, carteBorne, joueur));
        switch (carteBorne.getNom()) {
            case VINGT_CINQ -> joueur.setScore(joueur.getScore() + 25);
            case CINQUANTE -> joueur.setScore(joueur.getScore() + 50);
            case SOIXANTE_QUINZE -> joueur.setScore(joueur.getScore() + 75);
            case CENT -> joueur.setScore(joueur.getScore() + 100);
            case DEUX_CENTS -> joueur.setScore(joueur.getScore() + 200);
            default -> throw new IllegalArgumentException("Erreur: la carte borne n'as pas un nom de carte correct " +
                "(valeur en km)!");
        }

    }

    public void poserCarteBotte(Carte carteBotte, Joueur joueur) {
        joueur.setPlateau(plateauService.ajouterCartePlateau(TypePile.BOTTES, carteBotte, joueur));
        //TODO : on rejoue
    }


    // Retourner boolean ?
    // Pas pris en compte la boucle



    /**
     * Poser une carte sur le plateau
     * @param carteChoisie la carte à poser
     * @param defausse la défausse (dans le cas où une attaque est contrée : la carte choisie est donc de type parade)
     */
    public void poser(Carte carteChoisie, PileCarte defausse, Joueur joueur){
        if(carteChoisie.getType() == TypeCarte.ATTAQUE){
            throw new IllegalArgumentException("Erreur: on ne peut pas utiliser une carte attaque sur son plateau!");
        }
        if(carteChoisie.getNom() == NomCarte.VITESSE){
            poserCarteVitesse(carteChoisie, defausse, joueur);
        }
        // Le feu vert n'est pas pris en compte lorsqu'il est posé sur une carte parade : faire sous fonction poserFeuVert()
        else if(carteChoisie.getType() == TypeCarte.PARADE){
            poserCarteParade(carteChoisie, defausse, joueur);
        }
        else if(carteChoisie.getType() == TypeCarte.BORNE){
            poserCarteBorne(carteChoisie, joueur);

        }
        else if(carteChoisie.getType() == TypeCarte.BOTTE){
            poserCarteBotte(carteChoisie, joueur);
        }
        joueur.setMain(joueur.getMain().enlever(carteChoisie));
    }

    public void attaquer(Carte carteAttaque, Joueur jouerAttaque) {
        //TODO
    }
}
