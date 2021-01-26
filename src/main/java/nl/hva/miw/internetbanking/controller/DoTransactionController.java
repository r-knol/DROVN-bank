package nl.hva.miw.internetbanking.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dto.CustomerHasAccountsDTO;
import nl.hva.miw.internetbanking.data.dto.TransactionDetailsDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import nl.hva.miw.internetbanking.model.Transaction;
import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import nl.hva.miw.internetbanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@SessionAttributes({"customer", "nameCurrentCus", "transactionDTO"})
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
    public String doTransactionHandler(@ModelAttribute("customer") Customer c, @ModelAttribute("nameCurrentCus") String currentCusName, @ModelAttribute("transactionDTO") TransactionDetailsDTO tDto, Model model){
        CustomerHasAccountsDTO customerDto = new CustomerHasAccountsDTO(c);
        customerDto.setAccountList(accountService.getAccountsForCustomer(c));
        customerService.setCustomerWithAccounts(customerDto);
        model.addAttribute("nameCurrentCus", currentCusName);
        model.addAttribute("customerWithAccountOverview", customerDto);
        model.addAttribute("transactionDTO", tDto);
        System.out.println(tDto);
        return "pages/do-transaction";
    }

    @ModelAttribute("transactionDTO")
    public TransactionDetailsDTO getTransactionDetailsDTO(){
        return new TransactionDetailsDTO();
    }

    @PostMapping("/do-transaction/")
    public String postDoTransactionHandler(@ModelAttribute("customer") Customer c, @ModelAttribute("customerWithAccountOverview") CustomerHasAccountsDTO customerDto, @ModelAttribute("transactionDTO") TransactionDetailsDTO tDto, @ModelAttribute("nameCurrentCus") String currentCusName, Model model){
        model.addAttribute("nameCurrentCus", currentCusName);
        model.addAttribute("transactionDTO", tDto);
        if (transactionService.checkValidTransaction(tDto)) {
            customerDto.setAccountList(accountService.getAccountsForCustomer(c));
            customerService.setCustomerWithAccounts(customerDto);
            model.addAttribute("customerWithAccountOverview", customerDto);
            return "pages/transaction-confirmation";
        } else {
            return "pages/errorpage-transaction";
        }
    }

    @PostMapping("/transaction-confirmed/")
    public String transactionConfirmationHandler(@ModelAttribute("customer") Customer c, @ModelAttribute("customerWithAccountOverview") CustomerHasAccountsDTO customerDto, @ModelAttribute("transactionConfirmedDTO") TransactionDetailsDTO tDto, Model model){
        tDto.setAmount(transactionService.transferToDouble(tDto.getAmount()));
        transactionService.doTransaction(tDto, Transaction.class);
        customerDto.setAccountList(accountService.getAccountsForCustomer(c));
        customerService.setCustomerWithAccounts(customerDto);
        model.addAttribute("customerWithAccountOverview", customerDto);
        model.addAttribute("transactionDTO", new TransactionDetailsDTO());
        model.addAttribute("customer", c);
        model.addAttribute("transactionConfirmed", "true");
        return "pages/account-overview";
    }

    @CrossOrigin
    @PostMapping("/iban")
    @ResponseBody
    public String checkExistingIban(@RequestParam String iban){
        System.out.println("Request data in: " + iban);
        String ibanFound = accountService.getAccountByIban(iban).get().getIban();
        if (iban.equals(ibanFound)) {
            return ibanFound;
        }
        return null;
    }

    @CrossOrigin
    @PostMapping("/getnames")
    @ResponseBody
    public List<String> loadCustomerNames(@RequestParam String iban, @ModelAttribute("customer") Customer c){
        CustomerHasAccountsDTO cusAcc = new CustomerHasAccountsDTO(c);
        cusAcc.setAccountList(accountService.getAccountsForCustomer(c));
        customerService.setCustomerWithAccounts(cusAcc);
        return customerService.accountHolderNamesList(cusAcc, iban);
    }



}
