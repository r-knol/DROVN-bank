package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.data.dto.AccountTransactionDTO;
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

import java.util.List;
import java.util.Optional;

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
    @GetMapping("/account-overview/{id}") // http://localhost:8080/rekeningoverzicht
    public String accountOverviewHandler(@ModelAttribute Account account,
                                         @PathVariable ("id") long accountID, Model model) {
        System.out.println(account.getAccountID());
        Optional <Account> acc = accountService.getAccountById(accountID);
        model.addAttribute(acc);
        System.out.println(acc);


        // search customer info by customer id:
//        NaturalPerson np = customerService.getNpByCustomerId(customerId);


        // search accounts by customer id:
//        List<Account> accountList = accountService.getAccountsByCustomerId(customerId);
//        model.addAttribute("allAccountsList", accountList);

//        logger.info("De rekeningen van klantID " + customerId + " worden getoond.");

        return "/account-overview";
    }

//    @RequestMapping(value = "/account-overview/{a.iban}", method = RequestMethod.GET)
//    public Optional<Account> getAccount (@PathVariable("a.iban") String iban, Model model) {
//        accountService.getAccountByIban(iban);
//        System.out.println(iban);
//        return accountService.getAccountByIban(iban);
//    }

    @PostMapping("/account-overview/{id}")
    public String PostHandlerAccountDetails (@PathVariable ("id") long accountID, Model model) {
        Optional <Account> acc = accountService.getAccountById(accountID);
        if (acc.isPresent()) {
            Account accountFound = acc.get();
            model.addAttribute("account", accountFound);
            AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO(accountFound);
//            ccountTransactionDTO.setTransactionList();
            model.addAttribute("accountWithTransactions", accountTransactionDTO);
            return "pages/account_details";
        }
        return "pages/open-account";
    }


}
