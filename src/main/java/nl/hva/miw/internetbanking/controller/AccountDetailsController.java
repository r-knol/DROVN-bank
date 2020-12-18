package nl.hva.miw.internetbanking.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dto.AccountTransactionDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@Slf4j(topic="accountDetailsOverview")
public class AccountDetailsController {

    private AccountService accountService;


    @Autowired
    public AccountDetailsController (AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account_details/{id}")
    public String AccountDetailsHandler (@PathVariable ("id") long accountID, Model model) {
        Optional<Account> a = accountService.getAccountById(accountID);
        System.out.println(a);
        if (a.isPresent()) {
            Account accountFound = a.get();
            model.addAttribute("account",accountFound);
            model.addAttribute("iban", accountFound.getIban());
            model.addAttribute("balance", accountFound.getBalance());
            AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
            accountService.setAccountTransactionDTO(accountTransactionDTO);
            model.addAttribute("accountWithTransactions", accountTransactionDTO);
            log.info("Model has account:\n{}", model.getAttribute("account"));
            return "pages/account_details";
        }
        return "errorpage";
    }



}
