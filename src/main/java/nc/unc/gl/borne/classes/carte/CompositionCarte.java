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

    public void ajouterCarte(Carte newCarte){
        this.jeu.add(newCarte);
    }

    public void enelverCarte(Carte delCarte){
        // ATTENTION : ne pas enlever une carte qui n'existe pas dans le jeu
        if(!this.jeu.contains(delCarte)){
            throw new IllegalAccessError("Erreur: la carte " + delCarte + " n'est pas présente dans le jeu " + this.jeu);
        }
        // Boucle qui s'arrête lorsqu'on a trouvé et enlevé la première occurence de la carte à enlever
        boolean fin = false;
        int i = 0;
        while(!fin && i<this.tailleJeu){
            if (this.jeu.get(i).equals(delCarte)) {
                this.jeu.remove(delCarte);
                fin = true;
            }
            i += 1;
        }
    }

    public List<Carte> getJeu() {
        return jeu;
    }

    public int getTailleJeu() {
        return tailleJeu;
    }
}
