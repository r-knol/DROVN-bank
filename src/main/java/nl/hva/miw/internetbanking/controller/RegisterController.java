package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    private Logger logger = LoggerFactory.getLogger(RegisterController.class);
    private CustomerService customerService;

    @Autowired
    public RegisterController(CustomerService customerService) {
        this.customerService = customerService;
        logger.warn("New RegisterController.");
    }

    @GetMapping("/registreer_klant")
    public String showSignUpFormCustomer(Model model) { // Model nodig om nieuw Customer object mee te sturen
        Customer c = new Customer();
        model.addAttribute("customer", c);
        return "pages/register_customer";
    }

    @GetMapping("/registreerParticulier")
    public String showSignUpFormNatural(Model model) { // Model nodig om nieuw Customer object mee te sturen
        NaturalPerson np = new NaturalPerson();
        model.addAttribute("naturalperson", np);
        return "pages/register_private";
    }

    @GetMapping("/registreerZakelijk")
    public String showSignUpFormLegal(Model model) { // Model nodig om nieuw Customer object mee te sturen
        LegalPerson lp = new LegalPerson();
        model.addAttribute("legalperson", lp);
        return "pages/register_legal";
    }

    @PostMapping("/registreer_particulier")
    public String processRegistrationPrivate(NaturalPerson naturalPerson) {
        customerService.saveNaturalPerson(naturalPerson);
        return "pages/confirmation_page";
    }


}
