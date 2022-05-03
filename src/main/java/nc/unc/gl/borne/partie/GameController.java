package nc.unc.gl.borne.partie;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/test")
public class GameController {

    @GetMapping("/{idPartie}")
    public String getIdPartie(@PathVariable("idPartie") String idPartie) {
        return idPartie;
    }
}
