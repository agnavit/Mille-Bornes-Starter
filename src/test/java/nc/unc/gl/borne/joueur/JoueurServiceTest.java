package nc.unc.gl.borne.joueur;

import nc.unc.gl.borne.carte.*;
import nc.unc.gl.borne.plateau.PlateauService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class JoueurServiceTest {

    public JoueurService joueurService = new JoueurService();
    public PlateauService plateauService = new PlateauService();

    @Test
    void jeter(){
        /*On va vérifier qu'une carte jetée n'est plus présente dans la main du joueur
        Et vérifier que cette carte est au sommet de la pile défausse
         */
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Deck mainJoueur = new Deck();
        PileCarte defausse = new PileCarte();
        mainJoueur.ajouter(carte1);
        j.setMain(mainJoueur);
        joueurService.jeter(carte1, defausse, j);
        assertEquals(defausse.getSommet(), carte1);
        assertEquals(j.getMain().taille(), 0);
        assertFalse(j.getMain().contains(carte1));

    }

    @Test
    void poserVitesse(){
        //TODO
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Carte carte2 = new Carte(NomCarte.VITESSE, TypeCarte.ATTAQUE, 2);
        Deck mainJoueur = new Deck();
        PileCarte defausse = new PileCarte();
        mainJoueur.ajouter(carte1).ajouter(carte2);
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
        assertEquals(defausse.taille(), 2);
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
        PileCarte defausse = new PileCarte();
        mainJoueur.ajouter(carte1).ajouter(carte2).ajouter(carte3);
        j.setMain(mainJoueur);

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
        assertEquals(defausse.taille(), 2);
        assertEquals(plateauService.getTaillePile(TypePile.BATAILLE, j), 0);

        // Si il n'y a pas d'erreur et que la carte parade a pour nom FEU
        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carte4, j);
        joueurService.poserCarteParade(carte5, defausse, j);
        assertEquals(defausse.taille(), 3);
        assertEquals(plateauService.getTaillePile(TypePile.BATAILLE, j), 1);
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
        mainJoueur.ajouter(carte1).ajouter(carte2).ajouter(carte3).ajouter(carte4).ajouter(carte5);
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
        assertEquals(j.getPlateau().getPile(TypePile.BORNES).taille(),5);
    }

    @Test
    void poserBotte(){
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Carte carte1 = new Carte(NomCarte.CAMION_CITERNE, TypeCarte.BORNE, 1);
        Carte carte2 = new Carte(NomCarte.VEHICULE_PRIORITAIRE, TypeCarte.BORNE, 2);
        Deck mainJoueur = new Deck();
        PileCarte defausse = new PileCarte();
        mainJoueur.ajouter(carte1).ajouter(carte2);
        j.setMain(mainJoueur);

        joueurService.poserCarteBotte(carte1, j);
        joueurService.poserCarteBotte(carte2, j);
        assertEquals(j.getPlateau().getPile(TypePile.BOTTES).taille(), 2);
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
        mainJoueur.ajouter(carte1);
//        .ajouter(carte2).ajouter(carte3).ajouter(carte4).ajouter(carte5).ajouter(carte6)
        j.setMain(mainJoueur);

        // Si on pose une carte ATTAQUE -> erreur
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> joueurService.poser(carte2, defausse, j));
        Assertions.assertEquals("Erreur: on ne peut pas utiliser une carte attaque sur son plateau!", thrown.getMessage());

        plateauService.ajouterCartePlateau(TypePile.VITESSE, carte2, j);
        joueurService.poser(carte1,defausse, j);
        assertEquals(j.getMain().taille(), 0);
        assertEquals(defausse.taille(),2);

        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carte3, j);
        j.setMain(mainJoueur.ajouter(carte4));
        joueurService.poser(carte4, defausse, j);
        assertEquals(j.getMain().taille(), 0);
        assertEquals(defausse.taille(),4);

        j.setMain(mainJoueur.ajouter(carte5).ajouter(carte6));
        joueurService.poser(carte5, defausse, j);
        joueurService.poser(carte6, defausse, j);
        assertEquals(j.getMain().taille(), 0);
        assertEquals(defausse.taille(),4);

        assertEquals(j.getPlateau().getPile(TypePile.VITESSE).taille(),0);
        assertEquals(j.getPlateau().getPile(TypePile.BATAILLE).taille(),0);
        assertEquals(j.getPlateau().getPile(TypePile.BORNES).taille(),1);
        assertEquals(j.getPlateau().getPile(TypePile.BOTTES).taille(),1);
    }
}
