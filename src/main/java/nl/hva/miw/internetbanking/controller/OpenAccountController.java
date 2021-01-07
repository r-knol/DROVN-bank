package nl.hva.miw.internetbanking.controller;


import nl.hva.miw.internetbanking.data.dto.OpenAccountDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

   @GetMapping("/openaccount/{id}")
    public ModelAndView showNewAccount(@PathVariable(value = "id") long id, ModelAndView model){
        Optional<Customer> customer = customerService.getCustomerById(id);
        Account newAccount = new Account();
        model.addObject("account", newAccount);
        model.addObject("customer", customer.get());
        model.setViewName("pages/open-account");
       logger.info(" " + model);
       return model;
    }

    @PostMapping("/saveaccount")
    public ModelAndView saveAccount (@ModelAttribute ("account") Account account, @ModelAttribute ("customer") Customer customer){
        logger.info(" Dit is huidige: " + account + customer);
        accountService.saveNewAccount(account, customer);
        return new ModelAndView("pages/account-overview");
    }
}
