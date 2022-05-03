package nc.unc.gl.borne.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("testActionLayout")
public class ActionLayout extends HorizontalLayout {

    public ActionLayout(){

        Button putCard = new Button("Poser");
            putCard.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        Button throwCard = new Button("Jeter");
            throwCard.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);


        putCard.addClickListener(
            click -> {
                //TODO: Poser la carte choisi
            }
        );

        throwCard.addClickListener(
            click -> {
                //TODO: Jeter la carte choisi
            }
        );


        VerticalLayout buttonsLayout = new VerticalLayout(putCard, throwCard);

        add(buttonsLayout);
    }
}
