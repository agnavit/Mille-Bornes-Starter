package nc.unc.gl.borne;

import org.apache.http.conn.routing.RouteTracker;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route
@Tag("borne-main")
public class MainView extends HtmlContainer {

    public MainView() {
    	//TO DO afficher l'image
    	Image img = new Image("/cartes/header.png", "Mille Bornes logo");

    	H1 title = new H1("Mille Bornes App");
        title.getStyle()
          .set("font-size", "var(--lumo-font-size-l)")
          .set("left", "var(--lumo-space-l)")
          .set("margin-left", "700px")
          .set("position", "absolute")
          .set("top", "-1px")
          .set("color", "#FAFAFA")
          .set("font-style", "bold")
          .set("z-index", "1");

        Tabs tabs = getTabs();
        tabs.getStyle()
    	.set("padding", "10px")
    	.set("background-color", "#2E86C1");
        
        Div div = new Div();
        div.getStyle()
        	.set("background-color", "#EA2418")
        	.set("width", "100%")
        	.set("height", "5px");
        
        add(title, tabs, div, img);
    }
    
    private Tabs getTabs() {
    	Tabs tabs = new Tabs();
        
        tabs.add(
          createTab("Sign in", SignIn.class),
          createTab("Regles", RegleJeu.class),
          createTab("Multi-Joeurs", MultiPlayer.class),
          createTab("Solo", Solo.class)
        );
        return tabs;
      }
    
    private Tab createTab(String viewName, Class className) {
        RouterLink link = new RouterLink();
        link.add(viewName);
        link.setRoute(className);
        link.setTabIndex(-1);
        Tab tab = new Tab(link);
        tab.getStyle()
        	.set("color", "white");
        
        return tab;
      }
}
