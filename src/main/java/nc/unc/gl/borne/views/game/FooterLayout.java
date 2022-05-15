package nc.unc.gl.borne.views.game;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.Deck.DeckService;
import nc.unc.gl.borne.carte.*;
import nc.unc.gl.borne.carte.enumerations.NomCarte;
import nc.unc.gl.borne.carte.enumerations.TypeCarte;
import nc.unc.gl.borne.carte.enumerations.TypePile;
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.joueur.JoueurService;
import nc.unc.gl.borne.partie.Partie;
import nc.unc.gl.borne.plateau.Plateau;

import java.util.ArrayList;

@Route("testFooterLayout")
public class FooterLayout extends HorizontalLayout {

    ArrayList<Image> deckPlayerImage;
    Carte selectedCard;
    Image selec;

    protected DeckService deckPlayer = new DeckService();


    JoueurService playerService = new JoueurService();

    Joueur joueurDefensif;

    public FooterLayout(Partie refPartie, Joueur joueurDefensive, Joueur joueurAttaque){
        this.joueurDefensif = joueurDefensive;
        HorizontalLayout footer = new HorizontalLayout();
        updateDeckPlayer(refPartie, footer, joueurDefensive, joueurAttaque);
    }

    public void putCardPlayer(Partie party, HorizontalLayout footerLayout, Button putCardButton,
                              Joueur joueurDefensif, Joueur joueurAttaque){
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
                System.out.println(party.getListejoueur().get(0).getMain().getMainJoueur());
                updateDeckPlayer(party, footerLayout, joueurDefensif, joueurAttaque);
                System.out.println(selectedCard);
            }
        );
    }

    public void throwCardPlayer(Partie party, HorizontalLayout footerLayout, Button throwCardButton,
                                Joueur joueurDefensif, Joueur joueurAttaque){
        throwCardButton.addClickListener(
            click -> {
                playerService.jeter(this.selectedCard, party.getDefausse(), joueurDefensif);
                System.out.println(joueurDefensif.getMain().getMainJoueur());
                updateDeckPlayer(party, footerLayout, joueurDefensif, joueurAttaque);
            }
        );

    }

    public void updateDeckPlayer(Partie party , HorizontalLayout footerLayout, Joueur joueurDefensif, Joueur joueurAttaque){

        removeAll();
        footerLayout.removeAll();

        Button putCardButton = new Button("Poser");
        putCardButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        Button throwCardButton = new Button("Jeter");
        throwCardButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        putCardButton.setEnabled(false);
        throwCardButton.setEnabled(false);

        VerticalLayout buttonsLayout = new VerticalLayout(putCardButton, throwCardButton);

        HorizontalLayout deckLayout = new HorizontalLayout();


        deckPlayerImage = new ArrayList<Image>(playerService.getSizeDeck(joueurDefensif));

        for (int i = 0; i < playerService.getSizeDeck(joueurDefensif); i++) {

            Image image = new Image(
                "cartes/" + playerService.getCardInDeck(joueurDefensif,i).getStringImage(),
                String.valueOf(playerService.getCardInDeck(joueurDefensif,i)));
            image.addClassName("card-deck");
            System.out.println(image);

            deckPlayerImage.add(image);

            var chosenCard = deckPlayerImage.get(i);
            int j = i;
            //TODO pas de clique sur les cartes quand il n'y a que six carte
            //TODO Spam sur les cartes = multiplicateur
            //TODO piocher /!\

            ArrayList<Image> deckPlayerCopy =  new ArrayList<Image>();
            deckPlayerCopy.addAll(deckPlayerImage);

            if(selectedCard != null){
                deckPlayerImage.remove(chosenCard);
                System.out.println(deckPlayerImage);
            }

            deckPlayerImage.get(i).addClickListener(
                click -> {

                    chosenCardMethode(chosenCard, j);

                    putCardButton.setEnabled(true);
                    throwCardButton.setEnabled(true);

                    putCardPlayer(party, footerLayout, putCardButton, joueurDefensif, joueurAttaque);
                    throwCardPlayer(party, footerLayout, throwCardButton, joueurDefensif, joueurAttaque);

                }

            );

        }

        for(Image image: deckPlayerImage){
            deckLayout.add(image);
        }

        footerLayout.add(deckLayout, buttonsLayout);
        System.out.println(deckPlayerImage);
        footerLayout.getStyle()
            .set("border-radius", "10px")
            .set("padding", "20px")
            .set("box-shadow", "0px 6px 10px rgba(0, 0, 0, 0.25)")
            .set("background", "linear-gradient(to right, #B0C4DE, #f5efef)");

        add(footerLayout);
    }

    public void chosenCardMethode(Image image, int j){

        if(selec != null){
            System.out.println( selectedCard.getClass().getComponentType());
            selec.getElement().getStyle().set("border-color", "#9eb0c7");
        }


        System.out.println("Le joueur: " + joueurDefensif.getPseudo() + " a choisi la carte " + image.getAlt());
        selectedCard = new Carte(
            playerService.getCardInDeck(joueurDefensif,j).getNom(),
            playerService.getCardInDeck(joueurDefensif,j).getType(),
            playerService.getCardInDeck(joueurDefensif,j).getIdentifiant()
        );
        this.selec = image;
        image.getElement().getStyle().set("border-color", "white");


    }
}
