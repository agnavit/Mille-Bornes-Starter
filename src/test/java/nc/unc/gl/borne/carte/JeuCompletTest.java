package nc.unc.gl.borne.carte;

import nc.unc.gl.borne.jeuComplet.JeuComplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JeuCompletTest {

    @Test
    void jeuComplet(){
        JeuComplet jeu = new JeuComplet();
        System.out.println(jeu);
        assertEquals(jeu.getJeuComplet().size(),106);
    }
}
