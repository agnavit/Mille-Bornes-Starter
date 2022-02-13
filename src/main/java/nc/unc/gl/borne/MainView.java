package nc.unc.gl.borne;

import java.util.Enumeration;
import java.util.Properties;

import com.vaadin.flow.component.HtmlContainer;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

@Route
@Tag("borne-main")
public class MainView extends HtmlContainer {

    public MainView() {
    	Image img = new Image("/cartes/back.png", "Mille Bornes App");
    	
    	H1 title = new H1("Mille Bornes App");
        title.getStyle()
          .set("font-size", "var(--lumo-font-size-l)")
          .set("left", "var(--lumo-space-l)")
          .set("margin-left", "700px")
          .set("position", "absolute")
          .set("top", "-13px")
          .set("color", "#FAFAFA")
          .set("font-style", "bold")
          .set("z-index", "1");

        Tabs tabs = getTabs();
        
        add(title, tabs, img);

    }
    
    private Tabs getTabs() {
    	Tabs tabs = new Tabs();
        tabs.getStyle()
        	.set("margin", "auto")
        	.set("background-color", "#A9CEF4");
        tabs.add(
          createTab("Sign in"),
          createTab("Regles"),
          createTab("Multi-Joeurs"),
          createTab("Solo")
        );
        return tabs;
      }
    
    private Tab createTab(String viewName) {
        RouterLink link = new RouterLink();
        link.add(viewName);
        
        link.setTabIndex(-1);
        Tab tab = new Tab(link);
        tab.getStyle()
        	.set("color", "white");
        return tab;
      }
}
