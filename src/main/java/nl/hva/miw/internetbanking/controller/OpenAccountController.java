package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@SessionAttributes("customer")
public class OpenAccountController {

    private AccountService accountService;
    private CustomerService customerService;
    private Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);

    @Autowired
    public OpenAccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

   @GetMapping("/openaccount")
    public String showNewAccount(@ModelAttribute("customer") Customer customer,  Model model){
        Account newAccount = new Account();
        model.addAttribute("account", newAccount);
        model.addAttribute("nameCurrentCus", customerService.printNameCustomer(customer.getCustomerID()));
        model.addAttribute("customer", customer);
       logger.info(" openaccount " + model);
       return "pages/open-account";
    }

    @PostMapping("/saveaccount")
    public String saveAccount (@ModelAttribute ("account") Account account, @ModelAttribute ("customer") Customer customer){
        logger.info(" Dit is huidige: " + account + customer);
        accountService.saveNewAccount(account, customer);
        return "pages/open-account";
    }

    @CrossOrigin
    @PostMapping("/check_iban")
    public @ResponseBody String ibanCheckDB(@RequestParam String iban){
        System.out.println("Request data in: " + iban);
        String ibanFound = accountService.getAccountByIban(iban).get().getIban();
        if (iban.equals(ibanFound)) {
            return ibanFound;
        }
        return null;
    }
}
