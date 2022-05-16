package nc.unc.gl.borne.views.game;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import nc.unc.gl.borne.MilleBornesApplication;
import nc.unc.gl.borne.metier.classes.ObserverGame;
import nc.unc.gl.borne.dao.connection.partieDao.JoueurDao;
import nc.unc.gl.borne.metier.classes.Joueur;
import nc.unc.gl.borne.metier.classes.partie.Game;
import nc.unc.gl.borne.metier.classes.partie.Partie;
import nc.unc.gl.borne.metier.services.partie.PartieService;

@Route("game/:idPartie?/:pseudoJoueur?")
@StyleSheet("css/game-view.css")
public class GameView extends VerticalLayout implements ObserverGame, BeforeEnterObserver {

    private final UI ui;
    String nomJoueur;
    Partie partie = MilleBornesApplication.getPartieList().get(0);

    Joueur p1;
    Joueur p2;

    public PartieService partieService = new PartieService();
    JoueurDao joueurDao = new JoueurDao();
    public static Game game = new Game();

    private String idPartie;

    Button notiff = new Button();

    @Override
    public void beforeEnter(BeforeEnterEvent event) {

        idPartie = event.getRouteParameters().get("idPartie").
            orElse("error");
        nomJoueur = event.getRouteParameters().get("pseudoJoueur").
            orElse("error");

        if (nomJoueur.equals(partie.getListejoueur().get(0).getPseudo())) {
            p1 = partie.getListejoueur().get(0);
            p2 = partie.getListejoueur().get(1);
        } else {
            p1 = partie.getListejoueur().get(1);
            p2 = partie.getListejoueur().get(0);
        }


        HorizontalLayout top = new HorizontalLayout();
        TopLayout topLayout = new TopLayout(p1, p2);


        PlateauLayout plateauLayout = new PlateauLayout(p2);
        plateauLayout.addClassName("not-my-pile-cards");


        top.add(topLayout, plateauLayout);

        PlateauLayout plateauLayout2 = new PlateauLayout(p1);
        plateauLayout2.addClassName("my-pile-cards");
        FooterLayout footerLayout = new FooterLayout(partie, p1, p2);

        VerticalLayout layout = new VerticalLayout();

        layout.add(top, plateauLayout2, footerLayout);
        layout.setJustifyContentMode(JustifyContentMode.BETWEEN);
        layout.setAlignItems(Alignment.CENTER);

        notiff.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        if (p1.getMain().getTaille() == 7) {
            notiff.setText("C'est à votre tour de jouer !");
        } else {
            notiff.setText("C'est au tour de " + p2.getPseudo() + " de jouer.");
        }

        layout.add(notiff);

        add(layout);

        this.getStyle().set("height", "100%")
            .set("background-image", "url(background.jpg)")
            .set("background-size", "cover")
            .set("background-repeat", "no-repeat");
    }

    private Icon createIcon(VaadinIcon vaadinIcon) {
        Icon icon = vaadinIcon.create();
        icon.getStyle().set("padding", "var(--lumo-space-xs");
        return icon;
    }

    public GameView() {
        this.ui = UI.getCurrent();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        game.addObserveur(this);
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        game.removeObserveur(this);
    }

    @Override
    public void updateWindowEnGame() {
        ui.access(() -> {
            removeAll();

            HorizontalLayout top = new HorizontalLayout();
            TopLayout topLayout = new TopLayout(p1, p2);
            PlateauLayout plateauLayout = new PlateauLayout(p2);

            top.add(topLayout, plateauLayout);

            plateauLayout.addClassName("not-my-pile-cards");
            PlateauLayout plateauLayout2 = new PlateauLayout(p1);
            plateauLayout2.addClassName("my-pile-cards");
            FooterLayout footerLayout = new FooterLayout(partie, p1, p2);

            VerticalLayout layout = new VerticalLayout();

            layout.add(top, plateauLayout2, footerLayout);
            layout.setJustifyContentMode(JustifyContentMode.BETWEEN);
            layout.setAlignItems(Alignment.CENTER);

            notiff.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            if (p1.getMain().getTaille() == 7) {
                notiff.setText("C'est à votre tour de jouer !");
            } else {
                notiff.setText("C'est au tour de " + p2.getPseudo() + " de jouer.");
            }

            layout.add(notiff);

            add(layout);

        });
    }
}
