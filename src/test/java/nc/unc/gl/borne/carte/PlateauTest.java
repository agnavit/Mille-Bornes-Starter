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
        Joueur joueur = new Joueur(1, "mayaSixtine", 24);
        Carte carteParadeVitesse = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 0);
        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carteParadeVitesse, joueur);
        assertEquals(joueur.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().size(),1);
        assertEquals(joueur.getPlateau().getPile(TypePile.BATAILLE).getSommet(), carteParadeVitesse);
    }

    @Test
    void enleverCartePlateau(){
        Joueur joueur = new Joueur(1, "mayaSixtine", 24);
        Carte carteParadeVitesse = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 0);
        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carteParadeVitesse, joueur);
        plateauService.ajouterCartePlateau(TypePile.BATAILLE,carteParadeVitesse, joueur);
        plateauService.enleverCartePlateau(TypePile.BATAILLE, joueur);
        assertEquals(joueur.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().size(),1);
        assertEquals(joueur.getPlateau().getPile(TypePile.BATAILLE).getSommet(), carteParadeVitesse);
        plateauService.enleverCartePlateau(TypePile.BATAILLE, joueur);
        assertEquals(joueur.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().size(),0);
    }

    @Test
    void enleverCartesAttaqueEtParadePile(){
        Joueur joueur = new Joueur(1, "mayaSixtine", 24);
        PileCarte defausse = new PileCarte();
        Carte carteAttaqueVitesse = new Carte(NomCarte.VITESSE, TypeCarte.ATTAQUE, 0);
        Carte carteParadeVitesse = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 0);
        plateauService.ajouterCartePlateau(TypePile.BATAILLE, carteAttaqueVitesse, joueur);
        plateauService.ajouterCartePlateau(TypePile.BATAILLE,carteParadeVitesse, joueur);
        plateauService.enleverCartesAttaqueEtParadePile(TypePile.BATAILLE, defausse, joueur);
        assertEquals(joueur.getPlateau().getPile(TypePile.BATAILLE).getPileCarte().size(), 0);
        assertEquals(defausse.getPileCarte().size(),2);

        IllegalArgumentException thrown1 = Assertions.assertThrows(IllegalArgumentException.class, () -> plateauService.enleverCartesAttaqueEtParadePile(TypePile.BOTTES,defausse, joueur));
        Assertions.assertEquals("Erreur : le type de pile pour utiliser cette fonction doit être Bataille ou Vitesse", thrown1.getMessage());
        IllegalArgumentException thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> plateauService.enleverCartesAttaqueEtParadePile(TypePile.BORNES,defausse, joueur));
        Assertions.assertEquals("Erreur : le type de pile pour utiliser cette fonction doit être Bataille ou Vitesse", thrown2.getMessage());
    }
}
