package nc.unc.gl.borne.views;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.Party;
import nc.unc.gl.borne.joueur.Joueur;

@Route(value = "party")
@Tag("party")
public class PartyView extends HtmlContainer {

    private final Party party = new Party();
    //private final UI ui;

    public PartyView(){

        // On stocke l'UI pour pouvoir faire des UI.access()
        //this.ui = UI.getCurrent();

        VerticalLayout container = new VerticalLayout();
        container.setSpacing(true);
        container.setAlignItems(FlexComponent.Alignment.CENTER);

        Image image = new Image("cartes/back.png", "header");

        container.add(image);

        createPlayer(container);
        add(container);
    }

    private void createPlayer(VerticalLayout container){

        HorizontalLayout layout = new HorizontalLayout();
        layout.setSpacing(true);
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        TextField userNameField = new TextField();
        userNameField.setPrefixComponent(VaadinIcon.USER.create());
        userNameField.setPlaceholder("Pseudo");
        userNameField.setMaxLength(17);

        Button pendingConfirmation = new Button("Confirmation", createIcon(VaadinIcon.CLOCK));
        pendingConfirmation.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        pendingConfirmation.setIconAfterText(true);

        IntegerField ageUserField = new IntegerField();
        ageUserField.setPrefixComponent(VaadinIcon.CALENDAR.create());
        ageUserField.setPlaceholder("Âge");
        ageUserField.setMax(120);

        layout.add(userNameField, ageUserField, pendingConfirmation);



        pendingConfirmation.addClickListener(click -> {

            // Récupération des données
            String username = userNameField.getValue();
            Integer ageUser = ageUserField.getValue();

            // Création du joueur
            Joueur player = new Joueur(this.hashCode(), username, ageUser);

            System.out.println(username + " viens de choisir sont pseudo, il a " + ageUser + " ans " + this.hashCode());
            userNameField.setEnabled(false);
            ageUserField.setEnabled(false);
            layout.remove(pendingConfirmation);

            Button confirmed = new Button("Confirmé", createIcon(VaadinIcon.CHECK));
            confirmed.setEnabled(false);
            confirmed.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_SUCCESS);
            confirmed.setIconAfterText(true);

            layout.add(confirmed);

            showButtons(username, container);
        });
        layout.addClassName("chose-username");
        container.add(layout);
    }

    private void showButtons(String username, VerticalLayout container){

        Notification.show("Bonjour " + username,2000,Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_PRIMARY);

        Button createGame = new Button("Créer une partie", click -> {
            party.createParty();
            Notification.show("Attente pour créer une partie");
        });
        createGame.addClassName("party-buttons");

        Button joinGame = new Button("Rejoindre une partie", click -> {
            party.joinParty();
            UI.getCurrent().navigate("test");
        });
        joinGame.addClassName("party-buttons");

        HorizontalLayout containerButtons = new HorizontalLayout();
        containerButtons.setSpacing(true);

        containerButtons.add(createGame, joinGame);
        container.add(containerButtons);
    }

    /**
     * Crée et ajout le style de l'icon pour les boutons
     * @param vaadinIcon Un icon de Vaadin
     * @return l'icon avec le style
     */
    private Icon createIcon(VaadinIcon vaadinIcon) {
        Icon icon = vaadinIcon.create();
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        return icon;
    }

    /**
     @Override
     public void updatePartie(Partie partie) {
     System.out.println("update");
     ui.access(() -> this.add(""));
     }*/
}
