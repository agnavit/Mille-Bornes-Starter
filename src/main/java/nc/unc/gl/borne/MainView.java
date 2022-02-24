package nc.unc.gl.borne;

import java.util.concurrent.atomic.AtomicReference;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;

@Route("")
@Tag("main-page")
@PageTitle("Menu | Mille Bornes")
public class MainView extends HtmlContainer {

    public MainView() {

        // Création de la div qui va servir de conteneur pour les autres balises
        Div container = new Div();
        appliedContainerStyle(container);

        // Création du titre de la page
        Div title = new Div();
        appliedTitleStyle(title);

        // Création des sous-titres
        H2 singlePlayer = new H2("single player");
        H2 multiPlayer = new H2("multi-player");
        H2 rules = new H2("rules");

        // Modification du style des sous-titres
        singlePlayer = appliedSubtitleStyle(singlePlayer);
        multiPlayer = appliedSubtitleStyle(multiPlayer);
        rules = appliedSubtitleStyle(rules);

        // Création des événements des sous-titres
        singlePlayer.addClickListener(click -> UI.getCurrent().navigate("single-player"));
        AtomicReference<Boolean> validClick = new AtomicReference<>(true);
        H2 finalMultiPlayer = multiPlayer;
        multiPlayer.addClickListener(click -> {if (validClick.get()){
                Div content = new Div();
                H3 playersEqual4 = new H3("4 joueurs");
                H3 playersEqual8 = new H3("8 joueurs");
                content.add(playersEqual4, playersEqual8);
                finalMultiPlayer.add(content);
                validClick.set(false);
            } else{
                finalMultiPlayer.getElement().removeAllChildren();
                finalMultiPlayer.setText("multi-player");
                validClick.set(true);
            }}
        );
        rules.addClickListener(click -> Notification.show("Les règles ne sont pas encore disponible"));

        // Les éléments sont ajoutés au conteneur
        container.add(title, singlePlayer, finalMultiPlayer, rules);
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

    /**
     * Création du titre
     * @param div (type = Div)
     */
    public void appliedTitleStyle(Div div){
        Image titleImage = new Image("cartes/header.png", "header");
        titleImage.getStyle()
            .set("display","block")
            .set("margin-left","auto")
            .set("margin-right","auto")
            .set("width","31%");
        div.add(titleImage);
    }

    /**
     * Modification de style des balises H2 qui servent de sous-titres
     * @param subtitle (type = H2)
     * @return variable
     */
    protected H2 appliedSubtitleStyle(H2 subtitle){
        subtitle.getStyle()
            //.set("text-shadow", "0 0 .4em rgba(0,0,0,.5)")
            .set("background-color", "#FFDEAD")
            .set("border", "solid #DEB887")
            .set("margin-left", "38%")
            .set("margin-right", "38%")
            .set("border-radius", "4vmin")
            .set("text-align", "center")
            .set("text-transform","uppercase")
            .set("-webkit-text-stroke", "1px #DEB887")
            .set("font-family", "cursive")
            .set("color", "#DEB887")
            .set("padding", "1px")
            .set("font-weight", "100")
            .set("position","relative");
        return subtitle;
    }
}
