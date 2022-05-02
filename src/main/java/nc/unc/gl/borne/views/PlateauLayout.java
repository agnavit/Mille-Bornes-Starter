package nc.unc.gl.borne.views;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.carte.PileCarte;
import nc.unc.gl.borne.carte.TypePile;
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.plateau.Plateau;

@Route("testPlateauLayout")
@StyleSheet("css/decklayout.css")
public class PlateauLayout extends HorizontalLayout {

    Plateau plateau;

    public PlateauLayout(){
        //TODO version générique avec paramètre joueur
//        this.plateau = joueur.getPlateau();

        VerticalLayout pileVitesse = new VerticalLayout();
        pileVitesse.add("Pile vitesse");
        pileVitesse.add(new Image("cartes/attaque_accident.jpeg", "carte/attaque_accident"));

        VerticalLayout pileBataille = new VerticalLayout();
        pileBataille.add("Pile Bataille");
        pileBataille.add(new Image("cartes/parade_feu.jpeg", "carte/parade_feu"));

//        ----------------------------------------------------
        VerticalLayout pileBornes = new VerticalLayout();
        pileBornes.add("Pile bornes");

        HorizontalLayout pileBornesHori = new HorizontalLayout();
        Div vingtCinq = new Div();
        vingtCinq.add(new Image("cartes/borne_vingt_cinq.jpeg","cartes/borne_vingt_cinq"));
        vingtCinq.add(" X 0");

        Div cinquante = new Div();
        cinquante.add(new Image("cartes/borne_cinquante.jpeg","cartes/borne_cinquante"));
        cinquante.add(" X 0");

        Div soixanteQuinze = new Div();
        soixanteQuinze.add(new Image("cartes/borne_soixante_quinze.jpeg","cartes/borne_soixante_quinze"));
        soixanteQuinze.add(" X 0");

        Div cent = new Div();
        cent.add(new Image("cartes/borne_cent.jpeg","cartes/borne_cent"));
        cent.add(" X 0");

        Div deuxCents = new Div();
        deuxCents.add(new Image("cartes/borne_deux_cents.jpeg","cartes/borne_deux_cents"));
        deuxCents.add(" X 0");

        pileBornesHori.add(vingtCinq, cinquante, soixanteQuinze, cent, deuxCents);

        pileBornes.add(pileBornesHori);

        //        ----------------------------------------------------
        VerticalLayout pileBottes = new VerticalLayout();
        pileBottes.add("Pile bottes");
        pileBottes.add(new Image("cartes/botte_accident.jpeg", "cartes/botte_accident"));


        add(pileVitesse, pileBataille, pileBornes, pileBottes);
    }
}
