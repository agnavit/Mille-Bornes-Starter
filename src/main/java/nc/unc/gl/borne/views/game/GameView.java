package nc.unc.gl.borne.views.game;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import nc.unc.gl.borne.MilleBornesApplication;
import nc.unc.gl.borne.ObserverGame;
import nc.unc.gl.borne.dao.connection.partieDao.JoueurDao;
import nc.unc.gl.borne.partie.Game;
import nc.unc.gl.borne.partie.Partie;
import nc.unc.gl.borne.partie.PartieService;

import java.util.ArrayList;

@Route("game/:idPartie?/:pseudoJoueur?")
@StyleSheet("css/game.css")
public class GameView extends VerticalLayout implements ObserverGame, BeforeEnterObserver, AfterNavigationObserver {

    private final UI ui;

    public PartieService partieService = new PartieService();
    JoueurDao joueurDao = new JoueurDao();
    Game game = new Game();

    private String idPartie;

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        idPartie = event.getRouteParameters().get("idPartie").
            orElse("error");
    }

    public GameView() {
        this.ui = UI.getCurrent();

        PlateauLayout plateauLayout = new PlateauLayout();
        plateauLayout.addClassName("not-my-pile-cards");
        PlateauLayout plateauLayout2 = new PlateauLayout();
        plateauLayout2.addClassName("my-pile-cards");
        FooterLayout footerLayout = new FooterLayout();

        VerticalLayout layout = new VerticalLayout();

        layout.add(plateauLayout, plateauLayout2, footerLayout);
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        layout.setAlignItems(Alignment.CENTER);

        add(layout);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        game.addObserveurPartie(this);
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        game.removeObserveurPartie(this);
    }

    @Override
    public void updateWindowParty(String idPartie) {
        ui.access(() -> {
            joueurDao.findJoueurWherePartieId(idPartie).forEach(j -> {
                add(j.getPseudo());
                System.out.println(j.getPseudo());
            });
        });
    }

    @Override
    public void updatePoser(Partie partie) {

    }

    @Override
    public void updateJeter(Partie partie) {

    }

    @Override
    public void updateScore(Partie partie) {

    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        game.getAllPlayer(idPartie);
    }
}
