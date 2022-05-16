package nc.unc.gl.borne.joueur;

import nc.unc.gl.borne.Deck.Deck;
import nc.unc.gl.borne.Deck.DeckService;
import nc.unc.gl.borne.carte.*;
import nc.unc.gl.borne.carte.enumerations.NomCarte;
import nc.unc.gl.borne.carte.enumerations.TypeCarte;
import nc.unc.gl.borne.carte.enumerations.TypePile;
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
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        PileCarte defausse = new PileCarte();
        deckService.ajouter(carte1, j);
        joueurService.jeter(carte1, defausse, j);
        assertEquals(defausse.getSommet(), carte1);
        assertFalse(deckService.contains(carte1, j));

    }

    @Test
    void poserCarteVitesse(){
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Carte carteParadeVitesse = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Carte carteAttaqueVitesse = new Carte(NomCarte.VITESSE, TypeCarte.ATTAQUE, 2);
        PileCarte defausse = new PileCarte();
        deckService.ajouter(carteParadeVitesse, j);
        deckService.ajouter(carteAttaqueVitesse, j);

        // Si la pile Vitesse est vide -> Erreur
        IllegalArgumentException thrown1 = Assertions
            .assertThrows(IllegalArgumentException.class, () -> joueurService.poserCarteVitesse(carteParadeVitesse, defausse, j));
        Assertions.assertEquals("Erreur: la pile vitesse est vide, on ne peut pas poser une carte Vitesse-Parade!", thrown1.getMessage());

        plateauService.ajouterCartePlateau(TypePile.VITESSE, carteAttaqueVitesse, j);

        //Si la carte vitesse est de type attaque
        IllegalArgumentException thrown2 = Assertions
            .assertThrows(IllegalArgumentException.class, () -> joueurService.poserCarteVitesse(carteAttaqueVitesse, defausse, j));
        Assertions.assertEquals("Erreur: on ne peut pas poser une carte Vitesse-Attaque sur notre plateau!", thrown2.getMessage());

        joueurService.poserCarteVitesse(carteParadeVitesse,defausse, j);
        assertEquals(defausse.getPileCarte().size(), 2);
        assertEquals(plateauService.getTaillePile(TypePile.VITESSE, j), 0);


        // Si le joueur possède la botte vehicule prioritaire
        Carte vehiculePrioritaire = new Carte(NomCarte.VITESSE, TypeCarte.BOTTE, 0);
        plateauService.ajouterCartePlateau(TypePile.VITESSE, carteAttaqueVitesse, j);
        plateauService.ajouterCartePlateau(TypePile.BOTTES, vehiculePrioritaire, j);
        IllegalArgumentException thrwon3 = Assertions
            .assertThrows(IllegalArgumentException.class, () -> joueurService.poserCarteVitesse(carteParadeVitesse, defausse, j));
        Assertions.assertEquals("Erreur : le joueur possède le véhicule prioritaire!", thrwon3.getMessage());

    }

    @Test
    void poserCarteParade(){
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Carte carteAttaqueCrevaison = new Carte(NomCarte.CREVAISON, TypeCarte.ATTAQUE, 1);
        Carte carteParadeCrevaison = new Carte(NomCarte.CREVAISON, TypeCarte.PARADE, 2);
        Carte carteParadeAccident = new Carte(NomCarte.ACCIDENT, TypeCarte.PARADE, 3);
        PileCarte defausse = new PileCarte();
        deckService.ajouter(carteAttaqueCrevaison, j);
        deckService.ajouter(carteParadeCrevaison, j);
        deckService.ajouter(carteParadeAccident, j);

        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carteAttaqueCrevaison, j);

        // Si la carte parade est de type différent de la carte sur la pile bataille
        IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> joueurService.poserCarteParade(carteParadeAccident, defausse, j));
        Assertions.assertEquals("Erreur: on ne peut pas poser une carte parade d'un nom différent que celui" +
            " la première carte sur la pile bataille!", thrown1.getMessage());

        plateauService.enleverCartePlateau(TypePile.BATAILLE, j);
        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carteParadeCrevaison, j);

        // Si il n'y a pas d'attaque à parer (la carte sur la pile bataille n'est pas une attaque)
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> joueurService.poserCarteParade(carteParadeCrevaison, defausse, j));
        Assertions.assertEquals("Erreur: on ne peut pas poser une carte parade si la première carte" +
            " sur le pile bataille n'est pas une carte attaque", thrown2.getMessage());

        plateauService.enleverCartePlateau(TypePile.BATAILLE, j);
        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carteAttaqueCrevaison, j);
        joueurService.poserCarteParade(carteParadeCrevaison, defausse, j);
        assertEquals(defausse.getPileCarte().size(), 2);
        assertEquals(plateauService.getTaillePile(TypePile.BATAILLE, j), 0);
    }

    @Test
    void poserCarteBorne(){
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Carte carteBorne25 = new Carte(NomCarte.VINGT_CINQ, TypeCarte.BORNE, 1);
        Carte carteBorne50 = new Carte(NomCarte.CINQUANTE, TypeCarte.BORNE, 2);
        Carte carteBorne75 = new Carte(NomCarte.SOIXANTE_QUINZE, TypeCarte.BORNE, 3);
        Carte carteBorne100 = new Carte(NomCarte.CENT, TypeCarte.BORNE, 4);
        Carte carteBorne200 = new Carte(NomCarte.DEUX_CENTS, TypeCarte.BORNE, 5);
        Carte carteVitesseAttaque = new Carte(NomCarte.VITESSE, TypeCarte.ATTAQUE, 6);
        Carte carteAccidentAttaque = new Carte(NomCarte.ACCIDENT, TypeCarte.ATTAQUE, 7);

        deckService.ajouter(carteBorne25, j);
        deckService.ajouter(carteBorne50, j);
        deckService.ajouter(carteBorne75, j);
        deckService.ajouter(carteBorne100, j);
        deckService.ajouter(carteBorne200, j);

        plateauService.ajouterCartePlateau(TypePile.VITESSE,carteVitesseAttaque, j);
        IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> joueurService.poserCarteBorne(carteBorne100, j));
        Assertions.assertEquals("Erreur : la carte borne ne peut être posée, en raison " +
            "d'une limitation de vitesse! ", thrown1.getMessage());
        joueurService.poserCarteBorne(carteBorne25, j);
        joueurService.poserCarteBorne(carteBorne50, j);
        plateauService.enleverCartePlateau(TypePile.VITESSE,j);
        assertEquals(j.getScore(),75);

        plateauService.ajouterCartePlateau(TypePile.BATAILLE,carteAccidentAttaque, j);
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> joueurService.poserCarteBorne(carteBorne50, j));
        Assertions.assertEquals("Erreur : la carte borne ne peut être posée, en raison " +
            "d'une carte attaque présente! ", thrown2.getMessage());
        plateauService.enleverCartePlateau(TypePile.BATAILLE,j);


        joueurService.poserCarteBorne(carteBorne75, j);
        assertEquals(j.getScore(),150);
        joueurService.poserCarteBorne(carteBorne100, j);
        assertEquals(j.getScore(),250);
        joueurService.poserCarteBorne(carteBorne200, j);
        assertEquals(j.getScore(),450);

        assertEquals(j.getPlateau().getPile(TypePile.BORNES).getPileCarte().size(),5);
    }

    @Test
    void poserCarteBotte(){
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Carte carteBotteAccident = new Carte(NomCarte.ACCIDENT, TypeCarte.BOTTE, 0);
        Carte carteBotteVitesse = new Carte(NomCarte.VITESSE, TypeCarte.BOTTE, 0);
        Carte carteAccidentAttaque = new Carte(NomCarte.ACCIDENT, TypeCarte.ATTAQUE, 0);
        Carte carteVitesseAttaque = new Carte(NomCarte.VITESSE, TypeCarte.ATTAQUE, 0);

        PileCarte defausse = new PileCarte();
        deckService.ajouter(carteBotteAccident, j);
        deckService.ajouter(carteBotteVitesse, j);

        plateauService.ajouterCartePlateau(TypePile.BATAILLE,carteAccidentAttaque, j);
        joueurService.poserCarteBotte(carteBotteAccident, j, defausse);
        assertEquals(j.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().size(), 0);

        plateauService.ajouterCartePlateau(TypePile.VITESSE,carteVitesseAttaque, j);
        System.out.println(j.getPlateau());
        joueurService.poserCarteBotte(carteBotteVitesse, j, defausse);
        assertEquals(j.getPlateau().getPile(TypePile.VITESSE).getPileCarte().size(), 0);

        assertEquals(j.getPlateau().getPile(TypePile.BOTTES).getPileCarte().size(), 2);
    }

    @Test
    void poser(){
        //TODO Check
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Carte carte2 = new Carte(NomCarte.VITESSE, TypeCarte.ATTAQUE, 2);
        Carte carte3 = new Carte(NomCarte.CREVAISON, TypeCarte.ATTAQUE, 3);
        Carte carte4 = new Carte(NomCarte.CREVAISON, TypeCarte.PARADE, 4);
        Carte carte5 = new Carte(NomCarte.DEUX_CENTS, TypeCarte.BORNE, 5);
        Carte carte6 = new Carte(NomCarte.ESSENCE, TypeCarte.BOTTE, 6);

        PileCarte defausse = new PileCarte();
        deckService.ajouter(carte1, j);

        // Si on pose une carte ATTAQUE -> erreur
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> joueurService.poser(carte2, defausse, j));
        Assertions.assertEquals("Erreur: on ne peut pas utiliser une carte attaque sur son plateau!", thrown.getMessage());

        plateauService.ajouterCartePlateau(TypePile.VITESSE, carte2, j);
        joueurService.poser(carte1,defausse, j);
        assertEquals(j.getMain().getMainJoueur().size(), 0);
        assertEquals(defausse.getPileCarte().size(),2);

        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carte3, j);
        deckService.ajouter(carte4, j);
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
        assertEquals(j.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().size(),0);
        assertEquals(j.getPlateau().getPile(TypePile.BORNES).getPileCarte().size(),1);
        assertEquals(j.getPlateau().getPile(TypePile.BOTTES).getPileCarte().size(),1);
    }

    @Test
    void attaquer() {
        //TODO Check
        Joueur j = new Joueur(1, "mayaSixtine", 24);

        Carte carteAttaque = new Carte(NomCarte.VITESSE, TypeCarte.ATTAQUE, 2);
        Carte carteAttaque2 = new Carte(NomCarte.CREVAISON, TypeCarte.ATTAQUE, 3);

        joueurService.attaquer(carteAttaque, j);
        assertEquals(j.getPlateau().getPile(TypePile.VITESSE).getPileCarte().size(), 1);
        joueurService.attaquer(carteAttaque2, j);
        assertEquals(j.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().size(), 1);
        assertFalse(joueurService.attaquer(carteAttaque2, j));
    }
}
