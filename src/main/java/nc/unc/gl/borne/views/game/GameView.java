package nc.unc.gl.borne.views.game;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.Observer;
import nc.unc.gl.borne.ObserverPartie;
import nc.unc.gl.borne.dao.connection.partieDao.JoueurDao;
import nc.unc.gl.borne.joueur.Joueur;
import nc.unc.gl.borne.partie.Partie;
import nc.unc.gl.borne.partie.PartieService;

@Tag("game")
@Route("game")
@StyleSheet("css/game.css")
public class GameView extends VerticalLayout implements HasUrlParameter<String>, ObserverPartie {

    public String idPartie;
    public PartieService partieService = new PartieService();
    JoueurDao joueurDao = new JoueurDao();
    Partie party = new Partie();

    public GameView() {
        /*Partie partie = new Partie();
        Joueur joueur = partie.getListejoueur().get(0);
        add("Joueur 1: " + partie.getListejoueur().get(0).getPseudo());
        add("Joueur 2: " + partie.getListejoueur().get(1).getPseudo());
        add("fenétre du joueur" + joueur.getPseudo());*/
        party.getAllPlayer(idPartie);
        PlateauLayout plateauLayout = new PlateauLayout();
        plateauLayout.addClassName("not-my-pile-cards");
        PlateauLayout plateauLayout2 = new PlateauLayout();
        plateauLayout2.addClassName("my-pile-cards");
        FooterLayout footerLayout = new FooterLayout();
        footerLayout.addClassName("my-deck");

        VerticalLayout layout = new VerticalLayout();

        layout.add(plateauLayout, plateauLayout2, footerLayout);
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        layout.setAlignItems(Alignment.CENTER);

        add(layout);

        //add(String.valueOf(party.getListejoueur()));
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        idPartie = s;
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        party.addObserveurPartie(this);
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        party.removeObserveurPartie(this);
    }

    @Override
    public void updateWindowParty(String idPartie) {
        joueurDao.findJoueurWherePartieId(idPartie).forEach(j -> {
            add(j.getPseudo());
        });
    }
}
