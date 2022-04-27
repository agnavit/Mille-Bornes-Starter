package nc.unc.gl.borne.views;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.Party;
import nc.unc.gl.borne.joueur.Joueur;

@Route(value = "party")
@Tag("party")
@StyleSheet("css/party.css")
public class PartyView extends HtmlContainer {

    private final Party party = new Party();
    //private final UI ui;

    public PartyView(){

        // On stocke l'UI pour pouvoir faire des UI.access()
        //this.ui = UI.getCurrent();

        // Création de la div qui va servir de conteneur pour les autres balises
        Div container = new Div();
        container.getStyle()
            .set("height", "100%")
            .set("background-image", "url(background.jpg)")
            .set("background-size", "cover")
            .set("background-repeat", "no-repeat");

        // Création du titre de la page
        Div title = new Div();
        Image titleImage = new Image("cartes/header.png", "header");
        titleImage.addClassName("image-title");
        title.add(titleImage);
        container.add(title);

        createPlayer(container);
        add(container);
    }

    private void createPlayer(Div container){
        HorizontalLayout layout = new HorizontalLayout();
        TextField userNameField = new TextField();
        Button confirmationButton = new Button("Confirmation");
        layout.add(userNameField, confirmationButton);

        confirmationButton.addClickListener(click -> {

            // Récupération du pseudo
            String username = userNameField.getValue();
            // Création du joueur (Pas besoin d'age)
            Joueur player = new Joueur(this.hashCode(), username, 0);

            System.out.println(username + " viens de choisir sont pseudo " + this.hashCode());

            //container.remove(layout);
            confirmationButton.addClassName("buttons-disabled");
            showButtons(username, container);
        });
        layout.addClassName("chose-username");
        container.add(layout);
    }

    private void showButtons(String username, Div container){
        Notification.show("Bonjour " + username);

        Button createGame = new Button("Créer une partie", click -> {
            party.createParty();
            Notification.show("Attente pour créer une partie");
        });
        createGame.addClassName("party-buttons");

        Button joinGame = new Button("Rejoindre une partie", click -> {
            party.joinParty();
            Notification.show("Attente pour rejoindre une partie");
            UI.getCurrent().navigate("test");
        });
        joinGame.addClassName("party-buttons");

        Div containerButtons = new Div();
        containerButtons.add(createGame, joinGame);
        container.add(containerButtons);
    }

    /**
     @Override
     public void updatePartie(Partie partie) {
     System.out.println("update");
     ui.access(() -> this.add(""));
     }*/
}
