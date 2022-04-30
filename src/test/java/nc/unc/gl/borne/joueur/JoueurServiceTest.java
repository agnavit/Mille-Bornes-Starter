package nc.unc.gl.borne.joueur;

import nc.unc.gl.borne.Deck.Deck;
import nc.unc.gl.borne.Deck.DeckService;
import nc.unc.gl.borne.carte.*;
import nc.unc.gl.borne.plateau.PlateauService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class JoueurServiceTest {

    public JoueurService joueurService = new JoueurService();
    public PlateauService plateauService = new PlateauService();
    public DeckService deckService = new DeckService();

    @Test
    void jeter(){
        /*On va vérifier qu'une carte jetée n'est plus présente dans la main du joueur
        Et vérifier que cette carte est au sommet de la pile défausse
         */
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Deck mainJoueur = new Deck();
        PileCarte defausse = new PileCarte();
        deckService.ajouter(carte1, j);
        j.setMain(mainJoueur);
        joueurService.jeter(carte1, defausse, j);
        assertEquals(defausse.getSommet(), carte1);
        assertFalse(deckService.contains(carte1, j));

    }

    @Test
    void poserVitesse(){
        //TODO
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Carte carte2 = new Carte(NomCarte.VITESSE, TypeCarte.ATTAQUE, 2);
        Deck mainJoueur = new Deck();
        PileCarte defausse = new PileCarte();
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte2, j);
        j.setMain(mainJoueur);

        // Si la pile Vitesse est vide -> Erreur
        IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> joueurService.poserCarteVitesse(carte1, defausse, j));
        Assertions.assertEquals("Erreur: la pile vitesse est vide, on ne peut pas poser une carte Vitesse-Parade!", thrown1.getMessage());

        plateauService.ajouterCartePlateau(TypePile.VITESSE, carte2, j);

        //Si la carte vitesse est de type attaque
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> joueurService.poserCarteVitesse(carte2, defausse, j));
        Assertions.assertEquals("Erreur: on ne peut pas poser une carte Vitesse-Attaque sur notre plateau!", thrown2.getMessage());

        // Si il n'y a pas d'erreur
        joueurService.poserCarteVitesse(carte1,defausse, j);
        assertEquals(defausse.getPileCarte().size(), 2);
        assertEquals(plateauService.getTaillePile(TypePile.VITESSE, j), 0);

    }

    @Test
    void poserParade(){
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Carte carte1 = new Carte(NomCarte.CREVAISON, TypeCarte.ATTAQUE, 1);
        Carte carte2 = new Carte(NomCarte.CREVAISON, TypeCarte.PARADE, 2);
        Carte carte3 = new Carte(NomCarte.ACCIDENT, TypeCarte.PARADE, 3);
        Carte carte4 = new Carte(NomCarte.FEU, TypeCarte.ATTAQUE, 4);
        Carte carte5 = new Carte(NomCarte.FEU, TypeCarte.PARADE, 5);
        Deck mainJoueur = new Deck();
        PileCarte defausse = new PileCarte();;
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte2, j);
        deckService.ajouter(carte3, j);

        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carte1, j);

        // Si la carte parade est de type différent de la carte sur la pile bataille
        IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> joueurService.poserCarteParade(carte3, defausse, j));
        Assertions.assertEquals("Erreur: on ne peut pas poser une carte parade d'un nom différent que celui" +
            " la première carte sur la pile bataille!", thrown1.getMessage());

        plateauService.enleverCartePlateau(TypePile.BATAILLE, j);
        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carte2, j);

        // Si il n'y a pas d'attaque à parer (la carte sur la pile bataille n'est pas une attaque)
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> joueurService.poserCarteParade(carte2, defausse, j));
        Assertions.assertEquals("Erreur: on ne peut pas poser une carte parade si la première carte" +
            " sur le pile bataille n'est pas une carte attaque", thrown2.getMessage());

        // Si il n'y a pas d'erreur et que la carte parade n'a pas pour nom FEU
        plateauService.enleverCartePlateau(TypePile.BATAILLE, j);
        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carte1, j);
        joueurService.poserCarteParade(carte2, defausse, j);
        assertEquals(defausse.getPileCarte().size(), 2);
        assertEquals(plateauService.getTaillePile(TypePile.BATAILLE, j), 1);

        // Si il n'y a pas d'erreur et que la carte parade a pour nom FEU
        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carte4, j);
        joueurService.poserCarteParade(carte5, defausse, j);
        assertEquals(defausse.getPileCarte().size(), 3);
        assertEquals(plateauService.getTaillePile(TypePile.BATAILLE, j), 2);
    }

    @Test
    void poserBorne(){
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Carte carte1 = new Carte(NomCarte.VINGT_CINQ, TypeCarte.BORNE, 1);
        Carte carte2 = new Carte(NomCarte.CINQUANTE, TypeCarte.BORNE, 2);
        Carte carte3 = new Carte(NomCarte.SOIXANTE_QUINZE, TypeCarte.BORNE, 3);
        Carte carte4 = new Carte(NomCarte.CENT, TypeCarte.BORNE, 4);
        Carte carte5 = new Carte(NomCarte.DEUX_CENTS, TypeCarte.BORNE, 5);

        Deck mainJoueur = new Deck();
        PileCarte defausse = new PileCarte();
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte2, j);
        deckService.ajouter(carte3, j);
        deckService.ajouter(carte4, j);
        deckService.ajouter(carte5, j);
        j.setMain(mainJoueur);

        joueurService.poserCarteBorne(carte1, j);
        assertEquals(j.getScore(),25);
        joueurService.poserCarteBorne(carte2, j);
        assertEquals(j.getScore(),75);
        joueurService.poserCarteBorne(carte3, j);
        assertEquals(j.getScore(),150);
        joueurService.poserCarteBorne(carte4, j);
        assertEquals(j.getScore(),250);
        joueurService.poserCarteBorne(carte5, j);
        assertEquals(j.getScore(),450);
        assertEquals(j.getPlateau().getPile(TypePile.BORNES).getPileCarte().size(),5);
    }

    @Test
    void poserBotte(){
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Carte carte1 = new Carte(NomCarte.CAMION_CITERNE, TypeCarte.BORNE, 1);
        Carte carte2 = new Carte(NomCarte.VEHICULE_PRIORITAIRE, TypeCarte.BORNE, 2);
        Deck mainJoueur = new Deck();
        PileCarte defausse = new PileCarte();
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte2, j);
        j.setMain(mainJoueur);

        joueurService.poserCarteBotte(carte1, j, defausse);
        joueurService.poserCarteBotte(carte2, j, defausse);
        assertEquals(j.getPlateau().getPile(TypePile.BOTTES).getPileCarte().size(), 2);

        //TODO maj tests
    }

    @Test
    void poser(){
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Carte carte2 = new Carte(NomCarte.VITESSE, TypeCarte.ATTAQUE, 2);
        Carte carte3 = new Carte(NomCarte.CREVAISON, TypeCarte.ATTAQUE, 3);
        Carte carte4 = new Carte(NomCarte.CREVAISON, TypeCarte.PARADE, 4);
        Carte carte5 = new Carte(NomCarte.DEUX_CENTS, TypeCarte.BORNE, 5);
        Carte carte6 = new Carte(NomCarte.CAMION_CITERNE, TypeCarte.BOTTE, 6);

        Deck mainJoueur = new Deck();
        PileCarte defausse = new PileCarte();
        deckService.ajouter(carte1, j);
//        .ajouter(carte2).ajouter(carte3).ajouter(carte4).ajouter(carte5).ajouter(carte6)
        j.setMain(mainJoueur);

        // Si on pose une carte ATTAQUE -> erreur
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> joueurService.poser(carte2, defausse, j));
        Assertions.assertEquals("Erreur: on ne peut pas utiliser une carte attaque sur son plateau!", thrown.getMessage());

        plateauService.ajouterCartePlateau(TypePile.VITESSE, carte2, j);
        joueurService.poser(carte1,defausse, j);
        assertEquals(j.getMain().getMainJoueur().size(), 0);
        assertEquals(defausse.getPileCarte().size(),2);

        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carte3, j);
        j.setMain(deckService.ajouter(carte4, j));
        joueurService.poser(carte4, defausse, j);
        assertEquals(j.getMain().getMainJoueur().size(), 0);
        assertEquals(defausse.getPileCarte().size(),4);

        deckService.ajouter(carte5, j);
        deckService.ajouter(carte6, j);
        joueurService.poser(carte5, defausse, j);
        joueurService.poser(carte6, defausse, j);
        assertEquals(j.getMain().getMainJoueur().size(), 0);
        assertEquals(defausse.getPileCarte().size(),4);

        assertEquals(j.getPlateau().getPile(TypePile.VITESSE).getPileCarte().size(),0);
        assertEquals(j.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().size(),1);
        assertEquals(j.getPlateau().getPile(TypePile.BORNES).getPileCarte().size(),1);
        assertEquals(j.getPlateau().getPile(TypePile.BOTTES).getPileCarte().size(),1);
    }

    @Test
    void attaquer() {
        Joueur j = new Joueur(1, "mayaSixtine", 24);

        Carte carteAttaque = new Carte(NomCarte.VITESSE, TypeCarte.ATTAQUE, 2);
        Carte carteAttaque2 = new Carte(NomCarte.CREVAISON, TypeCarte.ATTAQUE, 3);

        assertEquals(j.getPlateau().getPile(TypePile.VITESSE).getPileCarte().size(), 0);
        joueurService.attaquer(carteAttaque, j);
        assertEquals(j.getPlateau().getPile(TypePile.VITESSE).getPileCarte().size(), 1);
        //on verrifie qu'il y a déjà une carte dans la pile bataille : feu vert
        assertEquals(j.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().size(), 1);
        joueurService.attaquer(carteAttaque2, j);
        assertEquals(j.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().size(), 2);
        joueurService.attaquer(carteAttaque2, j);
        //on ne peut pas poser 2 attaque pareil sur le même plateau
        assertEquals(j.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().size(), 2);
    }
}
