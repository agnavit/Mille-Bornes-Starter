package nc.unc.gl.borne.deck;

import nc.unc.gl.borne.Deck.Deck;
import nc.unc.gl.borne.Deck.DeckService;
import nc.unc.gl.borne.carte.Carte;
import nc.unc.gl.borne.carte.enumerations.NomCarte;
import nc.unc.gl.borne.carte.enumerations.TypeCarte;
import nc.unc.gl.borne.joueur.Joueur;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    public DeckService deckService = new DeckService();
    Joueur j = new Joueur(1, "mayaSixtine", 24);

    @Test
    void ajouter(){
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Deck deck = new Deck();
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte1, j);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> deckService.ajouter(carte1, j));
        Assertions.assertEquals("Erreur: la main d'un joueur peut contenir au plus 7 cartes!", thrown.getMessage());
    }

    @Test
    void enlever(){
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Deck deck = new Deck();
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte1, j);
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> deckService.enlever(carte1, j));
        Assertions.assertEquals("Erreur: la main d'un joueur peut contenir au moins 6 cartes!", thrown.getMessage());
    }

    @Test
    void contains(){
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Carte carte2 = new Carte(NomCarte.VITESSE, TypeCarte.ATTAQUE, 1);
        Deck deck = new Deck();
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte1, j);
        deckService.ajouter(carte1, j);
        assertTrue(deckService.contains(carte1, j));
        assertFalse(deckService.contains(carte2, j));
    }
}
