package nc.unc.gl.borne.views;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import nc.unc.gl.borne.joueur.Joueur;

@StyleSheet("css/decklayout.css")
public class DeckLayout extends VerticalLayout {

    public DeckLayout(Joueur player){

        Image[] deckPlayerImage = new Image[player.getMain().getMainJoueur().size()];

        //TODO: getSizeDeck() -> player.getMain().getMainJoueur().size()
        for(Integer i = 0; i < player.getMain().getMainJoueur().size(); i++){

            //TODO: player.getCardImageName(i)
            deckPlayerImage[i] = new Image(
                "cartes/"+player.getMain().getMainJoueur().get(i).getStringImage(),
                player.getMain().getMainJoueur().get(i).getStringImage());
            deckPlayerImage[i].addClassName("image-deck");

            var chosenCard = deckPlayerImage[i];
            deckPlayerImage[i].addClickListener(
                click -> {
                    //click.getSource().getElement().getStyle().set("border-color", "gold");
                    chosenCard.getElement().getStyle().set("border-color", "gold");
                    System.out.println("Le joueur: " + player.getPseudo() + " a choisi la carte "+chosenCard.getAlt());
                }
            );
        }

        HorizontalLayout deckLayout = new HorizontalLayout(deckPlayerImage);
        add(deckLayout);
    }
}
