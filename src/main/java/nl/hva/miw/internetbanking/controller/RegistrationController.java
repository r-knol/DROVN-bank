package nl.hva.miw.internetbanking.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dto.RegistrationDto;
import nl.hva.miw.internetbanking.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j(topic = "CustomerRegistration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping("/registratie")
    public String registrationGetHandler(Model model) {
        model.addAttribute("registrationDto", registrationService.getRegistrationDto());
        log.info("Model has DTOs:\n{}", model.getAttribute("registrationDto"));
        return "pages/registration";
    }

    @PostMapping("/registratie-particulier")
    public String registrationPostHandlerPrivate(@ModelAttribute("registrationDto") RegistrationDto registrationDto) {
        registrationService.setRegistrationDto(registrationDto);
        log.info("\nRegistration Private Account:\n{}", registrationService.getRegistrationDto());
        return "pages/confirmation-private";
    }

    @PostMapping("/bevestiging-particulier")
    public String confirmationPostHandlerPrivate(RedirectAttributes redirectAttributes) {
        registrationService.registerPrivateCustomer();
        redirectAttributes.addFlashAttribute("accountCreated", "true");
        log.info("Confirmation Private Account:\n{}", registrationService.getRegistrationDto());
        return "redirect:/";
    }

    @PostMapping("/registratie-zakelijk")
    public String registrationPostHandlerLegal(@ModelAttribute(name = "registrationDto") RegistrationDto registrationDto) {
        registrationService.setRegistrationDto(registrationDto);
        log.info("Registration Business Account:\n{}", registrationService.getRegistrationDto());
        return "pages/confirmation-business";
    }

    @PostMapping("/bevestiging-zakelijk")
    public String confirmationPostHandlerLegal(RedirectAttributes redirectAttributes) {
        registrationService.registerBusinessCustomer();
        redirectAttributes.addFlashAttribute("accountCreated", "true");
        log.info("Confirmation Business Account:\n{}", registrationService.getRegistrationDto());
        return "redirect:/";
    }

}
