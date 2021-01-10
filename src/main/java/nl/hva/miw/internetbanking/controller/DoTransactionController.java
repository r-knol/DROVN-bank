package nl.hva.miw.internetbanking.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dto.CustomerHasAccountsDTO;
import nl.hva.miw.internetbanking.data.dto.TransactionDetailsDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.Transaction;
import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import nl.hva.miw.internetbanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@Slf4j
@SessionAttributes("customer") // hier staat welke attributen deze controllers doorgeven aan een volgende controller
public class DoTransactionController {

    private TransactionService transactionService;
    private CustomerService customerService;
    private AccountService accountService;

    @Autowired
    public DoTransactionController(TransactionService transactionService, CustomerService customerService, AccountService accountService) {
        this.transactionService = transactionService;
        this.customerService = customerService;
        this.accountService = accountService;
    }

    @GetMapping("/do-transaction/")
    // hieronder geef je de session attributes door die je krijgt van de vorige controller
    public String doTransactionHandler(@ModelAttribute("customer") Customer c, @ModelAttribute("nameCurrentCus") String currentCusName, Model model){
        CustomerHasAccountsDTO customerDto = new CustomerHasAccountsDTO(c);
        customerDto.setAccountList(accountService.getAccountsForCustomer(c));

        // voor alle accounts de bijbehorende customers ophalen:
        for (Account acc : customerDto.getAccountList()) {
            acc.setAccountHolders(customerService.getCustomerByAccountId(acc.getAccountID()));
            // voor iedere accountHolder de juiste naam ophalen:
            for (Customer cus : acc.getAccountHolders()) {
                acc.addAccountHolderName(customerService.printNameCustomer(cus.getCustomerID()));
            }
        }
        model.addAttribute("nameCurrentCus", currentCusName);
        model.addAttribute("customerWithAccountOverview", customerDto);
        model.addAttribute("transactionDTO", new TransactionDetailsDTO());
        return "pages/do-transaction";
    }

    @PostMapping("/do-transaction/")
    public String postDoTransactionHandler(@ModelAttribute("transactionDTO") TransactionDetailsDTO tDto, Model model){
        model.addAttribute("transactionDTO", tDto);
        System.out.println("!!!!!!!!!!!!!!!!! " + tDto);
        return "pages/transaction-confirmation";
    }



}
