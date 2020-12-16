package nl.hva.miw.internetbanking.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@Slf4j
public class AccountDetailsController {

    private AccountService accountService;

    @Autowired
    public AccountDetailsController (AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accountDetails")
    public String AccountDetailsHandler (Model model) {
        model.addAttribute("acountTransactionDTO", accountService.getAccountTransactionDTO());
        return "pages/accountDetails";
    }

    public String accountDetailsOverview(@ModelAttribute("iban") String iban) {

    }


}
