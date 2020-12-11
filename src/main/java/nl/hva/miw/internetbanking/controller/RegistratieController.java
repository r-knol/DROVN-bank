package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.data.dto.RegistrationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistratieController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistratieController.class);
    private RegistrationDto registrationDto = new RegistrationDto();

    @GetMapping("/registratie")
    public String registrationGetHandler(Model model) {
        if (!model.containsAttribute("registrationDto")) {
            model.addAttribute("registrationDto", registrationDto);
        }
        return "pages/registratie";
    }

    @PostMapping("/bevestiging-particulier")
    public String registrationPostHandlerPrivate(@ModelAttribute(name = "registrationDto") RegistrationDto registrationDto) {
        this.registrationDto = registrationDto;
        LOGGER.warn("REGISTRATIE PARTICULIER (POST): " + registrationDto.toString());
        return "pages/confirmation-private";
    }

    @PostMapping("/bevestiging-zakelijk")
    public String registrationPostHandlerLegal(@ModelAttribute(name = "registrationDto") RegistrationDto registrationDto) {
        this.registrationDto = registrationDto;
        LOGGER.warn("REGISTRATIE ZAKELIJK (POST): " + registrationDto.toString());
        return "pages/confirmation-legal";
    }

    @PostMapping("/bevestiging")
    public String confirmationHandler(RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("accountCreated", "true");
        LOGGER.warn("BEVESTIGING (POST): " + registrationDto.toString());
        return "redirect:/";
    }

}
