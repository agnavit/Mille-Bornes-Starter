package nc.unc.gl.borne.carte;
import nc.unc.gl.borne.carte.enumerations.NomCarte;
import nc.unc.gl.borne.carte.enumerations.TypeCarte;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarteTest {
    @Test
    void equals(){
        Carte c1 = new Carte(NomCarte.VEHICULE_PRIORITAIRE, TypeCarte.BOTTE, 1);
        Carte c2 = new Carte(NomCarte.VEHICULE_PRIORITAIRE, TypeCarte.BOTTE, 2);
        assertEquals(c1,c2);
    }

    @Test
    void getStringImage(){
        Carte c1 = new Carte(NomCarte.ACCIDENT, TypeCarte.ATTAQUE, 1);
        assertEquals(c1.getStringImage(),"attaque_accident.jpeg");
    }
}
