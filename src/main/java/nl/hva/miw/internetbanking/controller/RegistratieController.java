package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.data.dto.BusinessRegistrationDto;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistratieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistratieController.class);

    @GetMapping("/registratie")
    public String registrationGetHandler(Model model) {
        if (!model.containsAttribute("personPrivate")) {
            model.addAttribute("personPrivate", new NaturalPerson());
        }
        if (!model.containsAttribute("businessRegistrationDto")) {
            model.addAttribute("businessRegistrationDto", new BusinessRegistrationDto());
        }
        return "pages/registratie";
    }

    @PostMapping("/registration_private")
    public String registrationPostHandlerPrivate(
            @ModelAttribute NaturalPerson personPrivate,
            @ModelAttribute BusinessRegistrationDto businessRegistrationDto,
            Model model) {
        model.addAttribute("personPrivate", personPrivate);
        model.addAttribute("businessRegistrationDto", businessRegistrationDto);
        return "pages/registratie";
    }

    @PostMapping("/registration_legal")
    public String registrationPostHandlerLegal(
            @ModelAttribute NaturalPerson privatePerson,
            @ModelAttribute BusinessRegistrationDto businessRegistrationDto,
            Model model
    ) {
        model.addAttribute("personPrivate", privatePerson);
        model.addAttribute("businessRegistrationDto", businessRegistrationDto);
        LOGGER.warn(model.getAttribute("personPrivate").toString());
        LOGGER.warn(model.getAttribute("businessRegistrationDto").toString());
        return "pages/registratie";
    }

    @PostMapping("/check_legal_contact")
    public String checkLegalContactHandler(
            @ModelAttribute NaturalPerson privatePerson,
            @ModelAttribute BusinessRegistrationDto businessRegistrationDto,
            Model model
    ) {
        model.addAttribute("personPrivate", privatePerson);
        model.addAttribute("businessRegistrationDto", businessRegistrationDto);
        LOGGER.warn(model.getAttribute("personPrivate").toString());
        LOGGER.warn(model.getAttribute("businessRegistrationDto").toString());
        return "pages/registratie";
    }

}
