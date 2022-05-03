package nc.unc.gl.borne.views;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.partie.Partie;

@Tag("game")
public class GameView extends HtmlContainer {

    public GameView(Partie partie, Joueur joueur) {
        add("Joueur 1: " + partie.getListejoueur().get(0).getPseudo());
        add("Joueur 2: " + partie.getListejoueur().get(1).getPseudo());
        add("fenétre du joueur" + joueur.getPseudo());
    }
}
