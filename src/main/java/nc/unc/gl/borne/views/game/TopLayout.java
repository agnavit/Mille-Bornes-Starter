package nc.unc.gl.borne.views.game;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import nc.unc.gl.borne.metier.classes.Joueur;

public class TopLayout extends VerticalLayout {

    private Joueur firstPlayer;
    private Joueur secondePlayer;

    public TopLayout(Joueur me, Joueur notMe) {

        VerticalLayout topLayout = new VerticalLayout();

        if(me.getScore() == notMe.getScore()) {

            H2 highScore = new H2("1er " + me.getPseudo() + " : " + me.getScore());
            highScore.addClassName("first-player");

            H2 lowScore = new H2("1er " + notMe.getPseudo() + " : " + notMe.getScore());
            lowScore.addClassName("first-player");

            topLayout.add(highScore, lowScore);
            topLayout.addClassName("top");

        } else {

            if(me.getScore() > notMe.getScore()) {
                firstPlayer = me;
                secondePlayer = notMe;
            } else if(me.getScore() < notMe.getScore()) {
                firstPlayer = notMe;
                secondePlayer = me;
            }

            H2 highScore = new H2("1er " + firstPlayer.getPseudo() + " : " + firstPlayer.getScore());
            highScore.addClassName("first-player");

            H2 lowScore = new H2("2nd " + secondePlayer.getPseudo() + " : " + secondePlayer.getScore());
            lowScore.addClassName("seconde-player");

            topLayout.add(highScore, lowScore);
            topLayout.addClassName("top");
        }

        add(topLayout);
    }
}
