package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.data.dto.CustomerHasAccountsDTO;
import nl.hva.miw.internetbanking.data.dto.TransactionDetailsDTO;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.Employee;
import nl.hva.miw.internetbanking.model.EmployeeRole;
import nl.hva.miw.internetbanking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@SessionAttributes({"customer", "nameCurrentCus", "transactionDTO"})
public class LoginController {

    private CustomerService customerService;
    private LoginService loginService;
    private AccountService accountService;
    private EmployeeService employeeService;
    private BalanceService balanceService;
    private TransactionService transactionService;

    @Autowired
    public LoginController(CustomerService customerService, LoginService loginService, AccountService accountService,
                           EmployeeService employeeService, BalanceService balanceService, TransactionService transactionService) {
        this.customerService = customerService;
        this.loginService = loginService;
        this.accountService = accountService;
        this.employeeService = employeeService;
        this.balanceService = balanceService;
        this.transactionService = transactionService;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "pages/login";
    }

    @ModelAttribute("transactionDTO")
    public TransactionDetailsDTO getTransactionDetailsDTO(){
        return new TransactionDetailsDTO();
    }

    @PostMapping("/customer-with-accounts")
    public String handleLogin(@RequestParam(name = "userName") String userName, @RequestParam(name = "password") String password, Model model, @ModelAttribute("transactionDTO") TransactionDetailsDTO tDto) {
        model.addAttribute("transactionDTO", new TransactionDetailsDTO());
        Optional<Customer> customer = customerService.getCustomerByUsername(userName);
        if (customer.isPresent()) {
            Customer customerFound = customer.get();
            if (loginService.validCustomer(customerFound, password)) {
                model.addAttribute("customer", customerFound);
                model.addAttribute("nameCurrentCus", customerService.printNameCustomer(customerFound.getCustomerID()));
                CustomerHasAccountsDTO customerDto = new CustomerHasAccountsDTO(customerFound);
                customerDto.setAccountList(accountService.getAccountsForCustomer(customerFound));
                customerService.setCustomerWithAccounts(customerDto);
                model.addAttribute("customerWithAccountOverview", customerDto);
                return "pages/account-overview";
            }
        }
        return "pages/errorpage";
    }

    @GetMapping("/customer-with-accounts")
    public String showCustomerWithAccounts(@ModelAttribute("customer") Customer c, Model model, @ModelAttribute("transactionDTO") TransactionDetailsDTO tDto) {
        model.addAttribute("transactionDTO", new TransactionDetailsDTO());
        model.addAttribute("nameCurrentCus", customerService.printNameCustomer(c.getCustomerID()));
        CustomerHasAccountsDTO customerDto = new CustomerHasAccountsDTO(c);
        customerDto.setAccountList(accountService.getAccountsForCustomer(c));
        customerService.setCustomerWithAccounts(customerDto);
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
                    model.addAttribute("npWithHighestBalance", balanceService.getNaturalAccountsWithHighestBalance());
                    return "pages/employee-dashboard-private";
                }
                if (employeeFound.getEmployeeRole() == EmployeeRole.HEAD_LEGAL) {
                    model.addAttribute("lpWithHighestBalance", balanceService.getClientsWithHighestBalance());
                    model.addAttribute("balancePerSector", balanceService.getAvgBalancePerSector());
                    model.addAttribute("mostActiveClients", transactionService.getMostActiveClients());
                    return "pages/employee-dashboard-legal";
                }
            }
        }
        return "pages/errorpage-employee";
    }
}