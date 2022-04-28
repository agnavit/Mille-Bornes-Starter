package nc.unc.gl.borne.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("test2")
@Tag("test2")
@StyleSheet("css/decklayout.css")
public class DeckLayout extends VerticalLayout {

    // Doit remplacer les images par le deck protected Deck deck = new Deck();
    protected Image[] deckPlayer = new Image[6];

    public DeckLayout(){

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.getStyle()
            .set("position", "fixed")
            .set("bottom", "0")
            .set("padding", "14px");

        deckPlayer[0] = new Image("cartes/parade_vitesse.jpeg", "parade_vitesse");
        deckPlayer[0].addClassName("image-deck");
        deckPlayer[1] = new Image("cartes/attaque_accident.jpeg", "attaque_accident");
        deckPlayer[1].addClassName("image-deck");
        deckPlayer[2] = new Image("cartes/attaque_crevaison.jpeg", "attaque_crevaison");
        deckPlayer[2].addClassName("image-deck");
        deckPlayer[3] = new Image("cartes/borne_25.jpeg", "borne_25");
        deckPlayer[3].addClassName("image-deck");
        deckPlayer[4] = new Image("cartes/borne_75.jpeg", "borne_75");
        deckPlayer[4].addClassName("image-deck");
        deckPlayer[5] = new Image("cartes/borne_25.jpeg", "borne_25");
        deckPlayer[5].addClassName("image-deck");

        HorizontalLayout deckLayout = new HorizontalLayout(deckPlayer);
        deckLayout.getStyle().set("margin-right", "60px");


        Image cardTaken = new Image("cartes/back.png", "back.png");
        cardTaken.addClassName("image-deck");

        HorizontalLayout cardTakenLayout = new HorizontalLayout(cardTaken);
        cardTakenLayout.getStyle().set("margin-right", "25px");

        Button putCard = new Button("Poser");
        putCard.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        Button throwCard = new Button("Jeter");
        throwCard.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);

        VerticalLayout buttonsLayout = new VerticalLayout(putCard, throwCard);

        layout.add(deckLayout, cardTakenLayout, buttonsLayout);
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        add(layout);
    }
}
