package nl.hva.miw.internetbanking.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dto.RegistrationDto;
import nl.hva.miw.internetbanking.service.RegistrationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j(topic = "CustomerRegistration")
// Lombok annotation @Slf4j creates a Logger: private static final org.slf4j.Logger log =
// org.slf4j.LoggerFactory.getLogger(RegistrationController.class);
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registratie")
    public String registrationGetHandler(Model model) {
        if (!model.containsAttribute("registrationDto")) {
            model.addAttribute("registrationDto", registrationService.getRegistrationDto());
        }
        log.info("Registration ModelAttribute has DTO:\n{}",
                registrationService.getRegistrationDto());
        return "pages/registration";
    }

    @PostMapping("/registratie-particulier")
    public String registrationPostHandlerPrivate(@ModelAttribute(name = "registrationDto") RegistrationDto registrationDto) {
        registrationService.setRegistrationDto(registrationDto);
        log.info(
                "Registration Private Account:\n{}\n{}",
                registrationService.getRegistrationDto().getCustomer(),
                registrationService.getRegistrationDto().getPersonPrivate());
        return "pages/confirmation-private";
    }

    @PostMapping("/bevestiging-particulier")
    public String confirmationPostHandlerPrivate(RedirectAttributes redirectAttributes) {
        registrationService.registerNaturalPerson();
        redirectAttributes.addFlashAttribute("accountCreated", "true");
        log.info(
                "Confirmation Private Account:\n{}\n{}",
                registrationService.getRegistrationDto().getCustomer(),
                registrationService.getRegistrationDto().getPersonPrivate()
        );
        return "redirect:/";
    }

    @PostMapping("/registratie-zakelijk")
    public String registrationPostHandlerLegal(@ModelAttribute(name = "registrationDto") RegistrationDto registrationDto) {
        registrationService.setRegistrationDto(registrationDto);
        log.info(
                "Registration Business Account:\n{}\n{}",
                registrationDto.getCustomer().toString(),
                registrationDto.getPersonLegal().toString()
        );
        return "pages/confirmation-legal";
    }

    @PostMapping("/bevestiging-zakelijk")
    public String confirmationPostHandlerLegal(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("accountCreated", "true");
        log.info(
                "Confirmation Business Account:\n{}\n{}",
                registrationService.getRegistrationDto().getCustomer(),
                registrationService.getRegistrationDto().getPersonLegal()
        );
        return "redirect:/";
    }

}
