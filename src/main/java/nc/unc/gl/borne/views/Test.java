package nc.unc.gl.borne.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.Deck.DeckService;
import nc.unc.gl.borne.carte.*;
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.joueur.JoueurService;
import nc.unc.gl.borne.plateau.Plateau;

import java.util.ArrayList;


@Route("yoya")
@StyleSheet("css/decklayout.css")
public class Test extends VerticalLayout {

    protected DeckService deckPlayer = new DeckService();

    Joueur myPlayer = new Joueur(1, "Anthony", 22);
    Joueur notMyPlayer = new Joueur(2, "Jason", 21);

    Carte carte = new Carte(NomCarte.ACCIDENT, TypeCarte.ATTAQUE, 1);
    Carte carte2 = new Carte(NomCarte.ESSENCE, TypeCarte.ATTAQUE, 2);
    Carte carte3 = new Carte(NomCarte.DEUX_CENTS, TypeCarte.BORNE, 3);

    Plateau plateau = new Plateau();

    JoueurService playerService = new JoueurService();

    public Test() {

        deckPlayer.ajouter(carte, myPlayer);
        deckPlayer.ajouter(carte2, myPlayer);
        deckPlayer.ajouter(carte3, myPlayer);
        deckPlayer.ajouter(carte3, myPlayer);
        deckPlayer.ajouter(carte3, myPlayer);
        deckPlayer.ajouter(carte3, myPlayer);
        deckPlayer.ajouter(carte3, myPlayer);

        VerticalLayout test = new VerticalLayout();
        HorizontalLayout footer = new HorizontalLayout();

        HorizontalLayout deckLayout = new HorizontalLayout();
        VerticalLayout topLayout = new VerticalLayout();

        VerticalLayout scoresPlayers = new VerticalLayout();
        H2 myScore = new H2(myPlayer.getPseudo() + " : " + myPlayer.getScore());
        H2 notMyScore = new H2(notMyPlayer.getPseudo() + " : " + notMyPlayer.getScore());

        scoresPlayers.add(myScore, notMyScore);
        topLayout.add(new H1("Score"), scoresPlayers);
        topLayout.getStyle().set("width", "100px");

        Button putCard = new Button("Poser");
        putCard.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);
        Button throwCard = new Button("Jeter");
        throwCard.addThemeVariants(ButtonVariant.LUMO_PRIMARY, ButtonVariant.LUMO_CONTRAST);

        putCard.setEnabled(false);
        throwCard.setEnabled(false);

        ArrayList<Image> deckPlayerImage = new ArrayList<Image>(myPlayer.getMain().getMainJoueur().size());

        for (Integer i = 0; i < playerService.getSizeDeck(myPlayer); i++) {

            Image image = new Image(
                "cartes/" + playerService.getCardInDeck(myPlayer,i).getStringImage(),
                String.valueOf(playerService.getCardInDeck(myPlayer,i)));
            image.addClassName("image-deck");

            deckPlayerImage.add(image);

            var chosenCard = deckPlayerImage.get(i);
            int j = i;
            deckPlayerImage.get(i).addClickListener(
                click -> {
                    //click.getSource().getElement().getStyle().set("border-color", "gold");
                    chosenCard.getElement().getStyle().set("border-color", "gold");
                    System.out.println("Le joueur: " + myPlayer.getPseudo() + " a choisi la carte " + chosenCard.getAlt());
                    Carte selectedCard = new Carte(
                        playerService.getCardInDeck(myPlayer,j).getNom(),
                        playerService.getCardInDeck(myPlayer,j).getType(),
                        playerService.getCardInDeck(myPlayer,j).getNumero()
                    );
                    putCard.setEnabled(true);
                    throwCard.setEnabled(true);
                    int k = j;
                    putCard.addClickListener(
                        clickPutCard -> {
                            //TODO: Poser la carte choisi
                            if (selectedCard.getType() == TypeCarte.BORNE) {
                                playerService.poserCarteBorne(
                                    selectedCard,
                                    myPlayer
                                );
                                deckLayout.remove(deckPlayerImage.get(k));
                                myScore.setText(myPlayer.getPseudo() + " : " + myPlayer.getScore());
                                //deckPlayer.enlever(selectedCard, myPlayer);

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
                        }
                    );
                }
            );

            throwCard.addClickListener(
                click -> {
                    //TODO: Jeter la carte choisi

                }
            );
        }


        for(Image image: deckPlayerImage){
            deckLayout.add(image);
        }


        VerticalLayout buttonsLayout = new VerticalLayout(putCard, throwCard);

        footer.add(deckLayout, buttonsLayout);
        test.add(topLayout, footer);
        add(test);
    }

}
