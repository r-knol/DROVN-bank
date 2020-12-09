package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private CustomerService customerService;

    @Autowired
    public LoginController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "pages/login";
    }

    @PostMapping("/login") // Controller praat alleen tegen service
    public String handleLogin(@RequestParam String userName, String password) { // Gaat iets doen met ingevoerde G en W
        if (customerService.valideerLogin(userName, password)) { // Roept service op
            return "pages/rekeningoverzicht";
        } else {
            return "pages/foutpagina";
        }
    }

}
