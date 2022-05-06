package nc.unc.gl.borne.carte;

import nc.unc.gl.borne.carte.enumerations.NomCarte;
import nc.unc.gl.borne.carte.enumerations.TypeCarte;
import nc.unc.gl.borne.carte.enumerations.TypePile;
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.plateau.Plateau;
import nc.unc.gl.borne.plateau.PlateauService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlateauTest {

    public PlateauService plateauService = new PlateauService();

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
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Plateau plateau = new Plateau();
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carte1, j);
        assertEquals(j.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().size(),2);
        assertEquals(j.getPlateau().getPile(TypePile.BATAILLE).getSommet(), carte1);
    }

    @Test
    void enleverCartePlateau(){
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Plateau plateau = new Plateau();
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Carte carte2 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 2);
        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carte1, j);
        plateauService.ajouterCartePlateau(TypePile.BATAILLE,carte2, j);
        plateauService.enleverCartePlateau(TypePile.BATAILLE, j);
        assertEquals(j.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().size(),2);
        assertEquals(j.getPlateau().getPile(TypePile.BATAILLE).getSommet(), carte1);
        plateauService.enleverCartePlateau(TypePile.BATAILLE, j);
        assertEquals(j.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().size(),1);
    }

    @Test
    void enleverCartesAttaqueEtParadePile(){
        Joueur j = new Joueur(1, "mayaSixtine", 24);
        Plateau plateau = new Plateau();
        PileCarte defausse = new PileCarte();
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.ATTAQUE, 1);
        Carte carte2 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 2);
        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carte1, j);
        plateauService.ajouterCartePlateau(TypePile.BATAILLE,carte2, j);
        plateauService.enleverCartesAttaqueEtParadePile(TypePile.BATAILLE, defausse, j);
        assertEquals(j.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().size(), 1);
        assertEquals(defausse.getPileCarte().size(),2);

        IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> plateauService.enleverCartesAttaqueEtParadePile(TypePile.BOTTES,defausse, j));
        Assertions.assertEquals("Erreur : le type de pile pour utiliser cette fonction doit être Bataille ou Vitesse", thrown1.getMessage());
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> plateauService.enleverCartesAttaqueEtParadePile(TypePile.BORNES,defausse, j));
        Assertions.assertEquals("Erreur : le type de pile pour utiliser cette fonction doit être Bataille ou Vitesse", thrown2.getMessage());
    }
}
