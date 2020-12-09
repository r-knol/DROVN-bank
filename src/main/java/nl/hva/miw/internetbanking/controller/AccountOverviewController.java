package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AccountOverviewController {
    private List<Account> accountList;

    // private AccountService accountService;
    private Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);

    @Autowired
    public AccountOverviewController() {
        // this.accountService = accountService;
        logger.warn("New AccountOverviewController.");
    }

    @GetMapping("/rekeningoverzicht") // http://localhost:8080/rekeningoverzicht
    public String accountOverviewHandler() {
//        if (!((Long) customerId).equals(null)) {
//            model.addAttribute("all_accounts", accountList);
//            logger.info("De rekeningen van klantID " + customerId + " worden getoond.");
//        }
        return "pages/rekeningoverzicht";
    }

    // Onderstaand als een customerId beschikbaar is:
//    @GetMapping("/rekeningoverzicht") // http://localhost:8080/rekeningoverzicht
//    public String accountOverviewHandler(@RequestParam(name="customerId") long customerId, Model model) {
//        if (!((Long) customerId).equals(null)) {
//            model.addAttribute("all_accounts", accountList);
//            logger.info("De rekeningen van klantID " + customerId + " worden getoond.");
//        }
//        return "pages/rekeningoverzicht";
//    }


}
