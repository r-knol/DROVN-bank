package nl.hva.miw.internetbanking.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dto.BusinessRegistrationDTO;
import nl.hva.miw.internetbanking.data.dto.PrivateRegistrationDTO;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Slf4j(topic = "CustomerRegistration")
public class RegistrationController {

    private final CustomerService customerService;

    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/registratie")
    public String registrationGetHandler(Model model) {
        model.addAttribute("hasErrors", "false");
        model.addAttribute("privateRegistrationDto", new PrivateRegistrationDTO());
        model.addAttribute("businessRegistrationDto", new BusinessRegistrationDTO());
        return "pages/registration";
    }

    @PostMapping("/registratie-particulier")
    public String registrationPostHandlerPrivate(
            @Valid @ModelAttribute("privateRegistrationDto") PrivateRegistrationDTO dto,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.warn(bindingResult.getAllErrors().toString());
            model.addAttribute("hasErrors", "true");
            return "pages/confirmation-private";
        }
        model.addAttribute("hasErrors", "false");
        model.addAttribute("privateRegistrationDto", dto);
        return "pages/confirmation-private";

    }

    @PostMapping("/bevestiging-particulier")
    public String confirmationPostHandlerPrivate(
            @Valid @ModelAttribute("privateRegistrationDto") PrivateRegistrationDTO dto,
            Model model, RedirectAttributes redirectAttributes) {
        try {
            customerService.registerCustomer(dto, NaturalPerson.class);
            redirectAttributes.addFlashAttribute("accountCreated", "true");
            return "redirect:/";
        } catch (UnexpectedRollbackException e) {
            log.error("Failed to register customer. Rolled back transaction.");
            model.addAttribute("hasErrors", "true");
            return "pages/confirmation-private";
        }
    }

    @PostMapping("/registratie-zakelijk")
    public String registrationPostHandlerBusiness(
            @Valid @ModelAttribute("businessRegistrationDto") BusinessRegistrationDTO dto,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.warn(bindingResult.getAllErrors().toString());
            model.addAttribute("hasErrors", "true");
            return "pages/confirmation-business";
        }
        model.addAttribute("hasErrors", "false");
        model.addAttribute("businessRegistrationDto", dto);
        return "pages/confirmation-business";
    }

    @PostMapping("/bevestiging-zakelijk")
    public String confirmationPostHandlerBusiness(
            @Valid @ModelAttribute("businessRegistrationDto") BusinessRegistrationDTO dto,
            Model model, RedirectAttributes redirectAttributes) {
        try {
            customerService.registerCustomer(dto, LegalPerson.class);
            redirectAttributes.addFlashAttribute("accountCreated", "true");
            return "redirect:/";
        } catch (UnexpectedRollbackException e) {
            log.error("Failed to register customer, transaction rolled back [{} - {}]",
                      e.getClass().getSimpleName(), e.getMessage());
            model.addAttribute("hasErrors", "true");
            return "pages/confirmation-business";
        } catch (Exception e) {
            log.error(e.getClass().getSimpleName(), e.getMessage());
            model.addAttribute("hasErrors", "true");
            return "pages/confirmation-business";
        }
    }

}
