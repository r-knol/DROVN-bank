package nl.hva.miw.internetbanking.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dto.CustomerHasAccountsDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
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
@SessionAttributes("customer")
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
    public String doTransactionHandler(@ModelAttribute("customer") Customer c, Model model){
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
        model.addAttribute("customerWithAccountOverview", customerDto);
        System.out.println("!!!!!!!!!!!!!! " + c);

        return "pages/do-transaction";
    }



}
