package nc.unc.gl.borne.carte;

import nc.unc.gl.borne.carte.PileCarte;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * Description des test de la classe PileCarte
 */
public class PileCarteTest {

    @Test
    public void ajouter(){
        PileCarte unePile = new PileCarte();
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Carte carte2 = new Carte(NomCarte.CENT, TypeCarte.BORNE, 2);
        unePile.ajouter(carte1);
        unePile.ajouter(carte2);
        // TODO : Ajout equals() dans la classe Carte
        assert unePile.getCarte(0) == unePile.getCarte(carte1);
        assert unePile.getCarte(1) == unePile.getCarte(carte2);
    }

    @Test
    void enlever(){
        //TODO
    }

    public static void main(String[] args){


    }
}
