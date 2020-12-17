package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.data.dto.CustomerHasAccountsDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.Employee;
import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import nl.hva.miw.internetbanking.service.EmployeeService;
import nl.hva.miw.internetbanking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Controller
@SessionAttributes("customer")
public class LoginController {

    private CustomerService customerService;
    private LoginService loginService;
    private AccountService accountService;
    private EmployeeService employeeService;

    @Autowired
    public LoginController(CustomerService customerService, LoginService loginService, AccountService accountService,
                           EmployeeService employeeService) {
        this.customerService = customerService;
        this.loginService = loginService;
        this.accountService = accountService;
        this.employeeService = employeeService;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "pages/login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String userName, @RequestParam String password, Model model) {
        Optional<Customer> customer = customerService.getCustomerByUsername(userName);

        if (customer.isPresent()) {
            Customer customerFound = customer.get();
            if (loginService.validCustomer(customerFound, password)) {
                model.addAttribute("customer", customerFound);
                CustomerHasAccountsDTO customerDto = new CustomerHasAccountsDTO(customerFound);
                customerDto.setAccountList(accountService.getAccountsForCustomer(customerFound));

                // voor alle accounts de bijbehorende customers ophalen:
                for (Account acc : customerDto.getAccountList()) {
                    acc.setAccountHolders(customerService.getCustomerByAccountId(acc.getAccountID()));
                    // acc.addAccountHolderName(customerService.printNameCustomer(customerFound.getCustomerID()));
                }
                model.addAttribute("customerWithAccountOverview", customerDto);

                // controle op accountholders van Account op plek 0:
                System.out.println(customerDto.getAccountList().get(0).getAccountHolders());


                // TODO:
                // Nog een if statement voor het geval dat het een employee is, andere modelAttribute meegeven
                // model.addAttribute(employeeDashboard, ...)
                // return "pages/employee-dashboard"
//                Optional<Employee> employee = employeeService.getEmployeeByUsername(userName);
//                if (employee.isPresent()) {
//                    Employee employeeFound = employee.get();
//                    model.addAttribute("employee", employeeFound);
//                }

                return "pages/account-overview";
            }

        }
        return "pages/foutpagina";
    }
}
