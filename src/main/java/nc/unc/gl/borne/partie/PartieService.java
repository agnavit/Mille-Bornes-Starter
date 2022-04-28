package nc.unc.gl.borne.partie;

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
}
