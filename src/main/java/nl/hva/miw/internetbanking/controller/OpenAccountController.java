package nl.hva.miw.internetbanking.controller;


import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

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

    @GetMapping("/openaccount")
    public String openNewAccountHandler(Model model){
        model.addAttribute("customer", new Customer());
        return "pages/open-account";
    }

    @PostMapping("/open-account/{id}")
    public String openAccountHandler(@PathVariable ("id") long customerID, Model model) {
        Optional<Customer> customer = customerService.getCustomerById(customerID);
        model.addAttribute("customer", customer);
        return "pages/open-account";
    }

}
