package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.data.dto.CustomerAccountDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.service.CustomerService;
import nl.hva.miw.internetbanking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("customer")
public class LoginController {

    private CustomerService customerService;
    private LoginService loginService;

    @Autowired
    public LoginController(CustomerService customerService, LoginService loginService) {
      this.customerService = customerService;
      this.loginService = loginService;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "pages/login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String userName, @RequestParam String password, Model model) {
      Customer customer = customerService.getCustomerByName(userName);
      if (customer != null) {
        if (loginService.validCustomer(customer, password)) {
          model.addAttribute("customer", customer);
          return "pages/rekeningoverzicht";
        }
      }
      return "pages/foutpagina";
    }
}
