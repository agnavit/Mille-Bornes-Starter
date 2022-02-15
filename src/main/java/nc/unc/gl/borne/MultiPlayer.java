package nc.unc.gl.borne;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.Route;

@Route
@Tag("multi-player")
public class MultiPlayer extends HtmlContainer {
	
	public MultiPlayer() {
		H1 title = new H1("hello word Multi-player");
		add(title);
	}
}
