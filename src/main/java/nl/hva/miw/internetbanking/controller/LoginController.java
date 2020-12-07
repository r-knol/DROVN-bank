package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.model.Klant;
import nl.hva.miw.internetbanking.service.KlantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final KlantService klantService;

    @Autowired
    public LoginController(KlantService klantService) {
        super();
        this.klantService = klantService;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/login") // controller praat alleen tegen service
    public String handleLogin(@RequestParam String gebruikersnaam, String wachtwoord) { // Gaat iets doen met ingevoerde G en W
        if (klantService.valideerLogin(gebruikersnaam, wachtwoord)) { // roept service op
            return "pages/rekeningoverzicht";
        }
        else {
            return "pages/foutpagina";
        }
    }

    // submit requesthandler (om request van inloggen (OK) af te handelen)
    // password controleren, foutpagina of toegang
}
