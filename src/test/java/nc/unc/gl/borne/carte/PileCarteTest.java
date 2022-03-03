package nc.unc.gl.borne.carte;

import org.junit.jupiter.api.Test;

/**
 * Description des test de la classe PileCarte
 */
public class PileCarteTest {

    @Test
    public static void ajouter(){
        PileCarte unePile = new PileCarte();
        Carte carte1 = new Carte(NomCarte.VITESSE, TypeCarte.PARADE, 1);
        Carte carte2 = new Carte(NomCarte.CENT, TypeCarte.BORNE, 2);
        unePile.ajouter(carte1);
        unePile.ajouter(carte2);
        System.out.println(unePile.getCarte(0).equals(unePile.getCarte(carte1)));
        System.out.println(unePile.getCarte(1).equals(unePile.getCarte(carte2)));
    }

    @Test
    void enlever(){
        //TODO
    }

    public static void main(String[] args){
        ajouter();

    }
}
