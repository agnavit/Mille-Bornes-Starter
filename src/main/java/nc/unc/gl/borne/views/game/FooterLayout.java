package nc.unc.gl.borne.views.game;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.metier.classes.Joueur;
import nc.unc.gl.borne.metier.classes.carte.Carte;
import nc.unc.gl.borne.metier.classes.carte.enumerations.TypeCarte;
import nc.unc.gl.borne.metier.classes.partie.Partie;
import nc.unc.gl.borne.metier.services.JoueurService;

import java.util.ArrayList;

@Route("testFooterLayout")
public class FooterLayout extends HorizontalLayout {

    ArrayList<Image> deckPlayerImage;
    Carte selectedCard;
    Image selectedIm;
    JoueurService playerService = new JoueurService();
    Button putCardButton;
    Button throwCardButton;
    Partie party;
    Joueur joueurDefensif;
    Joueur joueurAttaque;

    public FooterLayout(Partie refPartie, Joueur joueurDefensive, Joueur joueurAttaque){
        this.party = refPartie;
        this.joueurDefensif = joueurDefensive;
        this.joueurAttaque = joueurAttaque;

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
        footer.getStyle()
            .set("border-radius", "10px")
            .set("padding", "20px")
            .set("box-shadow", "0px 6px 10px rgba(0, 0, 0, 0.25)")
            .set("background", "linear-gradient(to right, #B0C4DE, #f5efef)");

        add(footer);
        this.throwCardButton = throwCardButton;
        this.putCardButton = putCardButton;
    }

    public void putCardPlayer( HorizontalLayout deckLayout, Button putCardButton){
        putCardButton.addClickListener(
            clickPutCard -> {
                if (selectedCard.getType() == TypeCarte.BORNE) {
                    playerService.poser(
                        selectedCard,
                        party.getDefausse(),
                        joueurDefensif
                    );
                } else if (selectedCard.getType() == TypeCarte.ATTAQUE) {
                    playerService.attaquer(
                        selectedCard,
                        joueurDefensif,
                        joueurAttaque
                    );
                    joueurDefensif.getMain().getMainJoueur().remove(selectedCard);

                } else if (selectedCard.getType() == TypeCarte.BOTTE) {
                    playerService.poser(
                        selectedCard,
                        party.getDefausse(),
                        joueurDefensif
                    );
                } else {
                    playerService.poser(
                        selectedCard,
                        party.getDefausse(),
                        joueurDefensif
                    );
                }
                updateDeckPlayer(deckLayout);
            }
        );
    }

    public void throwCardPlayer(HorizontalLayout deckLayout, Button throwCardButton){
        throwCardButton.addClickListener(
            click -> {
                playerService.jeter(this.selectedCard, party.getDefausse(), joueurDefensif);
                updateDeckPlayer(deckLayout);
            }
        );

    }

    public void updateDeckPlayer(HorizontalLayout deckLayout){

        deckLayout.removeAll();

        deckPlayerImage = new ArrayList<Image>(playerService.getSizeDeck(joueurDefensif));

        for (int i = 0; i < playerService.getSizeDeck(joueurDefensif); i++) {

            Image image = new Image(
                "cartes/" + playerService.getCardInDeck(joueurDefensif, i).getStringImage(),
                String.valueOf(playerService.getCardInDeck(joueurDefensif, i)));
            image.addClassName("card-deck");

            deckPlayerImage.add(image);

            var chosenCard = deckPlayerImage.get(i);
            int j = i;

            if (joueurDefensif.getMain().getTaille() == 7) {
                deckPlayerImage.get(i).addClickListener(
                    click -> {
                        if (chosenCard != selectedIm) {
                            eventImage(chosenCard, j, deckLayout);
                        }
                    }

                );
            }

        }
        for(Image image: deckPlayerImage){
            deckLayout.add(image);
        }
    }

    public void chosenCardMethode(Image image, int j){

        if(selectedIm != null){
            selectedIm.getElement().getStyle().set("border-color", "#9eb0c7");
        }

        System.out.println("Le joueur: " + joueurDefensif.getPseudo() + " a choisi la carte " + image.getAlt());
        selectedCard = new Carte(
            playerService.getCardInDeck(joueurDefensif,j).getNom(),
            playerService.getCardInDeck(joueurDefensif,j).getType(),
            playerService.getCardInDeck(joueurDefensif,j).getIdentifiant()
        );
        this.selectedIm = image;
        image.getElement().getStyle().set("border-color", "white");
    }

    public void eventImage(Image chosenCard, int j, HorizontalLayout deckLayout){

        chosenCardMethode(chosenCard, j);

        putCardButton.setEnabled(true);
        throwCardButton.setEnabled(true);

        putCardPlayer(deckLayout, putCardButton);
        throwCardPlayer(deckLayout, throwCardButton);
    }
}
