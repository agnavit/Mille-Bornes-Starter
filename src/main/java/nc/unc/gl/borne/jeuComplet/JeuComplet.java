package nc.unc.gl.borne.jeuComplet;

import lombok.Data;
import nc.unc.gl.borne.carte.Carte;
import nc.unc.gl.borne.carte.EnumCard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class JeuComplet {

    private ArrayList<Carte> jeuComplet;

    public JeuComplet() {
        ArrayList<Carte> jeu = new ArrayList<>();

        AtomicInteger i = new AtomicInteger();

        Arrays.asList(EnumCard.values())
            .forEach(carte -> {
                for(int j =0;j<=109;j++) {
                    jeu.add(new Carte(carte.nameCard, carte.typeCarte, i.incrementAndGet()));
                }});
    }
}
