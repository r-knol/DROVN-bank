package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@SessionAttributes("customer")
public class OpenAccountRestController {

    @Autowired
    private AccountService accountService;
    private CustomerService customerService;
    private Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);

    @Autowired
    public OpenAccountRestController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

        @PostMapping("/openaccount/check_iban")
        public String checkDuplicateIban(@Param("accountID") long id, @Param("ibanGenerator") String iban){
            System.out.println(id);
            System.out.println(iban);

            if (accountService.uniqueIbanViolated(id, iban)){
                return "Duplicate";
            }else {
                return "OK";
            }
        }
}
