package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@SessionAttributes("customer")
@Validated
public class OpenAccountRestController {

    @Value("key_drovn")
    private String apikey;

    @Autowired
    private AccountService accountService;
    private CustomerService customerService;
    private Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);

    @Autowired
    public OpenAccountRestController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }
        @CrossOrigin
        @PostMapping("openaccount/check_iban")
        public String checkDuplicateIban(@RequestParam(name = "iban", required = true) String iban, @RequestHeader("Authorization") String authValue){
            logger.info("check_iban " + iban);
            logger.info("request auth header" + authValue);
            logger.info("Key is" + apikey);
            if (accountService.uniqueIbanViolated(iban)){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Iban bestaat al");
            }else {
                logger.info("iban is nieuw" + iban);
                return "pages/saveaccount";
            }
        }
}
