package nc.unc.gl.borne.views.game;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import nc.unc.gl.borne.carte.Carte;
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.joueur.JoueurService;

@StyleSheet("css/decklayout.css")
public class DeckLayout extends VerticalLayout {

    JoueurService playerService = new JoueurService();

    public DeckLayout(Joueur player){

        Image[] deckPlayerImage = new Image[player.getMain().getMainJoueur().size()];

        for(Integer i = 0; i < playerService.getSizeDeck(player); i++){

            deckPlayerImage[i] = new Image(
                "cartes/"+  playerService.getCardInDeck(player,i).getStringImage(),
                String.valueOf(playerService.getCardInDeck(player,i)));
            deckPlayerImage[i].addClassName("image-deck");


            var chosenCard = deckPlayerImage[i];
            int j = i;
            deckPlayerImage[i].addClickListener(
                click -> {
                    //click.getSource().getElement().getStyle().set("border-color", "gold");
                    chosenCard.getElement().getStyle().set("border-color", "gold");
                    System.out.println("Le joueur: " + player.getPseudo() + " a choisi la carte "+chosenCard.getAlt());
                    Carte selectedCard = new Carte(
                        playerService.getCardInDeck(player,j).getNom(),
                        playerService.getCardInDeck(player,j).getType(),
                        playerService.getCardInDeck(player,j).getNumero()
                    );
                }
            );
        }

        HorizontalLayout deckLayout = new HorizontalLayout(deckPlayerImage);
        add(deckLayout);
    }
}
