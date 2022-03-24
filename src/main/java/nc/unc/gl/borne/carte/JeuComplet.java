package nc.unc.gl.borne.carte;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class JeuComplet {

    /**
     * Le jeu
     */
    private ArrayList<Carte> jeuComplet;


    /**
     * Constructeur par défaut
     */
    public JeuComplet() {

        this.jeuComplet = new ArrayList<>();
        AtomicInteger iAtomic = new AtomicInteger();

        Arrays.asList(EnumCard.values())
            .forEach(carte -> {
                int nbCarte = carte.numberCard;
                for(int i =1; i<=nbCarte; i++) {
                    this.jeuComplet.add(new Carte(carte.nameCard, carte.typeCarte, iAtomic.incrementAndGet()));
                }});
    }

    public ArrayList<Carte> getJeuComplet() {
        return jeuComplet;
    }

    public void setJeuComplet(ArrayList<Carte> jeuComplet) {
        this.jeuComplet = jeuComplet;
    }

    public int taille(){
        return this.jeuComplet.size();
    }

    @Override
    public String toString() {
        for(int i=0; i<jeuComplet.size(); i++){
            System.out.println(jeuComplet.get(i));
        }
        return "";
    }
}
