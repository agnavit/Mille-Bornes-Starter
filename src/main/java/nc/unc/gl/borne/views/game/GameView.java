package nc.unc.gl.borne.views.game;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.dao.connection.partieDao.JoueurDao;
import nc.unc.gl.borne.partie.PartieService;

@Tag("game")
@Route("game")
public class GameView extends VerticalLayout implements HasUrlParameter<String>{

    public String idPartie;
    public PartieService partieService = new PartieService();
    JoueurDao joueurDao = new JoueurDao();

    public GameView() {
        /*Partie partie = new Partie();
        Joueur joueur = partie.getListejoueur().get(0);
        add("Joueur 1: " + partie.getListejoueur().get(0).getPseudo());
        add("Joueur 2: " + partie.getListejoueur().get(1).getPseudo());
        add("fenétre du joueur" + joueur.getPseudo());*/
        joueurDao.findAll().forEach(j -> add(j.getPseudo()));

        TopLayout topLayout = new TopLayout();

        PlateauLayout plateauLayout = new PlateauLayout();
        plateauLayout.getStyle().set("height", " 14em");

        FooterLayout footerLayout = new FooterLayout();

        add(topLayout, plateauLayout, footerLayout);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {
        add(new Text(s));
    }
}
