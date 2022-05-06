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
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.joueur.JoueurService;
import nc.unc.gl.borne.partie.Partie;
import nc.unc.gl.borne.plateau.Plateau;

import java.util.ArrayList;

@Route("testFooterLayout")
@StyleSheet("css/decklayout.css")
public class FooterLayout extends HorizontalLayout {

    protected DeckService deckPlayer = new DeckService();

    Joueur myPlayer = new Joueur(1, "Anthony", 22);
    Joueur notMyPlayer = new Joueur(2, "Jason", 21);

    Carte carte1 = new Carte(NomCarte.ACCIDENT, TypeCarte.ATTAQUE, 1);
    Carte carte2 = new Carte(NomCarte.ESSENCE, TypeCarte.ATTAQUE, 2);
    Carte carte3 = new Carte(NomCarte.DEUX_CENTS, TypeCarte.BORNE, 3);
    Carte carte4 = new Carte(NomCarte.VITESSE, TypeCarte.BOTTE, 4);
    Carte carte5 = new Carte(NomCarte.ESSENCE, TypeCarte.PARADE, 5);
    Carte carte6 = new Carte(NomCarte.FEU, TypeCarte.PARADE, 6);
    Carte carte7 = new Carte(NomCarte.CREVAISON, TypeCarte.BOTTE, 7);

    Plateau plateau = new Plateau();

    JoueurService playerService = new JoueurService();

    ArrayList<Joueur> listPlayer = new ArrayList<Joueur>();

    public FooterLayout(){

        listPlayer.add(myPlayer);
        listPlayer.add(notMyPlayer);

        Partie party = new Partie(listPlayer,2,3);

        deckPlayer.ajouter(carte1, myPlayer);
        deckPlayer.ajouter(carte2, myPlayer);
        deckPlayer.ajouter(carte3, myPlayer);
        deckPlayer.ajouter(carte4, myPlayer);
        deckPlayer.ajouter(carte5, myPlayer);
        deckPlayer.ajouter(carte6, myPlayer);
        deckPlayer.ajouter(carte7, myPlayer);

        HorizontalLayout footer = new HorizontalLayout();

        updateDeckPlayer(party, footer);
    }

    public void putCardPlayer(Partie party, HorizontalLayout footerLayout, Button putCardButton, Carte selectedCard){
        putCardButton.addClickListener(
            clickPutCard -> {
                if (selectedCard.getType() == TypeCarte.BORNE) {
                    playerService.poserCarteBorne(
                        selectedCard,
                        myPlayer
                    );
                } else if (selectedCard.getType() == TypeCarte.ATTAQUE) {
                    playerService.attaquer(
                        selectedCard,
                        notMyPlayer
                    );
                } else if (selectedCard.getType() == TypeCarte.BOTTE) {
                    playerService.poser(
                        selectedCard,
                        plateau.getPile(TypePile.BOTTES),
                        myPlayer
                    );
                } else {
                    playerService.poser(
                        selectedCard,
                        plateau.getPile(TypePile.VITESSE),
                        myPlayer
                    );
                }
                System.out.println(party.getListejoueur().get(0).getMain().getMainJoueur());
                updateDeckPlayer(party, footerLayout);
            }
        );
    }

    public void throwCardPlayer(Partie party, HorizontalLayout footerLayout, Button throwCardButton, Carte selectedCard){
        throwCardButton.addClickListener(
            click -> {
                playerService.jeter(selectedCard, party.getDefausse(), party.getListejoueur().get(0));
                System.out.println(party.getListejoueur().get(0).getMain().getMainJoueur());
                updateDeckPlayer(party, footerLayout);
            }
        );

    }

    public void updateDeckPlayer(Partie party , HorizontalLayout footerLayout){

        removeAll();
        footerLayout.removeAll();

        Button putCardButton = new Button("Poser");
        putCardButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);

        Button throwCardButton = new Button("Jeter");
        throwCardButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_ERROR);

        putCardButton.setEnabled(false);
        throwCardButton.setEnabled(false);

        VerticalLayout buttonsLayout = new VerticalLayout(putCardButton, throwCardButton);

        HorizontalLayout deckLayout = new HorizontalLayout();


        ArrayList<Image> deckPlayerImage = new ArrayList<Image>(myPlayer.getMain().getMainJoueur().size());

        for (int i = 0; i < playerService.getSizeDeck(myPlayer); i++) {

            Image image = new Image(
                "cartes/" + playerService.getCardInDeck(myPlayer,i).getStringImage(),
                String.valueOf(playerService.getCardInDeck(myPlayer,i)));
            image.addClassName("card-deck");

            deckPlayerImage.add(image);

            var chosenCard = deckPlayerImage.get(i);
            int j = i;
            deckPlayerImage.get(i).addClickListener(
                click -> {
                    chosenCard.getElement().getStyle().set("border-color", "white");
                    System.out.println("Le joueur: " + myPlayer.getPseudo() + " a choisi la carte " + chosenCard.getAlt());
                    Carte selectedCard = new Carte(
                        playerService.getCardInDeck(myPlayer,j).getNom(),
                        playerService.getCardInDeck(myPlayer,j).getType(),
                        playerService.getCardInDeck(myPlayer,j).getNumero()
                    );
                    putCardButton.setEnabled(true);
                    throwCardButton.setEnabled(true);

                    putCardPlayer(party, footerLayout, putCardButton, selectedCard);
                    throwCardPlayer(party, footerLayout, throwCardButton, selectedCard);
                }
            );
        }

        for(Image image: deckPlayerImage){
            deckLayout.add(image);
        }

        footerLayout.add(deckLayout, buttonsLayout);

        footerLayout.getStyle()
            .set("border-radius", "10px")
            .set("padding", "20px")
            //.set("height", "14em")
            .set("box-shadow", "0px 6px 10px rgba(0, 0, 0, 0.25)")
            .set("background", "linear-gradient(to right, #feada6, #f5efef)");

        add(footerLayout);
    }
}
