package nc.unc.gl.borne.carte;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlateauTest {
    @Test
    void getPile(){
        Plateau plateau = new Plateau();
        assertEquals(plateau.getPile(TypePile.BATAILLE), new PileCarte());
        assertEquals(plateau.getPile(TypePile.BOTTES), new PileCarte());
        assertEquals(plateau.getPile(TypePile.VITESSE), new PileCarte());
        assertEquals(plateau.getPile(TypePile.BATAILLE), new PileCarte());
    }

    @Test
    void ajouterCartePlateau(){
        Plateau plateau = new Plateau();
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        plateau.ajouterCartePlateau(TypePile.BATAILLE, carte1);
        assertEquals(plateau.getPile(TypePile.BATAILLE).taille(),1);
        assertEquals(plateau.getPile(TypePile.BATAILLE).getSommet(), carte1);
    }

    @Test
    void enleverCartePlateau(){
        Plateau plateau = new Plateau();
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Carte carte2 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 2);
        plateau.ajouterCartePlateau(TypePile.BATAILLE, carte1).ajouterCartePlateau(TypePile.BATAILLE,carte2);
        plateau.enleverCartePlateau(TypePile.BATAILLE);
        assertEquals(plateau.getPile(TypePile.BATAILLE).taille(),1);
        assertEquals(plateau.getPile(TypePile.BATAILLE).getSommet(), carte1);
        plateau.enleverCartePlateau(TypePile.BATAILLE);
        assertEquals(plateau.getPile(TypePile.BATAILLE).taille(),0);
    }

    @Test
    void enleverCartesAttaqueEtParadePile(){
        Plateau plateau = new Plateau();
        PileCarte defausse = new PileCarte();
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.ATTAQUE, 1);
        Carte carte2 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 2);
        plateau.ajouterCartePlateau(TypePile.BATAILLE, carte1).ajouterCartePlateau(TypePile.BATAILLE,carte2);
        plateau.enleverCartesAttaqueEtParadePile(TypePile.BATAILLE, defausse);
        assertEquals(plateau.getPile(TypePile.BATAILLE).taille(), 0);
        assertEquals(defausse.taille(),2);

        IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> plateau.enleverCartesAttaqueEtParadePile(TypePile.BOTTES,defausse));
        Assertions.assertEquals("Erreur : le type de pile pour utiliser cette fonction doit être Bataille ou Vitesse", thrown1.getMessage());
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> plateau.enleverCartesAttaqueEtParadePile(TypePile.BORNES,defausse));
        Assertions.assertEquals("Erreur : le type de pile pour utiliser cette fonction doit être Bataille ou Vitesse", thrown2.getMessage());
    }
}
