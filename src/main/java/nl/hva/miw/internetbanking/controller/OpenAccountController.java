package nl.hva.miw.internetbanking.controller;


import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OpenAccountController {

    private AccountService accountService;
    private CustomerService customerService;
    private Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);

    @Autowired
    public OpenAccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
        logger.warn("New OpenAccountController.");
    }

    @GetMapping("/rekeningOpenen")
    public String rekeningOpenen(Model model) {
        Account account = new Account();
        model.addAttribute("rekening", account);
        return "rekeningOpenen";
    }

    //@Author Veroniek methode toevoegen die IBAN genereert, in rekeningOpenen.html toont en daarna opslaan in DB met customerID

}
