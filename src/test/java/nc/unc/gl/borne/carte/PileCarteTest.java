package nc.unc.gl.borne.carte;

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
        assertEquals(unePile.getCarte(0), unePile.getCarte(carte1));
        assertEquals(unePile.getCarte(1), unePile.getCarte(carte2));
    }

    @Test
    void enlever(){
        //Avec une erreur
        PileCarte unePile = new PileCarte();
        assertThrows(IllegalArgumentException.class, unePile::enlever);

        //Sans erreur
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        unePile.ajouter(carte1).enlever();
        assertEquals(unePile, new PileCarte());
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
//        assertThrows(IllegalArgumentException.class, unePile.getCarte(carte1));
    }
}
