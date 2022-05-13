package nc.unc.gl.borne.views.game;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.carte.*;
import nc.unc.gl.borne.carte.enumerations.NomCarte;
import nc.unc.gl.borne.carte.enumerations.TypeCarte;
import nc.unc.gl.borne.carte.enumerations.TypePile;
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.joueur.JoueurService;

@Route("testPlateauLayout")
@StyleSheet("css/plateau.css")
public class PlateauLayout extends HorizontalLayout {

    Carte carte1 = new Carte(NomCarte.FEU, TypeCarte.PARADE, 1);
    Carte carte2 = new Carte(NomCarte.DEUX_CENTS, TypeCarte.BORNE, 3);
    Carte carte3 = new Carte(NomCarte.DEUX_CENTS, TypeCarte.BORNE, 4);
    Carte carte4 = new Carte(NomCarte.ACCIDENT, TypeCarte.BOTTE, 4);

    PileCarte defausse = new PileCarte();

    JoueurService playerService = new JoueurService();
    Joueur joueur = new Joueur(2, "Anthony", 22);

    // Création de deux nouvelles cartes
    Carte carteVitesse = new Carte(NomCarte.FEU, TypeCarte.PARADE, 5);
    Carte carteBATAILLE = new Carte(NomCarte.FEU, TypeCarte.PARADE, 6);

    public PlateauLayout(){
        //TODO version générique avec paramètre joueur
        //playerService.poser(carte1, defausse, joueur);
        //playerService.poser(carte2, defausse, joueur);
        //playerService.poser(carte3, defausse, joueur);
        //playerService.poser(carte4, defausse, joueur);

        // Je pose une carte dans les piles vitesse et bataille
        //playerService.poser(carteVitesse, joueur.getPlateau().getPile(TypePile.VITESSE), joueur);
        //playerService.poser(carteBATAILLE, joueur.getPlateau().getPile(TypePile.BATAILLE), joueur);
        //playerService.poser(carte4, joueur.getPlateau().getPile(TypePile.BOTTES), joueur);



        // Correction :
        //      - Ajout d'une carte dans les piles
        //      - Ajout "cartes/" +
        //------------------------------------------------------

        HorizontalLayout layout = new HorizontalLayout();

        VerticalLayout pileVitesse = new VerticalLayout();
        pileVitesse.add(new H3("Vitesse"));
        getImageSommetPile(TypePile.VITESSE, joueur, pileVitesse);

        //------------------------------------------------------

        VerticalLayout pileBataille = new VerticalLayout();
        pileBataille.add(new H3("Bataille"));

        getImageSommetPile(TypePile.BATAILLE, joueur, pileBataille);

        //------------------------------------------------------

        VerticalLayout pileBornes = new VerticalLayout();
        pileBornes.add(new H3("Bornes"));

        HorizontalLayout pileBornesCartes = new HorizontalLayout();

        Div vingtCinq = new Div();
        vingtCinq.add(new Image("cartes/borne_vingt_cinq.jpeg","cartes/borne_vingt_cinq"));
        vingtCinq.add(new H4(getNbCartePileBorneString(NomCarte.VINGT_CINQ, joueur)));


        Div cinquante = new Div();
        cinquante.add(new Image("cartes/borne_cinquante.jpeg","cartes/borne_cinquante"));
        cinquante.add(new H4(getNbCartePileBorneString(NomCarte.CINQUANTE, joueur)));

        Div soixanteQuinze = new Div();
        soixanteQuinze.add(new Image("cartes/borne_soixante_quinze.jpeg","cartes/borne_soixante_quinze"));
        soixanteQuinze.add(new H4(getNbCartePileBorneString(NomCarte.SOIXANTE_QUINZE, joueur)));


        Div cent = new Div();
        cent.add(new Image("cartes/borne_cent.jpeg","cartes/borne_cent"));
        cent.add(new H4(getNbCartePileBorneString(NomCarte.CENT, joueur)));


        Div deuxCents = new Div();
        deuxCents.add(new Image("cartes/borne_deux_cents.jpeg","cartes/borne_deux_cents"));
        deuxCents.add(new H4(getNbCartePileBorneString(NomCarte.DEUX_CENTS, joueur)));


        pileBornesCartes.add(vingtCinq, cinquante, soixanteQuinze, cent, deuxCents);

        pileBornes.add(pileBornesCartes);

        //------------------------------------------------------

        VerticalLayout pileBottes = new VerticalLayout();
        pileBottes.add(new H3("Pile bottes"));

        if (!joueur.getPlateau().getPile(TypePile.BOTTES).estVide()){
            for (int i = 0; i < playerService.getSizeDeck(joueur); i++) {

                pileBottes.add(new Image("cartes/" + joueur
                    .getPlateau()
                    .getPile(TypePile.BOTTES)
                    .getCarte(i)
                    .getStringImage(),
                    "carte/carteBottes"));
            }
        } else {
            pileBottes.add(new Image("cartes/back.png", "Dos de la carte"));
        }


        pileVitesse.addClassName("piles-cards");

        pileBataille.addClassName("piles-cards");

        pileBornes.addClassName("piles-cards");

        pileBottes.addClassName("piles-cards");


        layout.add(pileVitesse, pileBataille, pileBornes, pileBottes);

        add(layout);
    }

    public void getImageSommetPile(TypePile typePile,Joueur joueur, VerticalLayout pileCible){
        if(joueur.getPlateau().getPile(typePile).estVide()){
            pileCible.add(new Image("cartes/back.png", "Dos de la carte"));
        }
        pileCible
            .add(new Image("cartes/" +playerService.getLastCardInPile(joueur,typePile)
                .getStringImage(),
                "carte/sommetPileBataille"));
    }

    public String getNbCartePileBorneString(NomCarte nomCarte, Joueur joueur){
        return String.valueOf(joueur
                .getPlateau()
                .getPile(TypePile.BORNES)
                .getNbCartePile(new Carte(nomCarte, TypeCarte.BORNE, 0)));
    }
}
