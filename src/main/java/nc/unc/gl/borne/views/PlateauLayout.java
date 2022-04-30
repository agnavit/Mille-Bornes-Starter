package nc.unc.gl.borne.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import nc.unc.gl.borne.carte.PileCarte;
import nc.unc.gl.borne.carte.TypePile;
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.plateau.Plateau;

public class PlateauLayout extends HorizontalLayout {

    Plateau plateau;

    public PlateauLayout(Joueur joueur){
        this.plateau = joueur.getPlateau();

//        Div pileBat = plateau.



    }
}
