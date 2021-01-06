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

    @GetMapping ("/account_details{id}")
    public String showAccounts (@PathVariable ("id") long accountID, Model model) {
        Optional<Account> account = accountService.getAccountById(accountID);
        model.addAttribute(account);
        System.out.println(account);
        return "pages/details_overview";
    }

    @PostMapping("/account_details/{id}")
    public String AccountDetailsHandler (@PathVariable ("id") long accountID, Model model) {
        Optional<Account> account = accountService.getAccountById(accountID);
        System.out.println(account);
        if (account.isPresent()) {
            Account accountFound = account.get();
            model.addAttribute("account", accountFound);
            accountFound.setTransactions(transactionService.getTransactionsForAccount(accountFound));
            System.out.println(accountFound);
//        AccountHasTransactionDTO accountHasTransactionDTO = new AccountHasTransactionDTO(accountFound);
//        accountHasTransactionDTO.setTransactionList(transactionService.getTransactionsForAccount(accountFound));
//        accountFound.setTransactions(transactionService.getTransactionsForAccount(accountFound));
            System.out.println(accountFound.getTransactions());
            for (Transaction transaction : accountFound.getTransactions()) {
                transaction.addTransactionToAccount(accountFound);
            }
            model.addAttribute("accountWithTransactionOverview", accountFound);
            return "pages/account_details";
        }
        return "pages/errorpage";
    }

}
