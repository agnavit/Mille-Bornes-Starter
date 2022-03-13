package nc.unc.gl.borne;

import java.util.concurrent.atomic.AtomicReference;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;

@Route("")
@Tag("main-view")
@StyleSheet("css/mainview.css")
@PageTitle("Menu | Mille Bornes")
public class MainView extends HtmlContainer {

    public MainView() {

        // Création de la div qui va servir de conteneur pour les autres balises
        Div container = new Div();
        appliedContainerStyle(container);

        // Création du titre de la page
        Div title = new Div();
        Image titleImage = new Image("cartes/header.png", "header");
        titleImage.addClassName("main-view-title");
        title.add(titleImage);

        // Création des sous-titres
        H2 play = new H2("play");
        play.addClassName("main-view-subtitle");
        H2 rules = new H2("rules");
        rules.addClassName("main-view-subtitle");

        // Création des événements des sous-titres
        play.addClickListener(click -> UI.getCurrent().navigate("play"));
        rules.addClickListener(click -> Notification.show("Les règles ne sont pas encore disponible"));

        // Les éléments sont ajoutés au conteneur
        container.add(title, play, rules);
        // Le conteneur est ajouté à la page
        add(container);
    }

    /**
     * Ajoute du style à la div qui va servir de conteneur
     * configuration:
     *      - de la hauteur
     *      - du background
     * @param container (type = Div)
     */
    public void appliedContainerStyle(Div container){
        container.getStyle()
            .set("height", "100%")
            .set("background-image", "url(background.jpg)")
            .set("background-size", "cover")
            .set("background-repeat", "no-repeat");
    }

}
