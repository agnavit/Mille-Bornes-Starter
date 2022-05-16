package nc.unc.gl.borne.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.*;

@Route("")
@Tag("main-view")
@StyleSheet("css/mainview.css")
@PageTitle("Menu | Mille Bornes")
public class MainView extends HtmlContainer {

    public MainView() {

        Div container = new Div();
        appliedContainerStyle(container);

        Div title = new Div();
        Image titleImage = new Image("cartes/header.png", "header");
        titleImage.addClassName("main-view-title");
        title.add(titleImage);

        Dialog rules = new Dialog();
        rules.getElement().setAttribute("aria-label", "Les règles");

        VerticalLayout dialogLayout = showRules();
        rules.add(dialogLayout);

        H2 playSubtitle = new H2("play");
        playSubtitle.addClassName("main-view-subtitle");
        H2 rulesSubtitle = new H2("rules");
        rulesSubtitle.addClassName("main-view-subtitle");

        playSubtitle.addClickListener(
            click -> UI.getCurrent().navigate("party")
        );

        rulesSubtitle.addClickListener(
            click -> rules.open()
        );

        container.add(title, playSubtitle, rulesSubtitle);
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

    public static VerticalLayout showRules(){

        Button arrowLeftButton = new Button(new Icon(VaadinIcon.ARROW_LEFT));
        Button arrowRightButton = new Button(new Icon(VaadinIcon.ARROW_RIGHT));
        arrowRightButton.setIconAfterText(true);

        HorizontalLayout arrowsLayout = new HorizontalLayout(arrowLeftButton, arrowRightButton);
        arrowsLayout.setAlignItems(FlexComponent.Alignment.END);

        VerticalLayout dialogLayout = new VerticalLayout(arrowsLayout);

        arrowLeftButton.addClickListener(
            click -> dialogLayout.getStyle()
                .set("background-image", "url(rules/rules-page-1.png)"));

        arrowRightButton.addClickListener(
            click -> dialogLayout.getStyle()
                .set("background-image", "url(rules/rules-page-2.png)"));

        dialogLayout.getStyle()
            .set("background-image", "url(rules/rules-page-1.png)")
            .set("background-size", "contain")
            .set("background-repeat", "no-repeat")
            .set("width", "600px")
            .set("height", "900px");

        return dialogLayout;
    }
}
