package nc.unc.gl.borne.views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.joueur.Joueur;

@Route("testFooterLayout")
public class FooterLayout extends HorizontalLayout {

    Joueur player = new Joueur(2, "Anthony", 22);

    public FooterLayout(){
        DeckLayout deckLayout = new DeckLayout(player);
        ActionLayout actionLayout = new ActionLayout();

        add(deckLayout,actionLayout);
    }
}
