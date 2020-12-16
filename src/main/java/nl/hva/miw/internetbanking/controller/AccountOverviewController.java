package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// aangemaakt door Nina 09-12-2020

@Controller
public class AccountOverviewController {

    private AccountService accountService;
    private CustomerService customerService;
    private Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);

    @Autowired
    public AccountOverviewController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
        logger.warn("New AccountOverviewController.");
    }

    // Onderstaand als een customerId beschikbaar is:
    @GetMapping("/rekeningoverzicht") // http://localhost:8080/rekeningoverzicht
    public String accountOverviewHandler(Model model) {

        // search customer info by customer id:
//        NaturalPerson np = customerService.getNpByCustomerId(customerId);


        // search accounts by customer id:
//        List<Account> accountList = accountService.getAccountsByCustomerId(customerId);
//        model.addAttribute("allAccountsList", accountList);

//        logger.info("De rekeningen van klantID " + customerId + " worden getoond.");

        return "account-overview";
    }
}
