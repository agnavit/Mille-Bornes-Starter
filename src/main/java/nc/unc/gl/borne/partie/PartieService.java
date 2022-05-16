package nc.unc.gl.borne.partie;

import nc.unc.gl.borne.MilleBornesApplication;
import nc.unc.gl.borne.dao.connection.partieDao.JoueurDao;
import nc.unc.gl.borne.jeuComplet.JeuComplet;
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.joueur.JoueurService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class PartieService extends Thread {

    public JoueurService joueurService = new JoueurService();
    public JoueurDao joueurDao = new JoueurDao();

    public void lancerPartie(Partie partie) {
        AtomicInteger score = new AtomicInteger();
        JeuComplet jeuComplet = new JeuComplet();

        partie.getPioche().setPileCarte(jeuComplet.getJeuComplet());
        partie.getListejoueur().forEach(j -> j.getMain().getMainJoueur().add(partie.getPioche().depiler()));
        partie.getPioche().melanger();
        distribuerCarte(partie);
        determinerOrdrePassage(partie);
        while (partie.getPioche().getPileCarte().size() != 0 || score.get() != 1000) {
            partie.getListejoueur().forEach(j -> {
                try {
                    joueurService.jouer(partie.getPioche(), j);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                score.set(j.getScore());
            });
        }
    }

    private void determinerOrdrePassage(Partie partie) {
        ArrayList<Integer> listeAge = new ArrayList<>();
        partie.getListejoueur().forEach(j -> listeAge.add(j.getAge()));
        Collections.sort(listeAge);
        ArrayList<Joueur> listejoueurTrie = new ArrayList<>();
        listeAge.forEach(age -> partie.getListejoueur().forEach(j -> {
            if (j.getAge() == age) {
                listejoueurTrie.add(j);
            }
        }));
        partie.setListejoueur(listejoueurTrie);
    }

    private void distribuerCarte(Partie partie) {
        while (partie.getListejoueur().get(0).getMain().getMainJoueur().size() != 6) {
            for (Joueur j : partie.getListejoueur()) {
                joueurService.piocher(partie.getPioche(), j);
            }
        }
    }

    public Partie getPartieJoueur(Joueur joueur, ArrayList<Partie> parties) {
        for (Partie partie : parties) {
            for (Joueur j : partie.getListejoueur()) {
                if (j.getPseudo().equals(joueur.getPseudo())) {
                    return partie;
                }
            }
        }
        return new Partie();
    }

    public void connectJoueur(Partie partie, Joueur joueur) {
        partie.getListejoueur().add(joueur);
        if (partie.getNbJoueurMax() == partie.getListejoueur().size()) {
            joueurDao.updatePartieJoueur(partie.getId(), joueur);
        }
    }

    public void run() {
        lancerPartie(MilleBornesApplication.getPartieList().get(0));
    }

}
