package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.DTO.CustomerAccountDTO;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    private CustomerService customerService;

    @Autowired
    public LoginController(CustomerService customerService) { //@Richard Knol
        this.customerService = customerService;
    }

    @GetMapping("/login") //@Richard Knol
    public String showLogin() {
        return "pages/login";
    }

    @PostMapping("/login") //@Richard Knol
    public String handleLogin(@RequestParam String userName, String password, Model model) { // Gaat iets doen met ingevoerde G en W

        CustomerAccountDTO c = customerService.getCustomerAccountOverview(userName, password);

        if (c != null) {
            model.addAttribute("customerWithAccountOverview", c);
            return "pages/rekeningoverzicht";
        } else {
            return "pages/foutpagina";
        }
    }
}
