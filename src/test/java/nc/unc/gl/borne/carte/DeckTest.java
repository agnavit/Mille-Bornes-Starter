package nc.unc.gl.borne.carte;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {
    @Test
    void ajouter(){
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Deck deck = new Deck();
        deck.ajouter(carte1).ajouter(carte1).ajouter(carte1).ajouter(carte1).ajouter(carte1).ajouter(carte1).ajouter(carte1);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> deck.ajouter(carte1));
        Assertions.assertEquals("Erreur: la main d'un joueur ne doit pas contenir au plus 7 cartes!", thrown.getMessage());
    }

    @Test
    void enlever(){
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Deck deck = new Deck();
        deck.ajouter(carte1).ajouter(carte1).ajouter(carte1).ajouter(carte1).ajouter(carte1).ajouter(carte1);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> deck.enlever(carte1));
        Assertions.assertEquals("Erreur: la main d'un joueur doit contenir au moins 6 cartes!", thrown.getMessage());
    }

    @Test
    void contains(){
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Carte carte2 = new Carte(NomCarte.VITESSE, TypeCarte.ATTAQUE, 1);
        Deck deck = new Deck();
        deck.ajouter(carte1).ajouter(carte1).ajouter(carte1).ajouter(carte1);
        assertTrue(deck.contains(carte1));
        assertFalse(deck.contains(carte2));
    }
}
