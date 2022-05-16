package nc.unc.gl.borne;

import nc.unc.gl.borne.metier.classes.partie.Partie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class MilleBornesApplication {

    public static ArrayList<Partie> partieList = new ArrayList<>();

    public static ArrayList<Partie> getPartieList() {
        return partieList;
    }

    public static Partie  getParties(String idPartie) {
        for (Partie partie : getPartieList()) {
            if (partie.getId().equals(idPartie)) {
                return partie;
            }
        }
        return null;
    }

    public static void addPartieList(Partie partie) {
        getPartieList().add(partie);
    }

    public static void main(String[] args) {
        SpringApplication.run(MilleBornesApplication.class, args);
    }
}
