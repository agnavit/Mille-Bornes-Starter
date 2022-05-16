package nc.unc.gl.borne.views.game;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import nc.unc.gl.borne.metier.classes.Joueur;
import nc.unc.gl.borne.metier.classes.carte.Carte;
import nc.unc.gl.borne.metier.classes.carte.enumerations.TypeCarte;
import nc.unc.gl.borne.metier.classes.partie.Partie;
import nc.unc.gl.borne.metier.services.JoueurService;
import java.util.ArrayList;

public class FooterLayout extends HorizontalLayout {

    Joueur me;
    Joueur notMe;
    Partie party;
    Carte selectedCard;
    Image selectedImage;
    Button putCardButton;
    Button throwCardButton;
    ArrayList<Image> deckPlayerImage;
    JoueurService playerService = new JoueurService();

    public FooterLayout(Partie party, Joueur me, Joueur notMe) {

        this.me = me;
        this.notMe = notMe;
        this.party = party;

        HorizontalLayout footer = new HorizontalLayout();

        Button putCardButton = new Button("Poser");
        putCardButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button throwCardButton = new Button("Jeter");
        throwCardButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        putCardButton.setEnabled(false);
        throwCardButton.setEnabled(false);

        VerticalLayout buttonsLayout = new VerticalLayout(putCardButton, throwCardButton);
        HorizontalLayout deckLayout = new HorizontalLayout();

        updateDeckPlayer(deckLayout);

        footer.add(deckLayout, buttonsLayout);
        footer.addClassName("footer");

        add(footer);

        this.throwCardButton = throwCardButton;
        this.putCardButton = putCardButton;
    }

    public void putCardPlayer(HorizontalLayout deckLayout, Button putCardButton){
        putCardButton.addClickListener(
            clickPutCard -> {
                if (selectedCard.getType() == TypeCarte.BORNE) {
                    playerService.poser(
                        selectedCard,
                        party.getDefausse(),
                        me
                    );
                } else if (selectedCard.getType() == TypeCarte.ATTAQUE) {
                    playerService.attaquer(
                        selectedCard,
                        me,
                        notMe
                    );
                    me.getMain().getMainJoueur().remove(selectedCard);

                } else if (selectedCard.getType() == TypeCarte.BOTTE) {
                    playerService.poser(
                        selectedCard,
                        party.getDefausse(),
                        me
                    );
                } else {
                    playerService.poser(
                        selectedCard,
                        party.getDefausse(),
                        me
                    );
                }
                updateDeckPlayer(deckLayout);
            }
        );
    }

    public void throwCardPlayer(HorizontalLayout deckLayout, Button throwCardButton) {
        throwCardButton.addClickListener(
            click -> {
                playerService.jeter(this.selectedCard, party.getDefausse(), me);
                updateDeckPlayer(deckLayout);
            }
        );
    }

    public void updateDeckPlayer(HorizontalLayout deckLayout) {

        deckLayout.removeAll();
        deckPlayerImage = new ArrayList<>(playerService.getSizeDeck(me));

        for (int i = 0; i < playerService.getSizeDeck(me); i++) {

            Image image = new Image(
                "cartes/" + playerService.getCardInDeck(me, i).getStringImage(),
                String.valueOf(playerService.getCardInDeck(me, i))
            );

            image.addClassName("card-deck");
            deckPlayerImage.add(image);

            var chosenCard = deckPlayerImage.get(i);
            int j = i;

            if (me.getMain().getTaille() == 7) {
                deckPlayerImage.get(i).addClickListener(
                    click -> {
                        if (chosenCard != selectedImage) {
                            eventImage(chosenCard, j, deckLayout);
                        }
                    }
                );
            }
        }
        for(Image image: deckPlayerImage) {
            deckLayout.add(image);
        }
    }

    public void chosenCardMethode(Image image, int j) {

        if(selectedImage != null){
            selectedImage.getElement().getStyle().set("border-color", "#9eb0c7");
        }

        System.out.println("Le joueur: " + me.getPseudo() + " a choisi la carte " + image.getAlt());
        selectedCard = new Carte(
            playerService.getCardInDeck(me, j).getNom(),
            playerService.getCardInDeck(me, j).getType(),
            playerService.getCardInDeck(me, j).getIdentifiant()
        );
        this.selectedImage = image;
        image.getElement().getStyle().set("border-color", "white");
    }

    public void eventImage(Image image, int j, HorizontalLayout deckLayout) {

        chosenCardMethode(image, j);

        putCardButton.setEnabled(true);
        throwCardButton.setEnabled(true);

        putCardPlayer(deckLayout, putCardButton);
        throwCardPlayer(deckLayout, throwCardButton);
    }
}
