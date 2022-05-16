package nc.unc.gl.borne.views;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("test")
@StyleSheet("css/end-view.css")
public class EndView extends VerticalLayout {

    public EndView(){
        VerticalLayout container = new VerticalLayout();

        container.setSpacing(true);
        container.setAlignItems(FlexComponent.Alignment.CENTER);

        Image image = new Image("cartes/back.png", "header");

        H3 end = new H3("Fin du jeu");
        H2 winner = new H2("Le gagnant est :");
        H1 theWinner = new H1("Anthony");

        container.add(image, end, winner, theWinner);

        add(container);
    }
}
