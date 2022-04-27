package nc.unc.gl.borne.views;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.router.Route;

import java.awt.*;

@Route("play")
@Tag("play")
@StyleSheet("css/play.css")
public class Play extends HtmlContainer {
    public Play(){
        Div container = new Div();
        Image image = new Image("cartes/back.png", "header");
        image.addClassName("party-image");

        Div containerButtons = new Div();
        Button createGame = new Button("Créer une partie");
        createGame.addClassName("party-buttons");

        Button joinGame = new Button("Rejoindre une partie");
        joinGame.addClassName("party-buttons");

        containerButtons.add(createGame, joinGame);
        container.add(image, containerButtons);
        add(container);
    }
}
