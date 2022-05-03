package nc.unc.gl.borne.partie;

import nc.unc.gl.borne.carte.Carte;
import nc.unc.gl.borne.jeuComplet.JeuComplet;
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.joueur.JoueurService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class PartieService {

    public JoueurService joueurService = new JoueurService();

    public void lancerPartie(Partie partie) {
        AtomicInteger score = new AtomicInteger();
        JeuComplet jeuComplet = new JeuComplet();

        partie.getPioche().setPileCarte(jeuComplet.getJeuComplet());
        //distribution de feu vert à mes joueur
        partie.getListejoueur().forEach(j -> {
            j.getMain().getMainJoueur().add(partie.getPioche().depiler());
        });
        partie.getPioche().melangerPioche();
        distribuerCarte(partie);
        determinerOrdrePassage(partie);
        while (partie.getPioche().getPileCarte().size() != 0 || score.get() != 1000) {
            partie.getListejoueur().forEach(j -> {
                joueurService.jouer(partie.getPioche(), j);
                score.set(j.getScore());
            });
        }
    }

    private void determinerOrdrePassage(Partie partie) {
        ArrayList<Integer> listeAge = new ArrayList<>();
        partie.getListejoueur().forEach(j -> {
            listeAge.add(j.getAge());
        });
        Collections.sort(listeAge);
        ArrayList<Joueur> listejoueurTrie = new ArrayList<>();
        listeAge.forEach(age -> {
            partie.getListejoueur().forEach(j -> {
                if (j.getAge() == age) {
                    listejoueurTrie.add(j);
                }
            });
        });
        partie.setListejoueur(listejoueurTrie);
    }

    private void distribuerCarte(Partie partie) {
        while (partie.getListejoueur().get(0).getMain().getMainJoueur().size() != 6) {
            partie.getListejoueur().forEach(j -> {
                joueurService.piocher(partie.getPioche(), j);
            });
        }
    }

    public void connectJoueur(Partie partie, Joueur joueur) {
        partie.getListejoueur().add(joueur);
    }
}
