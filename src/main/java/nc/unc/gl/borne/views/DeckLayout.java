package nc.unc.gl.borne.views;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import nc.unc.gl.borne.Deck.DeckService;
import nc.unc.gl.borne.carte.Carte;
import nc.unc.gl.borne.carte.NomCarte;
import nc.unc.gl.borne.carte.TypeCarte;
import nc.unc.gl.borne.joueur.Joueur;

@StyleSheet("css/decklayout.css")
public class DeckLayout extends VerticalLayout {

    public DeckLayout(Joueur player){

        Image[] deckPlayerImage = new Image[player.getMain().getMainJoueur().size()];

        for(Integer i = 0; i < player.getMain().getMainJoueur().size(); i++){
            deckPlayerImage[i] = new Image(
                "cartes/"+player.getMain().getMainJoueur().get(i).getStringImage(),
                player.getMain().getMainJoueur().get(i).getStringImage());
            deckPlayerImage[i].addClassName("image-deck");
        }

        HorizontalLayout deckLayout = new HorizontalLayout(deckPlayerImage);
        add(deckLayout);
    }

    //TODO
    //    public void setDeckPlayer(Carte cartePiochee) {
    //        this.deckPlayer[6] = cartePiochee;
    //    }
}
