package nc.unc.gl.borne.views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.Deck.DeckService;
import nc.unc.gl.borne.carte.Carte;
import nc.unc.gl.borne.carte.NomCarte;
import nc.unc.gl.borne.carte.TypeCarte;
import nc.unc.gl.borne.joueur.Joueur;

@Route("testFooterLayout")
public class FooterLayout extends HorizontalLayout {

    // Les 4 lignes seront supprimé elles ne servent pas
    protected DeckService deckPlayer = new DeckService();
    Carte carte = new Carte(NomCarte.ACCIDENT, TypeCarte.ATTAQUE, 1);
    Carte carte2 = new Carte(NomCarte.ESSENCE, TypeCarte.ATTAQUE, 2);
    Carte carte3 = new Carte(NomCarte.DEUX_CENTS, TypeCarte.BORNE, 3);



    Joueur player = new Joueur(2, "Anthony", 22);

    public FooterLayout(){

        // Idem pour les 3 lignes suivantes
        deckPlayer.ajouter(carte, player);
        deckPlayer.ajouter(carte2, player);
        deckPlayer.ajouter(carte3, player);

        DeckLayout deckLayout = new DeckLayout(player);
        ActionLayout actionLayout = new ActionLayout();

        HorizontalLayout footerLayout = new HorizontalLayout(deckLayout, actionLayout);
        footerLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        add(footerLayout);
    }
}
