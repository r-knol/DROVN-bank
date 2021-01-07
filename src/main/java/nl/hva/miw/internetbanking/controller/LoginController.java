package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.data.dto.CustomerHasAccountsDTO;
import nl.hva.miw.internetbanking.model.*;
import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import nl.hva.miw.internetbanking.service.EmployeeService;
import nl.hva.miw.internetbanking.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
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
    public String handleLogin(@RequestParam(name = "userName") String userName, @RequestParam(name = "password")
            String password, Model model) {

        Optional<Customer> customer = customerService.getCustomerByUsername(userName);
        System.out.println(customer);
        if (customer.isPresent()) {
            Customer customerFound = customer.get();
            if (loginService.validCustomer(customerFound, password)) {
                model.addAttribute("customer", customerFound);
                model.addAttribute("nameCurrentCus", customerService.printNameCustomer(customerFound.getCustomerID()));
                CustomerHasAccountsDTO customerDto = new CustomerHasAccountsDTO(customerFound);
                customerDto.setAccountList(accountService.getAccountsForCustomer(customerFound));

                // voor alle accounts de bijbehorende customers ophalen:
                for (Account acc : customerDto.getAccountList()) {
                    acc.setAccountHolders(customerService.getCustomerByAccountId(acc.getAccountID()));
                    // voor iedere accountHolder de juiste naam ophalen:
                    for (Customer c : acc.getAccountHolders()) {
                        acc.addAccountHolderName(customerService.printNameCustomer(c.getCustomerID()));
                    }
                }
                model.addAttribute("customerWithAccountOverview", customerDto);
                return "pages/account-overview";
            }
        }
        return "pages/errorpage";
    }

    @GetMapping("/customer-with-accounts")
    public String showCustomerWithAccounts(@ModelAttribute("customer") Customer c, Model model){
        System.out.println("bla bla" + c);
        // service aanroepen.
        model.addAttribute("nameCurrentCus", customerService.printNameCustomer(c.getCustomerID()));
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
        return "pages/account-overview";
    }



    @GetMapping("/loginemployee")
    public String showLoginEmployee() {
        return "pages/login-employee";
    }

    @PostMapping("/loginemployee")
    public String handleLoginEmployee(@RequestParam(name = "userName") String userName, @RequestParam(name = "password")
            String password, Model model) {

        Optional<Employee> employee = employeeService.getEmployeeByUsername(userName);

        if (employee.isPresent()) {
            Employee employeeFound = employee.get();
            if (loginService.validEmployee(employeeFound, password)) {
                model.addAttribute("employee", employeeFound);

                if (employeeFound.getEmployeeRole() == EmployeeRole.HEAD_PRIVATE) {

                    List<Account> accountList = accountService.getAllNaturalAccounts();
                    model.addAttribute("naturalAccounts", accountList);

                    for (Account account : accountList) {
                        account.setAccountHolders(customerService.getCustomerByAccountId(account.getAccountID()));
                        for (Customer customer : account.getAccountHolders()) {
                            account.addAccountHolderName(customerService.printNameCustomer(customer.getCustomerID()));
                        }
                    }
                    return "pages/employee-dashboard-private";
                }

                if (employeeFound.getEmployeeRole() == EmployeeRole.HEAD_LEGAL) {

                    List<Account> accountList = accountService.getAllLegalAccounts();
                    model.addAttribute("legalAccounts", accountList);
                    for (Account account : accountList) {
                        account.setAccountHolders(customerService.getCustomerByAccountId(account.getAccountID()));
                        for (Customer customer : account.getAccountHolders()) {
                            account.addAccountHolderName(customerService.printNameCustomer(customer.getCustomerID()));
                        }
                    }
                    List<LegalPerson> legalPersons = customerService.getAvgBalancePerSegment();
                    model.addAttribute("legalPersons", legalPersons);
                    System.out.println(legalPersons);

                    // todo: een balance oproepen behorende bij de sql query. Hoe combineer ik die?

                }
                return "pages/employee-dashboard-legal";
            }
        }
        return "pages/errorpage-employee";
    }
}