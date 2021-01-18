package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.Employee;
import nl.hva.miw.internetbanking.model.EmployeeRole;
import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import nl.hva.miw.internetbanking.service.EmployeeService;
import nl.hva.miw.internetbanking.service.LoginService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static nl.hva.miw.internetbanking.model.CustomerType.NATURAL;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(LoginController.class)
class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private LoginService loginService;
    @MockBean
    private AccountService accountService;
    @MockBean
    private EmployeeService employeeService;

    public LoginControllerTest() {
        super();
    }

    Customer nina = new Customer(1, "nvanloo", "myPassword", NATURAL);
    List<Account> accountsNina = List.of(
            new Account(1, 99.95, "84NL0563171251"),
            new Account(2, 1234.56, "56NL0563172222"));

    Customer richard = new Customer(1, "rknol", "knolPassword", NATURAL);
    List<Customer> customerList = List.of(nina, richard);

    Employee pieter = new Employee(1, "pdeboer", "test",
            "Pieter", "de", "Boer", EmployeeRole.HEAD_LEGAL);

    @Test
    void handleLoginTest() {
        Mockito.when(customerService.getCustomerByUsername("nvanloo")).thenReturn(Optional
                .of(nina));
        Mockito.when(loginService.validCustomer(nina, "myPassword")).thenReturn(true);
        Mockito.when(customerService.printNameCustomer(1)).thenReturn("Nina van Loo");
        Mockito.when(customerService.printNameCustomer(2)).thenReturn("Richard Knol");
        Mockito.when(accountService.getAccountsForCustomer(nina)).thenReturn(accountsNina);
        Mockito.when(customerService.getCustomerByAccountId(1)).thenReturn(customerList);

        try {
            MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/login")
                    .param("userName", "nvanloo")
                    .param("password", "myPassword");
            ResultActions response = mockMvc.perform(postRequest);
            response
                    .andDo(print())
                    .andExpect(status().isOk());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void handleLoginEmployee() {
        Mockito.when(employeeService.getEmployeeByUsername("pdeboer"))
                .thenReturn(Optional.of(pieter));
        Mockito.when(loginService.validEmployee(pieter, "test")).thenReturn(true);

        try {
            MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/loginemployee")
                    .param("userName", pieter.getUserName())
                    .param("password", pieter.getPassword());
            ResultActions response = mockMvc.perform(post);
            response
                    .andDo(print())
                    .andExpect(view().name("pages/employee-dashboard-legal"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}