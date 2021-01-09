package nl.hva.miw.internetbanking.controller;


import nl.hva.miw.internetbanking.data.dto.OpenAccountDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Controller
@SessionAttributes("customer")
public class OpenAccountController {

    private AccountService accountService;
    private CustomerService customerService;
    private Logger logger = LoggerFactory.getLogger(AccountOverviewController.class);


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OpenAccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
        logger.warn("New OpenAccountController.");
    }

    @GetMapping("/openaccount")
    public String showNewAccount(@ModelAttribute("customer") Customer customer, Model model) {
        Account newAccount = new Account();
        model.addAttribute("account", newAccount);
        model.addAttribute("customer", customer);
        model.addAttribute("nameCurrentCus", customerService.printNameCustomer(customer.getCustomerID()));
        logger.info(" getMapping openaccount" + model);
        return "pages/open-account";
    }

    @PostMapping("/saveaccount")
    public String saveAccount(@ModelAttribute("account") Account account, @ModelAttribute("customer") Customer customer) {
        logger.info(" Dit is huidige: " + account + customer);
        accountService.saveNewAccount(account, customer);
        return "pages/account-overview";
    }

    private Account getAccount(@RequestParam String iban) {
        Account account;
        try {
            account = jdbcTemplate.queryForObject("SELECT iban FROM account iban =?", new ibanMapper(), iban);
        } catch (EmptyResultDataAccessException emptyRS) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Iban niet gevonden", emptyRS);
        }
        return account;
    }
}


class ibanMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Account(rs.getString("iban"));
    }
}
