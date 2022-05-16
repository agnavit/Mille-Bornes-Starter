package nc.unc.gl.borne.metier.classes;

import nc.unc.gl.borne.metier.classes.JeuComplet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JeuCompletTest {

    @Test
    void jeuComplet(){
        JeuComplet jeu = new JeuComplet();
        assertEquals(jeu.getJeuComplet().size(),106);
    }
}
