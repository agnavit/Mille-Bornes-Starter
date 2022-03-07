package nc.unc.gl.borne.carte;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Description des test de la classe PileCarte
 */
public class PileCarteTest {

    @Test
    void ajouter(){
        PileCarte unePile = new PileCarte();
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Carte carte2 = new Carte(NomCarte.CENT, TypeCarte.BORNE, 2);
        unePile.ajouter(carte1).ajouter(carte2);
        assertEquals(unePile.taille(), 2);

        // On vérifie que les cartes ont été ajoutées dans le bon ordre (ajout à droite dans la liste)
        assertEquals(unePile.getCarte(0), unePile.getCarte(carte1));
        assertEquals(unePile.getCarte(1), unePile.getCarte(carte2));
    }

    @Test
    void enlever(){
        // Enlever une carte dans une pile vide
        PileCarte unePile = new PileCarte();
        assertThrows(IllegalArgumentException.class, unePile::enlever);

        // Enlever une carte sans erreur
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        unePile.ajouter(carte1).enlever();
        assertEquals(unePile, new PileCarte());
        assertEquals(unePile.taille(),0);
    }

    @Test
    void getCarte(){
        PileCarte unePile = new PileCarte();
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Carte carte2 = new Carte(NomCarte.CENT, TypeCarte.BORNE, 2);
        unePile.ajouter(carte1).ajouter(carte2);
        assertEquals(unePile.getCarte(0), carte1);
        assertEquals(unePile.getCarte(carte2), carte2);
        unePile.enlever();

        // Demander une carte pas présente dans la pile
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> unePile.getCarte(carte1));
        Assertions.assertEquals("Erreur : la carte demandée n'est pas présente dans la pile!", thrown.getMessage());
    }
}
