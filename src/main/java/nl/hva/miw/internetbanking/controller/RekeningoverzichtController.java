package nl.hva.miw.internetbanking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RekeningoverzichtController {

    private Logger logger = LoggerFactory.getLogger(RekeningoverzichtController.class);

    public RekeningoverzichtController() {
        logger.warn("New RekeningoverzichtController.");
    }

    @GetMapping("/rekeningoverzicht") // http://localhost:8080/rekeningoverzicht
    public String rekeningoverzichtHandler() {
        return "pages/rekeningoverzicht";
    }

    @GetMapping("/nieuwe-klant") // http://localhost:8080/nieuwe-klant
    public String nieuweKlantHandler() {
        return "";
    } // TODO: verwijzen naar nieuwe klant pagina


}
