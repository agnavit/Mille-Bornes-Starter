package nc.unc.gl.borne.views;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import lombok.Data;
import nc.unc.gl.borne.carte.Carte;
import nc.unc.gl.borne.partie.Partie;
import nc.unc.gl.borne.partie.PartieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Route(value = "/test/{idPartie}")
@Tag("test")
@StyleSheet("css/test.css")
@Data
public class TestView extends HtmlContainer {

    public Carte carteChoisie = null;
    PartieService partieService = new PartieService();

    public TestView() {
        //partieService.lancerPartie();

        VerticalLayout container = new VerticalLayout();
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

        DeckLayout deck = new DeckLayout();

        H2 titleBattle = new H2("Batailles");
        titleBattle.addClassName("content-title");
        VerticalLayout battle = new VerticalLayout();
        battle.addClassName("battle");

        H2 titleMiles = new H2("Bornes");
        titleMiles.addClassName("content-title");
        VerticalLayout miles = new VerticalLayout();
        miles.addClassName("miles");

        TopLayout topLayout = new TopLayout();

        containerSpeed.add(titleSpeed, speed);
        containerDeck.add(titleDeck, deck);
        containerBattle.add(titleBattle, battle);
        containerMiles.add(titleMiles, miles);



        container.add(topLayout, containerDeck);
        add(container);
    }
}
