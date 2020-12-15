package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.data.dto.CustomerAccountDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
    public String handleLogin(@RequestParam String userName, String password, Model model) {

        CustomerAccountDTO c = customerService.getCustomerByUsernameAndPassword(userName, password);
        if (c != null) {
            List<Account> accountList = c.getAccountList();

            for (Account a : accountList) {
                CustomerAccountDTO b = customerService.getCustomersByAccount(a.getAccountID());
                model.addAttribute("accountWithCustomerList", b);

                System.out.println(b.getCustomerList());

                // lijstje klaarzetten voor namen van customers:
                List<String> customerNameList = new ArrayList<>();

                // voor alle klanten uit het lijstje:
                for (Customer klant : b.getCustomerList()) {
                    // maak een String van de customerName met printNameCustomer methode uit CustomerService:
                    String customerName = customerService.printNameCustomer(klant.getCustomerID());
                    // voeg deze toe aan het lijstje met customer-namen:
                    customerNameList.add(customerName);
                }
                // lijstje met namen toevoegen aan Model:
                model.addAttribute("customerNameList", customerNameList);
            }
            model.addAttribute("customerWithAccountOverview", c);
            return "pages/rekeningoverzicht";
        }
        return "pages/foutpagina";
    }
}
