package nc.unc.gl.borne.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.carte.Carte;

@Route("testDeckLayout")
@Tag("testDeckLayout")
@StyleSheet("css/decklayout.css")
public class DeckLayout extends VerticalLayout {

    // Doit remplacer les images par le deck protected Deck deck = new Deck();

    protected Image[] deckPlayer = new Image[7];

    public DeckLayout(){

        deckPlayer[0] = new Image("cartes/parade_vitesse.jpeg", "parade_vitesse");
        deckPlayer[0].addClassName("image-deck");
        deckPlayer[1] = new Image("cartes/attaque_accident.jpeg", "attaque_accident");
        deckPlayer[1].addClassName("image-deck");
        deckPlayer[2] = new Image("cartes/attaque_crevaison.jpeg", "attaque_crevaison");
        deckPlayer[2].addClassName("image-deck");
        deckPlayer[3] = new Image("cartes/borne_vingt_cinq.jpeg", "borne_25");
        deckPlayer[3].addClassName("image-deck");
        deckPlayer[4] = new Image("cartes/borne_soixante_quinze.jpeg", "borne_75");
        deckPlayer[4].addClassName("image-deck");
        deckPlayer[5] = new Image("cartes/borne_vingt_cinq.jpeg", "borne_25");
        deckPlayer[5].addClassName("image-deck");
        deckPlayer[6] = new Image();
        deckPlayer[6].addClassName("image-deck");



        HorizontalLayout deckLayout = new HorizontalLayout(deckPlayer);
        deckLayout.getStyle().set("margin-right", "60px");
        add(deckLayout);
    }
//
//    public Image[] getDeckPlayer() {
//        return deckPlayer;
//    }

    //TODO
//    public void setDeckPlayer(Carte cartePiochee) {
//        this.deckPlayer[6] = cartePiochee;
//    }
}
