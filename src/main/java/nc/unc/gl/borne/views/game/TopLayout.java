package nc.unc.gl.borne.views.game;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.joueur.Joueur;

@Route("testTopLayout")
@Tag("testTopLayout")
@StyleSheet("css/toplayout.css")
public class TopLayout extends VerticalLayout {

    public TopLayout(Joueur myPlayer, Joueur notMyPlayer){

        VerticalLayout topLayout = new VerticalLayout();

        VerticalLayout scoresPlayers = new VerticalLayout();
        H2 myScore = new H2(myPlayer.getPseudo() + " : " + myPlayer.getScore());
        myScore.addClassName("test2");

        H2 notMyScore = new H2(notMyPlayer.getPseudo() + " : " + notMyPlayer.getScore());
        notMyScore.addClassName("test2");

        scoresPlayers.add(myScore, notMyScore);
        H1 top2 = new H1("Score");
        topLayout.add(top2, scoresPlayers);
        top2.addClassName("test2");

        topLayout.addClassName("test");
        add(topLayout);
    }
}
