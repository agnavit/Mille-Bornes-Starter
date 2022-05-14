package nc.unc.gl.borne.views.game;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.dnd.DropEvent;
import com.vaadin.flow.component.dnd.DropTarget;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.carte.*;
import nc.unc.gl.borne.carte.enumerations.NomCarte;
import nc.unc.gl.borne.carte.enumerations.TypeCarte;
import nc.unc.gl.borne.carte.enumerations.TypePile;
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.joueur.JoueurService;

import java.util.Optional;

public class PlateauLayout extends HorizontalLayout {

    FooterLayout footerLayout = new FooterLayout();
    VerticalLayout pileVitesse;
    VerticalLayout pileBornes;
    JoueurService playerService = new JoueurService();


    public PlateauLayout(Joueur joueur) {

        HorizontalLayout layout = new HorizontalLayout();

        pileVitesse = new VerticalLayout();
        pileVitesse.add(new H3("Vitesse"));
        getImageSommetPile(TypePile.VITESSE, joueur, pileVitesse);

        //------------------------------------------------------

        VerticalLayout pileBataille = new VerticalLayout();
        pileBataille.add(new H3("Bataille"));

        getImageSommetPile(TypePile.BATAILLE, joueur, pileBataille);

        //------------------------------------------------------

        pileBornes = new VerticalLayout();
        pileBornes.add(new H3("Bornes"));

        HorizontalLayout pileBornesCartes = new HorizontalLayout();

        Div vingtCinq = new Div();
        vingtCinq.add(new Image("cartes/borne_vingt_cinq.jpeg", "cartes/borne_vingt_cinq"));
        vingtCinq.add(new H4(getNbCartePileBorneString(NomCarte.VINGT_CINQ, joueur)));


        Div cinquante = new Div();
        cinquante.add(new Image("cartes/borne_cinquante.jpeg", "cartes/borne_cinquante"));
        cinquante.add(new H4(getNbCartePileBorneString(NomCarte.CINQUANTE, joueur)));

        Div soixanteQuinze = new Div();
        soixanteQuinze.add(new Image("cartes/borne_soixante_quinze.jpeg", "cartes/borne_soixante_quinze"));
        soixanteQuinze.add(new H4(getNbCartePileBorneString(NomCarte.SOIXANTE_QUINZE, joueur)));


        Div cent = new Div();
        cent.add(new Image("cartes/borne_cent.jpeg", "cartes/borne_cent"));
        cent.add(new H4(getNbCartePileBorneString(NomCarte.CENT, joueur)));


        Div deuxCents = new Div();
        deuxCents.add(new Image("cartes/borne_deux_cents.jpeg", "cartes/borne_deux_cents"));
        deuxCents.add(new H4(getNbCartePileBorneString(NomCarte.DEUX_CENTS, joueur)));


        pileBornesCartes.add(vingtCinq, cinquante, soixanteQuinze, cent, deuxCents);
        pileBornes.add(pileBornesCartes);

        //------------------------------------------------------

        VerticalLayout pileBottes = new VerticalLayout();
        pileBottes.add(new H3("Pile bottes"));

        if (!joueur.getPlateau().getPile(TypePile.BOTTES).estVide()) {
            for (int i = 0; i < joueur.getPlateau().getPile(TypePile.BOTTES).getTaille(); i++) {
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

        //------------------------------------------------------
        DropTarget.create(pileVitesse).addDropListener(this::onDrop);
        DropTarget.create(pileBataille).addDropListener(this::onDrop);
        DropTarget.create(pileBornes).addDropListener(this::onDrop);


        pileVitesse.addClassName("pile-vitesse");
        pileBataille.addClassName("pile-bataille");
        pileBornes.addClassName("pile-bornes");
        pileBottes.addClassName("pile-bottes");

        layout.add(pileVitesse, pileBataille, pileBornes, pileBottes);
        add(layout);
    }

    public void getImageSommetPile(TypePile typePile, Joueur joueur, VerticalLayout pileCible) {
        if (joueur.getPlateau().getPile(typePile).estVide()) {
            pileCible.add(new Image("cartes/back.png", "Dos de la carte"));
        } else {
            JoueurService playerService = new JoueurService();
            pileCible
                .add(new Image("cartes/" + playerService.getLastCardInPile(joueur, typePile)
                    .getStringImage(),
                    "carte/sommetPileBataille"));
        }
    }

    public String getNbCartePileBorneString(NomCarte nomCarte, Joueur joueur) {
        return String.valueOf(joueur
            .getPlateau()
            .getPile(TypePile.BORNES)
            .getNbCartePile(new Carte(nomCarte, TypeCarte.BORNE, 0)));
    }

    private void onDrop(DropEvent<VerticalLayout> event) {
        Optional<Object> carteFooter = event.getDragData(); //carte du footerlayout getter
        Component imageCarte = event.getDragSourceComponent().get();//composant image@5555
        Component carteDrag = event.getComponent();//ComponentVerticalLayout ou on pose
        Carte carte = (Carte) carteFooter.get();//Carte poser
        if (event.getDragSourceComponent().isPresent() && event.getDragSourceComponent().get() == imageCarte) {
            //poser dans le back
            //poser dans le front
            pileBornes.add(imageCarte);
            //update le back
            footerLayout.add(imageCarte);
        }
        else {
            Notification.show("Mouvement impossible");
        }
//        private void onDrop(DropEvent<VerticalLayout> event) {
//            if (event.getDragSourceComponent().isPresent() && event.getDragSourceComponent().get() == image) {
//                String dragData = (String) event.getDragData().orElse("");
//                if ("From bataille".equals(dragData) && event.getComponent() == bataille) {
//                    deck.remove(image);
//                    bataille.add(image);
//                }
    }
}
