package nc.unc.gl.borne.carte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

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

    public ArrayList<Carte> getJeuComplet() {
        return jeuComplet;
    }

    public void setJeuComplet(ArrayList<Carte> jeuComplet) {
        this.jeuComplet = jeuComplet;
    }
}
