package nc.unc.gl.borne.partie;

import nc.unc.gl.borne.views.TestView;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
public class GameController {

    @GetMapping("/{idPartie}")
    public TestView getIdPartie(@PathVariable("idPartie") String idPartie) {
        TestView testView = new TestView();
        testView.add(idPartie);
        return testView;
    }
}
