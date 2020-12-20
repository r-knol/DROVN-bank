package nl.hva.miw.internetbanking.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dto.AccountHasTransactionDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Transaction;
import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@Slf4j(topic="accountDetailsOverview")
public class AccountDetailsController {

    private AccountService accountService;
    private TransactionService transactionService;

    @Autowired
    public AccountDetailsController (AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/account_details/{id}")
    public String AccountDetailsHandler (@PathVariable ("id") long accountID, Model model) {
        Optional<Account> account = accountService.getAccountById(accountID);
        Account accountFound = account.get();
        System.out.println(account);
        AccountHasTransactionDTO accountHasTransactionDTO = new AccountHasTransactionDTO(accountFound);
        accountHasTransactionDTO.setTransactionList(transactionService.getTransactionsForAccount(accountFound));
        for (Transaction transaction : accountHasTransactionDTO.getTransactionList()) {
           transaction.addTransactionToAccount(accountFound);
        }
        model.addAttribute("accountWithTransactionOverview", accountHasTransactionDTO);
        return "pages/account_details";
    }
}
