package nc.unc.gl.borne;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

@Route
@Tag("single-player")
public class SinglePlayer extends HtmlContainer {
	
	public SinglePlayer() {
		H1 title = new H1("hello word Solo");
		add(title);
	}
}
