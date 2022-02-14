package nc.unc.gl.borne;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

@Route
@Tag("regle-jeu")
public class RegleJeu extends HtmlContainer {
	
	public RegleJeu() {
		H1 title = new H1("hello word Regle");
		add(title);
	}
}
