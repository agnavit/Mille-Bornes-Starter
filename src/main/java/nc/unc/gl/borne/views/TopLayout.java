package nc.unc.gl.borne.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.joueur.Joueur;

@Route("test3")
@Tag("test3")
@StyleSheet("css/toplayout.css")
public class TopLayout extends VerticalLayout {

    /**
     * Joueur player = new Joueur();
     * joueur.score
     */
    Integer integer = 3;
    Integer playerScore =  300;
    Image[] pileBataille = new Image[integer];

    public TopLayout(){
        VerticalLayout container = new VerticalLayout();

        H1 score = new H1("Score : " + playerScore);

        pileBataille[0] = new Image("cartes/attaque_feu.jpeg", "attaque_feu");
        pileBataille[0].addClassName("disabling-cards");
        pileBataille[1] = new Image("cartes/attaque_accident.jpeg", "attaque_accident");
        pileBataille[1].addClassName("disabling-cards");
        pileBataille[2] = new Image("cartes/attaque_crevaison.jpeg", "attaque_crevaison");
        pileBataille[2].addClassName("disabling-cards");

        HorizontalLayout disablingCards = new HorizontalLayout(pileBataille);

        container.add(score, disablingCards);
        add(container);
    }
}
