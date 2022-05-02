package nc.unc.gl.borne.partie;

import com.vaadin.flow.component.Text;
import nc.unc.gl.borne.views.TestView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class GameController {

    @GetMapping("/{idPartie}")
    public String getIdPartie(@PathVariable String idPartie) {
        TestView testView = new TestView();
        testView.add(idPartie);
        return idPartie;
    }
}
