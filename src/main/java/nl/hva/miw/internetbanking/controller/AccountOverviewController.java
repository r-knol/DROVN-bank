package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.data.dto.AccountHasTransactionsDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.Transaction;
import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import nl.hva.miw.internetbanking.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// aangemaakt door Nina 09-12-2020

@Controller
@SessionAttributes({"customer", "nameCurrentCus"})
public class AccountOverviewController {

    private AccountService accountService;
    private CustomerService customerService;
    private TransactionService transactionService;
    private Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);

    @Autowired
    public AccountOverviewController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    // Onderstaand als een customerId beschikbaar is:
    @GetMapping("/account-overview/{id}") // http://localhost:8080/rekeningoverzicht
    public String accountOverviewHandler(@ModelAttribute Account account,
                                         @PathVariable ("id") long accountID, Model model) {
        System.out.println(account.getAccountID());
        Optional <Account> acc = accountService.getAccountById(accountID);
        model.addAttribute(acc);
        System.out.println(acc);
        return "/account-overview";
    }


    @PostMapping("/account-overview/{id}")
    public String PostHandlerAccountDetails (@PathVariable ("id") long accountID, Model model) {
        Optional<Account> account = accountService.getAccountById(accountID);
        model.addAttribute("account", account.get());
        System.out.println(account.get().getTransactions());
        Optional<Customer> customer = customerService.getCustomerByAccountId2(accountID);
        Customer customerFound = customer.get();
        model.addAttribute("customer", customerFound);
        model.addAttribute("nameCurrentCus", customerService.printNameCustomer(customerFound.getCustomerID()));
        return "pages/open-account";
    }
}
