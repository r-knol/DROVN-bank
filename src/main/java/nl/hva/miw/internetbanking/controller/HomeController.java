package nl.hva.miw.internetbanking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistratieController.class);

    @RequestMapping("/")
    public String homeGetHandler(Model model) {
        if (model.containsAttribute("accountCreated")) {
            LOGGER.warn("Account created!");
        }
        return "pages/home";
    }

}
