package nc.unc.gl.borne.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import nc.unc.gl.borne.joueur.Joueur;

@Route("test3")
@Tag("test3")
@StyleSheet("css/toplayout.css")
public class TopLayout extends VerticalLayout {

    Joueur myPlayer = new Joueur(1, "Anthony", 22);
    Joueur notMyPlayer = new Joueur(2, "Jason", 21);

    public TopLayout(){
        VerticalLayout topLayout = new VerticalLayout();

        VerticalLayout scoresPlayers = new VerticalLayout();
        H2 myScore = new H2(myPlayer.getPseudo() + " : " + myPlayer.getScore());
        H2 notMyScore = new H2(notMyPlayer.getPseudo() + " : " + notMyPlayer.getScore());

        scoresPlayers.add(myScore, notMyScore);
        topLayout.add(new H1("Score"), scoresPlayers);
        topLayout.getStyle().set("width", "100px");

        add(topLayout);
    }
}
