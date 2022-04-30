package nc.unc.gl.borne.views;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route("testFooterLayout")
public class FooterLayout extends HorizontalLayout {

    public FooterLayout(){
        DeckLayout deckLayout = new DeckLayout();
        ActionLayout actionLayout = new ActionLayout();

        add(deckLayout,actionLayout);
    }
}
