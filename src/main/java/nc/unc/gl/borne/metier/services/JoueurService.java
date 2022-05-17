package nc.unc.gl.borne.metier.services;

import nc.unc.gl.borne.dao.connection.partieDao.JoueurDao;
import nc.unc.gl.borne.metier.classes.Joueur;
import nc.unc.gl.borne.metier.classes.carte.Carte;
import nc.unc.gl.borne.metier.classes.carte.PileCarte;
import nc.unc.gl.borne.metier.classes.carte.enumerations.NomCarte;
import nc.unc.gl.borne.metier.classes.carte.enumerations.TypeCarte;
import nc.unc.gl.borne.metier.classes.carte.enumerations.TypePile;
import nc.unc.gl.borne.metier.classes.partie.Game;
import nc.unc.gl.borne.views.game.GameView;


public class JoueurService {

    public PlateauService plateauService = new PlateauService();
    public DeckService deckService = new DeckService();
    public JoueurDao joueurDao = new JoueurDao();
    public Game game = GameView.game;

    public void jeter(Carte carteChoisie, PileCarte defausse, Joueur joueur){
        deckService.enlever(carteChoisie, joueur);
        defausse.empiler(carteChoisie);
    }

    /**
     * Poser sur son plateau une carte vitesse : c'est-à-dire contrer une attaque vitesse
     * Lors de cette action la carte attaque-vitesse et la carte parade-vitesse sont jetées dans la défausse
     */
    public void poserCarteVitesse(Carte carteVitesse, PileCarte defausse, Joueur joueur){
        if(carteVitesse.getType() == TypeCarte.ATTAQUE){
            throw new IllegalArgumentException("Erreur: on ne peut pas poser une carte Vitesse-Attaque sur notre" +
                " plateau!");
        }
        if(plateauService.getTaillePile(TypePile.VITESSE, joueur) == 0){
            throw new IllegalArgumentException("Erreur: la pile vitesse est vide, on ne peut pas poser une carte " +
                "Vitesse-Parade!");
        }

        if(joueur.getPlateau().getPile(TypePile.BOTTES).contientCarte(NomCarte.VITESSE, TypeCarte.BOTTE)){
            throw new IllegalArgumentException("Erreur : le joueur possède le véhicule prioritaire!");
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
     * Si une attaque est contrée, alors l'attaque et la parade sont jetées dans la défausse
     */
    public void poserCarteParade(Carte carteParade, PileCarte defausse, Joueur joueur){
        if(joueur.getPlateau().getPile(TypePile.BATAILLE).estVide()){
            throw new IllegalArgumentException("Erreur : la pile bataille est vide!");
        }

        Carte derniereCarte = joueur.getPlateau().getPile(TypePile.BATAILLE).getSommet();

        // Si la carte parade est de type différent de la carte sur la pile bataille
        if(derniereCarte.getNom() != carteParade.getNom()){
            throw new IllegalArgumentException("Erreur: on ne peut pas poser une carte parade d'un nom différent" +
                " que celui la première carte sur la pile bataille!");
        }

        // S' il n'y a pas d'attaque à parer
        if(derniereCarte.getType() != TypeCarte.ATTAQUE){
            throw new IllegalArgumentException("Erreur: on ne peut pas poser une carte parade si la première carte" +
                    " sur le pile bataille n'est pas une carte attaque");

        }
        joueur.setPlateau(plateauService.ajouterCartePlateau(TypePile.BATAILLE, carteParade, joueur));
        plateauService.enleverCartesAttaqueEtParadePile(TypePile.BATAILLE, defausse, joueur);
    }

    public void poserCarteBorne(Carte carteBorne, Joueur joueur){
        int scoreJoueur = 0;
        switch (carteBorne.getNom()) {
            case VINGT_CINQ -> scoreJoueur = joueur.getScore() + 25;
            case CINQUANTE -> scoreJoueur = joueur.getScore() + 50;
            case SOIXANTE_QUINZE -> scoreJoueur = joueur.getScore() + 75;
            case CENT -> scoreJoueur = joueur.getScore() + 100;
            case DEUX_CENTS -> scoreJoueur = joueur.getScore() + 200;
            default -> throw new IllegalArgumentException("Erreur: la carte borne n'as pas un nom de carte correct " +
                "(valeur en km)!");
        }
        // S'il y a une limitation de vitesse
        if (joueur.getScore() + carteBorne.getIdentifiant() > 1000) {
            throw new IllegalArgumentException("Erreur : il faut terminer avec un score égale à 1000");
        }else {
            if (joueur.getPlateau().getPile(TypePile.VITESSE).getTaille() == 1) {
                if (carteBorne.getNom() != NomCarte.VINGT_CINQ && carteBorne.getNom() != NomCarte.CINQUANTE) {
                    throw new IllegalArgumentException("Erreur : la carte borne ne peut être posée, en raison " +
                        "d'une limitation de vitesse! ");
                }
            }

            if (joueur.getPlateau().getPile(TypePile.BATAILLE).getTaille() == 1) {
                throw new IllegalArgumentException("Erreur : la carte borne ne peut être posée, en raison " +
                    "d'une carte attaque présente! ");
            }

            joueur.setPlateau(plateauService.ajouterCartePlateau(TypePile.BORNES, carteBorne, joueur));
            joueur.setScore(scoreJoueur);
        }
    }

    public void poserCarteBotte(Carte carteBotte, Joueur joueur, PileCarte defausse) {
        joueur.setPlateau(plateauService.ajouterCartePlateau(TypePile.BOTTES, carteBotte, joueur));

        // Si le joueur a une carte attaque du même nom que la carte botte alors cette attaque est enlevée

        if(carteBotte.getNom() == NomCarte.VITESSE
            && joueur.getPlateau().getPile(TypePile.VITESSE).getTaille() == 1){
            Carte carteAJeter = plateauService.enleverCartePlateau(TypePile.VITESSE, joueur);
            defausse.empiler(carteAJeter);
        }

        if (joueur.getPlateau().getPile(TypePile.BATAILLE).getTaille() == 1) {
            NomCarte nomCarteSommetPileBataille = joueur.getPlateau().getPile(TypePile.BATAILLE).getSommet().getNom();
            if(nomCarteSommetPileBataille.equals(NomCarte.FEU) && carteBotte.getNom()== NomCarte.VITESSE){
                Carte carteAJeter = plateauService.enleverCartePlateau(TypePile.BATAILLE, joueur);
                defausse.empiler(carteAJeter);
            }
            if (nomCarteSommetPileBataille.equals(carteBotte.getNom())) {
                Carte carteAJeter = plateauService.enleverCartePlateau(TypePile.BATAILLE, joueur);
                defausse.empiler(carteAJeter);
            }
        }
    }

    /**
     * Poser une carte := poser une carte sur le plateau du joueur
     */
    public void poser(Carte carteChoisie, PileCarte defausse, Joueur joueur){
        if(carteChoisie.getType() == TypeCarte.ATTAQUE){
            throw new IllegalArgumentException("Erreur: on ne peut pas utiliser une carte attaque sur son plateau!");
        }

        if(carteChoisie.getType() == TypeCarte.BOTTE){
            poserCarteBotte(carteChoisie, joueur, defausse);
        }

        else if(carteChoisie.getNom() == NomCarte.VITESSE){
            poserCarteVitesse(carteChoisie, defausse, joueur);
        }
        // Le feu vert n'est pas pris en compte lorsqu'il est posé sur une carte parade : faire sous fonction poserFeuVert()
        else if(carteChoisie.getType() == TypeCarte.PARADE){
            poserCarteParade(carteChoisie, defausse, joueur);
        }
        else if(carteChoisie.getType() == TypeCarte.BORNE){
            poserCarteBorne(carteChoisie, joueur);
        }
        deckService.enlever(carteChoisie, joueur);
    }

    public void attaquer(Carte carteAttaque, Joueur joueurDefensif, Joueur joueurAttaque) {
        if(carteAttaque.getType() != TypeCarte.ATTAQUE){
            throw new IllegalArgumentException("Erreur : on peut attaquer que avec une carte de type attaque!");
        }
        NomCarte nomCarteAttaque = carteAttaque.getNom();

        // La botte véhicule prioritaire contre les attaques feu et vitesse
        if(joueurAttaque.getPlateau().getPile(TypePile.BOTTES).contientCarte(NomCarte.VITESSE, TypeCarte.BOTTE)
            && (nomCarteAttaque == NomCarte.VITESSE || nomCarteAttaque == NomCarte.FEU)) {
            throw new IllegalArgumentException("Erreur : on peut attaquer, le joueur en face possède la botte véhicule" +
                " prioritaire!");
        }

        if(joueurAttaque.getPlateau().getPile(TypePile.BOTTES).contientCarte(NomCarte.ACCIDENT, TypeCarte.BOTTE)
            && nomCarteAttaque == NomCarte.ACCIDENT){
            throw new IllegalArgumentException("Erreur : on peut attaquer, le joueur en face possède la botte as du" +
                " volant!");
        }

        if(joueurAttaque.getPlateau().getPile(TypePile.BOTTES).contientCarte(NomCarte.CREVAISON, TypeCarte.BOTTE)
            && nomCarteAttaque == NomCarte.CREVAISON){
            throw new IllegalArgumentException("Erreur : on peut attaquer, le joueur en face possède la botte roue" +
                " increvable!");
        }
        if(joueurAttaque.getPlateau().getPile(TypePile.BOTTES).contientCarte(NomCarte.ESSENCE, TypeCarte.BOTTE)
            && nomCarteAttaque == NomCarte.ESSENCE ){
            throw new IllegalArgumentException("Erreur : on peut attaquer, le joueur en face possède la botte camion" +
                " citerne!");
        }


        if (nomCarteAttaque == NomCarte.VITESSE){
            if(joueurAttaque.getPlateau().getPile(TypePile.VITESSE).estVide()){
                joueurAttaque.getPlateau().getPile(TypePile.VITESSE).empiler(carteAttaque);
                deckService.enlever(carteAttaque, joueurDefensif);
            }
            else{
                throw new IllegalArgumentException("Erreur : on peut attaquer, le joueur en face a déjà une attaque sur " +
                    "sa pile vitesse");
            }
        }

        else{
            if(joueurAttaque.getPlateau().getPile(TypePile.BATAILLE).estVide()){
            joueurAttaque.getPlateau().getPile(TypePile.BATAILLE).empiler(carteAttaque);
            deckService.enlever(carteAttaque, joueurDefensif);
            }
            else{
                throw new IllegalArgumentException("Erreur : on peut attaquer, le joueur en face a déjà une attaque sur " +
                    "sa pile bataille");
            }
        }
    }

    public void piocher(PileCarte pioche, Joueur joueur) {
        joueur.getMain().getMainJoueur().add(pioche.depiler());
    }

    public void jouer(PileCarte pioche, Joueur joueur) throws InterruptedException {
        piocher(pioche, joueur);
        game.updateFenetreEnGame();
        while (joueur.getMain().getTaille() != 6) {
            Thread.sleep(500);
        }
        System.out.println(joueur +" à jouer");
    }

    public void insertPlayer(String pseudo, int age) {
        joueurDao.insertJoueur(pseudo, age);
    }

    public void updatePartyPlayer(String id, Joueur joueur) {
        joueurDao.updatePartieJoueur(id, joueur);
    }

    public int getSizeDeck(Joueur player){
        return player.getMain().getMainJoueur().size();
    }

    public Carte getCardInDeck(Joueur player, int number){
        return player.getMain().getMainJoueur().get(number);
    }

    public Carte getLastCardInPile(Joueur player, TypePile typePile){
        return player.getPlateau().getPile(typePile).getSommet();
    }
}
