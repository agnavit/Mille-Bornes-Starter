package nc.unc.gl.borne.views.game;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.carte.*;
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.joueur.JoueurService;

@Route("testPlateauLayout")
@StyleSheet("css/plateau.css")
public class PlateauLayout extends HorizontalLayout {

    Carte carte1 = new Carte(NomCarte.FEU, TypeCarte.PARADE, 1);
    Carte carte2 = new Carte(NomCarte.DEUX_CENTS, TypeCarte.BORNE, 3);
    Carte carte3 = new Carte(NomCarte.DEUX_CENTS, TypeCarte.BORNE, 4);
    //Carte carte4 = new Carte(NomCarte., TypeCarte.BOTTE, 4);
    PileCarte defausse = new PileCarte();

    JoueurService playerService = new JoueurService();
    Joueur player = new Joueur(2, "Anthony", 22);

    // Création de deux nouvelles cartes
    Carte carteVitesse = new Carte(NomCarte.FEU, TypeCarte.PARADE, 5);
    Carte carteBATAILLE = new Carte(NomCarte.FEU, TypeCarte.PARADE, 6);
    Carte carteBotte = new Carte(NomCarte.CREVAISON, TypeCarte.BOTTE, 7);
    Carte carteBotte2 = new Carte(NomCarte.ESSENCE, TypeCarte.BOTTE, 8);


    public PlateauLayout(){
        //TODO version générique avec paramètre joueur
        playerService.poser(carte1, defausse, player);
        playerService.poser(carte2, defausse, player);
        playerService.poser(carte3, defausse, player);

        // Je pose une carte dans les piles vitesse et bataille
        playerService.poser(carteVitesse, player.getPlateau().getPile(TypePile.VITESSE), player);
        playerService.poser(carteBATAILLE, player.getPlateau().getPile(TypePile.BATAILLE), player);
        playerService.poser(carteBotte, player.getPlateau().getPile(TypePile.BOTTES), player);
        playerService.poser(carteBotte2, player.getPlateau().getPile(TypePile.BOTTES), player);

        VerticalLayout pileVitesse = new VerticalLayout();
        pileVitesse.add(new H3("Vitesse"));
        pileVitesse
            .add(new Image("cartes/" + playerService.getLastCardInPile(player, TypePile.VITESSE).getStringImage(),
            "Dernière carte de la pile vitesse"));

        pileVitesse.setAlignItems(Alignment.CENTER);

        //------------------------------------------------------

        VerticalLayout pileBataille = new VerticalLayout();
        pileBataille.add(new H3("Bataille"));
        pileBataille
            .add(new Image("cartes/" + playerService.getLastCardInPile(player, TypePile.VITESSE).getStringImage(),
            "Dernière carte de la pile Bataille"));

        pileBataille.setAlignItems(Alignment.CENTER);

        //------------------------------------------------------

        var plateau = player.getPlateau();

        VerticalLayout pileBornes = new VerticalLayout();
        pileBornes.add(new H3("Bornes"));

        HorizontalLayout pileBornesCartes = new HorizontalLayout();

        Div vingtCinq = new Div();
        vingtCinq.add(new Image("cartes/borne_vingt_cinq.jpeg","cartes/borne_vingt_cinq"));
        vingtCinq.add(new H4("x " +
                plateau
                .getPile(TypePile.BORNES)
                .getNbCartePile(new Carte(NomCarte.VINGT_CINQ, TypeCarte.BORNE, 0))));

        Div cinquante = new Div();
        cinquante.add(new Image("cartes/borne_cinquante.jpeg","cartes/borne_cinquante"));
        cinquante.add(new H4("x " +
                plateau
                .getPile(TypePile.BORNES)
                .getNbCartePile(new Carte(NomCarte.CINQUANTE, TypeCarte.BORNE, 0))));

        Div soixanteQuinze = new Div();
        soixanteQuinze.add(new Image("cartes/borne_soixante_quinze.jpeg","cartes/borne_soixante_quinze"));
        soixanteQuinze.add(new H4("x " +
                plateau
                .getPile(TypePile.BORNES)
                .getNbCartePile(new Carte(NomCarte.SOIXANTE_QUINZE, TypeCarte.BORNE, 0))));

        Div cent = new Div();
        cent.add(new Image("cartes/borne_cent.jpeg","cartes/borne_cent"));
        cent.add(new H4("x " +
                plateau
                .getPile(TypePile.BORNES)
                .getNbCartePile(new Carte(NomCarte.CENT, TypeCarte.BORNE, 0))));

        Div deuxCents = new Div();
        deuxCents.add(new Image("cartes/borne_deux_cents.jpeg","cartes/borne_deux_cents"));
        deuxCents.add(new H4("x " +
                plateau
                .getPile(TypePile.BORNES)
                .getNbCartePile(new Carte(NomCarte.DEUX_CENTS, TypeCarte.BORNE, 0))));

        pileBornesCartes.add(vingtCinq, cinquante, soixanteQuinze, cent, deuxCents);

        pileBornes.add(pileBornesCartes);

        pileBornes.setAlignItems(Alignment.CENTER);

        // Correction : Ajout "cartes/" +
        VerticalLayout pileBottes = new VerticalLayout();
        pileBottes.add(new H3("Bottes"));
        HorizontalLayout cards = new HorizontalLayout();
        for (int i = 0; i < player.getPlateau().getPile(TypePile.BOTTES).getTaille(); i++) {
            cards.add(new Image("cartes/" +plateau
                .getPile(TypePile.BOTTES)
                .getCarte(i)
                .getStringImage(),
                "carte/carteBottes"));

        }

        pileBottes.add(cards);
        pileBottes.setAlignItems(Alignment.CENTER);

        HorizontalLayout plateau3 = new HorizontalLayout(pileVitesse, pileBataille, pileBornes, pileBottes);
        add(plateau3);
    }
}
