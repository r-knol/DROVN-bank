package nl.hva.miw.internetbanking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistratieController {

    @GetMapping("/registratie")
    public String registratieGetHandler() {
        return "pages/registratie";
    }

}
