package nl.hva.miw.internetbanking.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dto.AccountTransactionDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@Slf4j(topic="accountDetailsOverview")
public class AccountDetailsController {

    private AccountService accountService;


    @Autowired
    public AccountDetailsController (AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(value = "/account_details")
    public String AccountDetailsHandler (@ModelAttribute Account account, Model model) {
        Optional<Account> a = accountService.getAccountById(account.getAccountID());
        if (a.isPresent()) {
            Account accountFound = a.get();
            model.addAttribute(accountFound);
            model.addAttribute("a.iban", accountFound.getIban());
            AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
            accountService.setAccountTransactionDTO(accountTransactionDTO);
            model.addAttribute("accountWithTransactions", accountTransactionDTO);
            log.info("Model has account:\n{}", model.getAttribute("account"));
            return "pages/account_details";
        }
        return "pages/foutpagina";
    }

}
