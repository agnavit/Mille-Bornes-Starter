package nc.unc.gl.borne.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("testActionLayout")
@StyleSheet("css/decklayout.css")
public class ActionLayout extends HorizontalLayout {

    public ActionLayout(){
        Image cardTaken = new Image("cartes/back.png", "back.png");
            cardTaken.addClassName("image-deck");

        HorizontalLayout cardTakenLayout = new HorizontalLayout(cardTaken);
            cardTakenLayout.getStyle().set("margin-right", "25px");

        Button putCard = new Button("Poser");
            putCard.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        Button throwCard = new Button("Jeter");
            throwCard.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);

        VerticalLayout buttonsLayout = new VerticalLayout(putCard, throwCard);

        add(cardTakenLayout, buttonsLayout);
    }
}
