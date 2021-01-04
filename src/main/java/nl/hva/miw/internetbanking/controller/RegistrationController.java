package nl.hva.miw.internetbanking.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dto.BusinessRegistrationDTO;
import nl.hva.miw.internetbanking.data.dto.PrivateRegistrationDTO;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j(topic = "CustomerRegistration")
public class RegistrationController {

    private final CustomerService customerService;

    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/registratie")
    public String registrationGetHandler(Model model) {
        model.addAttribute("privateRegistrationDto", new PrivateRegistrationDTO());
        model.addAttribute("businessRegistrationDto", new BusinessRegistrationDTO());
        return "pages/registration";
    }

    @PostMapping("/registratie-particulier")
    public String registrationPostHandlerPrivate(
            @ModelAttribute("privateRegistrationDto") PrivateRegistrationDTO dto, Model model) {
        model.addAttribute("privateRegistrationDto", dto);
        return "pages/confirmation-private";
    }

    @PostMapping("/bevestiging-particulier")
    public String confirmationPostHandlerPrivate(
            @ModelAttribute("privateRegistrationDto") PrivateRegistrationDTO dto, Model model,
            RedirectAttributes redirectAttributes) {
        customerService.registerCustomer(dto, NaturalPerson.class);
        redirectAttributes.addFlashAttribute("accountCreated", "true");
        return "redirect:/";
    }

    @PostMapping("/registratie-zakelijk")
    public String registrationPostHandlerBusiness(
            @ModelAttribute("businessRegistrationDto") BusinessRegistrationDTO dto, Model model) {
        model.addAttribute("businessRegistrationDto", dto);
        return "pages/confirmation-business";
    }

    @PostMapping("/bevestiging-zakelijk")
    public String confirmationPostHandlerBusiness(
            @ModelAttribute("businessRegistrationDto") BusinessRegistrationDTO dto, Model model,
            RedirectAttributes redirectAttributes) {
        customerService.registerCustomer(dto, LegalPerson.class);
        redirectAttributes.addFlashAttribute("accountCreated", "true");
        return "redirect:/";
    }

}
