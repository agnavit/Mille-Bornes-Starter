package nc.unc.gl.borne.carte;

import nc.unc.gl.borne.carte.enumerations.NomCarte;
import nc.unc.gl.borne.carte.enumerations.TypeCarte;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PileCarteTest {

    @Test
    void empiler(){
        PileCarte unePile = new PileCarte();
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Carte carte2 = new Carte(NomCarte.CENT, TypeCarte.BORNE, 2);
        unePile.empiler(carte1).empiler(carte2);
        assertEquals(unePile.getPileCarte().size(), 2);

        // On vérifie que les cartes ont été ajoutées dans le bon ordre (ajout à droite dans la liste)
        assertEquals(unePile.getCarte(0), unePile.getCarte(carte1));
        assertEquals(unePile.getCarte(1), unePile.getCarte(carte2));
        assertEquals(unePile.getSommet(), carte2);
    }

    @Test
    void depiler(){
        // Enlever une carte dans une pile vide
        PileCarte unePile = new PileCarte();
        assertThrows(IllegalArgumentException.class, unePile::depiler);

        // Enlever une carte sans erreur
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        unePile.empiler(carte1).depiler();
        assertEquals(unePile, new PileCarte());
        assertEquals(unePile.getPileCarte().size(),0);
    }

    @Test
    void getCarte(){
        PileCarte unePile = new PileCarte();
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Carte carte2 = new Carte(NomCarte.CENT, TypeCarte.BORNE, 2);
        unePile.empiler(carte1).empiler(carte2);
        assertEquals(unePile.getCarte(0), carte1);
        assertEquals(unePile.getCarte(carte2), carte2);
        unePile.depiler();

        // Demander une carte pas présente dans la pile
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> unePile.getCarte(carte2));
        Assertions.assertEquals("Erreur : la carte demandée n'est pas présente dans la pile!", thrown.getMessage());
    }

    @Test
    void getNbCartePile(){
        PileCarte pile = new PileCarte();
        pile.empiler(new Carte(NomCarte.FEU, TypeCarte.PARADE, 1));
        pile.empiler(new Carte(NomCarte.FEU, TypeCarte.PARADE, 2));
        pile.empiler(new Carte(NomCarte.FEU, TypeCarte.ATTAQUE, 3));
        pile.empiler(new Carte(NomCarte.FEU, TypeCarte.PARADE, 4));
        assertEquals(pile.getNbCartePile(new Carte(NomCarte.FEU, TypeCarte.PARADE, 1)), 3);
        assertEquals(pile.getNbCartePile(new Carte(NomCarte.FEU, TypeCarte.ATTAQUE, 3)), 1);
    }
}
