package nc.unc.gl.borne.views;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dialog.Dialog;
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
import lombok.Data;
import nc.unc.gl.borne.Observer;
import nc.unc.gl.borne.carte.Carte;
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.partie.Partie;

import java.util.ArrayList;

@Route(value = "party")
@Tag("party")
@Data
@StyleSheet("css/partyview.css")
public class PartyView extends HtmlContainer implements Observer {

    private final UI ui;
    private static Partie party = new Partie();
    ListBox<String> listBox = new ListBox<>();
    public Carte carteChoisie = null;

    public PartyView(){

        // On stocke l'UI pour pouvoir faire des UI.access()
        this.ui = UI.getCurrent();

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

        Button createGameButton = new Button("Créer");
        Button joinGameButton = new Button("Rejoindre");
        Button cancelCreateGameButton = new Button("Annuler");


        createPartyTab.getElement().addEventListener("click", event -> {
            container.remove(createGameButton, listBox, joinGameButton);
            container.add(createGameButton);
        });

        joinPartyTab.getElement().addEventListener("click", event -> {
            container.remove(createGameButton, listBox, joinGameButton);
            container.add(listBox, joinGameButton);
        });

        Dialog loading = new Dialog();

        VerticalLayout loadingLayout = showLoading(loading);
        loading.add(loadingLayout);

        createGameButton.getElement().addEventListener("click", event -> {
            Notification.show("Attente pour créer une partie");
            party.creerPartieObserver(player);
            container.remove(createGameButton);
            container.add(cancelCreateGameButton);
            joinPartyTab.setEnabled(false);
            loading.open();
        });

        joinGameButton.getElement().addEventListener("click", event -> UI.getCurrent().navigate("test"));

        cancelCreateGameButton.getElement().addEventListener("click", event -> {
            container.remove(cancelCreateGameButton);
            container.add(createGameButton);
            joinPartyTab.setEnabled(true);
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

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        party.addObserveur(this);
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        party.removeObserveur(this);
    }

    public void update(Partie partie) {
        ui.access(() -> {
            listBox.setItems(partie.getListejoueur().get(0).getPseudo());
        });
    }

    public static VerticalLayout showLoading(Dialog loading){
        VerticalLayout loadingLayout = new VerticalLayout();
        Div loadingDiv = new Div();
        loadingDiv.addClassName("lds-dual-ring");
        loadingDiv.getStyle().set("padding-left", "110px");
        Text partieCree = new Text("Votre partie à été créé !");
        Text attente = new Text("\nEn attente de joueur");
        loadingLayout.add(loadingDiv, partieCree);
        loadingLayout.add(attente);
        return loadingLayout;
    }
}

