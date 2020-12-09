//package nl.hva.miw.internetbanking.controller;
//
//import nl.hva.miw.internetbanking.model.Customer;
//import nl.hva.miw.internetbanking.model.LegalPerson;
//import nl.hva.miw.internetbanking.model.NaturalPerson;
//import nl.hva.miw.internetbanking.service.CustomerService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class RegisterController {
//
//    private Logger logger = LoggerFactory.getLogger(RegisterController.class);
//    private CustomerService customerService;
//
//    @Autowired
//    public RegisterController(CustomerService customerService) {
//        this.customerService = customerService;
//        logger.warn("New RegisterController.");
//    }
//
//    //-----------------------------------------// particulier
//    @GetMapping("/registreerparticulier")
//    public String showSignUpFormNatural(Model model) { // Model nodig om nieuw Customer object mee te sturen
//        NaturalPerson np = new NaturalPerson();
//        model.addAttribute("naturalperson", np);
//        return "pages/register_private";
//    }
//
//    @PostMapping("/registreerparticulier")
//    public String saveRegistrationPrivate(@ModelAttribute("naturalperson") NaturalPerson naturalPerson) {
//        customerService.saveNaturalPerson(naturalPerson);
//        return "pages/confirmation_page";
//    }
//
//    //-----------------------------------------// zakelijk
//    @GetMapping("/registreerzakelijk")
//    public String showSignUpFormLegal(Model model) { // Model nodig om nieuw Customer object mee te sturen
//        LegalPerson lp = new LegalPerson();
//        model.addAttribute("legalperson", lp);
//        return "pages/confirmation_page";
//    }
//
//    @PostMapping("/registreerzakelijk")
//    public String saveRegistrationLegal(@ModelAttribute("legalperson") LegalPerson legalPerson) {
//        customerService.saveLegalPerson(legalPerson);
//        return "pages/confirmation_page";
//    }
//
//
//
//
//}
