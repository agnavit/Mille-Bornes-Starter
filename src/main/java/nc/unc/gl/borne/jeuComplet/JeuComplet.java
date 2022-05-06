package nc.unc.gl.borne.jeuComplet;

import lombok.Data;
import nc.unc.gl.borne.carte.Carte;
import nc.unc.gl.borne.carte.enumerations.CartesJeu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class JeuComplet {

    private ArrayList<Carte> jeuComplet;

    public JeuComplet() {
        this.jeuComplet = new ArrayList<Carte>();
        AtomicInteger i = new AtomicInteger();

        Arrays.asList(CartesJeu.values())
            .forEach(carte -> {
                for(int j = 1; j <= carte.nombreCartes; j++) {
                    this.jeuComplet.add(new Carte(carte.nom, carte.type, i.incrementAndGet()));
                }});
    }
}
