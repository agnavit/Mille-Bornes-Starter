package nc.unc.gl.borne.classes.carte;

import java.util.List;

/**
 * Description d'une composition de carte du jeu du 1000 bornes
 *
 * Notamment pour créer les instances suivantes:
 * - la main d'un joueur ("deck")
 * - la pile de pioche (où on pioche une carte)
 * - la pile de défausse (où on jette une carte)
 * - les piles formant le "plateau" (cartes visibles) d'un joueur
 *      - la pile de vitesse
 *      - la pile de bataille
 *      - les piles de bornes (25, 50, 75, 100, 200)
 *      - la(les) pile(s) d'une(des) botte(s)
 */
public class CompositionCarte {
    /**
     * Liste de cartes formant un regroupement de cartes
     */
    protected List<Carte> jeu;

    /**
     * Taille de la liste précédente
     */
    protected int tailleJeu;

    /**
     * Constructeur
     * @param jeu une liste de carte
     */
    public CompositionCarte(List<Carte> jeu) {
        this.jeu = jeu;
        this.tailleJeu = jeu.size();
    }

    @Override
    public String toString() {
        return "CompositionCarte{" +
            "jeu=" + jeu +
            ", tailleJeu=" + tailleJeu +
            '}';
    }

    /**
     * Ajoute une carte
     * @param newCarte la carte à ajouter
     */
    public void ajouterCarte(Carte newCarte){
        this.jeu.add(newCarte);
    }

    /**
     * Enlève une carte
     * @param delCarte la carte à enlever
     * @return true si la carte est enlevée ou false sinon (i.e la carte n'est pas présente)
     */
    public boolean enleverCarte(Carte delCarte){
        // Renvoie True si la carte est enlevée et false sinon
        return this.jeu.remove(delCarte);

    }

    public List<Carte> getJeu() {
        return jeu;
    }

    public int getTailleJeu() {
        return tailleJeu;
    }
}
