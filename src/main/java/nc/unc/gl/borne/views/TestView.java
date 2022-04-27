package nc.unc.gl.borne.views;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "test")
@Tag("test")
@StyleSheet("css/test.css")
public class TestView extends HtmlContainer {
    public TestView(){

        HorizontalLayout container = new HorizontalLayout();
        container.setSpacing(true);
        container.addClassName("container");

        Div containerSpeed = new Div();
        containerSpeed.addClassName("content-speed");
        Div containerDeck = new Div();
        containerDeck.addClassName("content-deck");
        Div containerBattle = new Div();
        containerBattle.addClassName("content-battle");
        Div containerMiles = new Div();
        containerMiles.addClassName("content-miles");

        H2 titleSpeed = new H2("Vitesse");
        titleSpeed.addClassName("content-title");
        VerticalLayout speed = new VerticalLayout();
        speed.addClassName("speed");

        H2 titleDeck = new H2("Deck");
        titleDeck.addClassName("content-title");
        HorizontalLayout deck = new HorizontalLayout();
        Image deck1 = new Image("cartes/attaque_accident.jpeg", "attaque_accident");
        Image deck2 = new Image("cartes/attaque_crevaison.jpeg", "attaque_crevaison");
        Image deck3 = new Image("cartes/attaque_accident.jpeg", "attaque_accident");
        Image deck4 = new Image("cartes/borne_25.jpeg", "borne_25");
        Image deck5 = new Image("cartes/borne_75.jpeg", "borne_75");
        Image deck6 = new Image("cartes/parade_vitesse.jpeg", "parade_vitesse");
        deck1.addClassName("deck-cards");
        deck2.addClassName("deck-cards");
        deck3.addClassName("deck-cards");
        deck4.addClassName("deck-cards");
        deck5.addClassName("deck-cards");
        deck6.addClassName("deck-cards");
        deck.add(titleDeck, deck1, deck2, deck3, deck4, deck5, deck6);
        deck.addClassName("deck");

        H2 titleBattle = new H2("Batailles");
        titleBattle.addClassName("content-title");
        VerticalLayout battle = new VerticalLayout();
        battle.addClassName("battle");

        H2 titleMiles = new H2("Bornes");
        titleMiles.addClassName("content-title");
        VerticalLayout miles = new VerticalLayout();
        miles.addClassName("miles");

        containerSpeed.add(titleSpeed, speed);
        containerDeck.add(titleDeck, deck);
        containerBattle.add(titleBattle, battle);
        containerMiles.add(titleMiles, miles);

        container.add(containerSpeed, containerDeck, containerBattle, containerMiles);
        add(container);
    }
}
