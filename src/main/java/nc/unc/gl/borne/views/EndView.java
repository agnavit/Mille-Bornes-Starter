package nc.unc.gl.borne.views;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.metier.classes.Joueur;

@Route("endView/:user?")
@StyleSheet("css/end-view.css")
public class EndView extends VerticalLayout implements BeforeEnterObserver {

    String gagnant;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        gagnant = event.getRouteParameters().get("user").orElse("error");

        VerticalLayout container = new VerticalLayout();

        container.setSpacing(true);
        container.setAlignItems(FlexComponent.Alignment.CENTER);

        Image image = new Image("cartes/back.png", "header");

        H3 end = new H3("Fin du jeu");
        H2 winner = new H2("Le gagnant est :");
        H1 theWinner = new H1(gagnant);

        container.add(image, end, winner, theWinner);

        add(container);
    }

    public EndView(){

    }
}
