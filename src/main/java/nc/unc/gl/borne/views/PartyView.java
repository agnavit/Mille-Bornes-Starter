package nc.unc.gl.borne.views;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.TabVariant;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.Party;
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.plateau.Plateau;

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

            showTabs(player, container);
        });
        layout.addClassName("chose-username");
        container.add(layout);
    }

    private void showTabs(Joueur player, VerticalLayout container){

        Notification.show(
            "Bonjour " + player.getPseudo(),
            2000,
            Notification.Position.TOP_CENTER).addThemeVariants(NotificationVariant.LUMO_PRIMARY
        );

        // Ici les joueurs qui ont créer une partie
        Joueur player1 = new Joueur(1, "Jason M", 21);
        Joueur player2 = new Joueur(1, "Clementine", 26);
        Joueur player3 = new Joueur(1, "Emerick", 22);

        ListBox<String> listBox = new ListBox<>();
        listBox.setItems(player1.getPseudo(), player2.getPseudo(), player3.getPseudo());

        Tab createPartyTab = new Tab(
            VaadinIcon.USER.create(),
            new Span("Créer une partie")
        );

        Tab joinPartyTab = new Tab(
            VaadinIcon.USERS.create(),
            new Span("Rejoindre une partie")
        );

        // Icons en haut
        for (Tab tab : new Tab[]{createPartyTab, joinPartyTab}){
            tab.addThemeVariants(TabVariant.LUMO_ICON_ON_TOP);
        }

        Tabs playerChoice = new Tabs(createPartyTab, joinPartyTab);

        Button createGameButton = new Button("Créer", click -> {
            party.createParty(player);
            Notification.show("Attente pour créer une partie");
        });

        Button joinGameButton = new Button("Rejoindre", click -> {
            party.joinParty(listBox.getValue(), player);
            UI.getCurrent().navigate("test");
        });

        createPartyTab.getElement().addEventListener("click", event -> {
            container.remove(createGameButton, listBox, joinGameButton);
            container.add(createGameButton);
        });

        joinPartyTab.getElement().addEventListener("click", event -> {
            container.remove(createGameButton, listBox, joinGameButton);
            container.add(listBox, joinGameButton);
        });

        container.add(playerChoice, createGameButton);
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
