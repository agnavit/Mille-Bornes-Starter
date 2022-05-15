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

    private Joueur firstPlayer;
    private Joueur secondePlayer;

    public TopLayout(Joueur myPlayer, Joueur notMyPlayer){

        VerticalLayout topLayout = new VerticalLayout();

        if(myPlayer.getScore() == notMyPlayer.getScore()){
            H2 highScore = new H2("1er " + myPlayer.getPseudo() + " : " + myPlayer.getScore());
            highScore.addClassName("first-player");

            H2 lowScore = new H2("1er " + notMyPlayer.getPseudo() + " : " + notMyPlayer.getScore());
            lowScore.addClassName("first-player");
            topLayout.add(highScore, lowScore);
            topLayout.addClassName("top");

        } else {

            if(myPlayer.getScore() > notMyPlayer.getScore()){
                firstPlayer = myPlayer;
                secondePlayer = notMyPlayer;
            } else if(myPlayer.getScore() < notMyPlayer.getScore()){
                firstPlayer = notMyPlayer;
                secondePlayer = myPlayer;
            }

            H2 highScore = new H2("1er " + firstPlayer.getPseudo() + " : " + firstPlayer.getScore());
            highScore.addClassName("first-player");

            H2 lowScore = new H2("2nd " +secondePlayer.getPseudo() + " : " + secondePlayer.getScore());
            lowScore.addClassName("seconde-player");

            topLayout.add(highScore, lowScore);
            topLayout.addClassName("top");
        }

        add(topLayout);
    }
}
