package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.CustomerType;
import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import nl.hva.miw.internetbanking.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountDetailsController.class)
public class AccountDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private AccountService accountService;
    @MockBean
    private TransactionService transactionService;

    @Test
    void returnToAccountOverviewTest() throws Exception {
        MockHttpSession session = new MockHttpSession();
        Account account = new Account(1L, 0, "NL77DRVN0458745121");
        Customer customer = new Customer(1L, "piet", "piet", CustomerType.NATURAL);
        Mockito.when(customerService.printNameCustomer(1)).thenReturn("Piet janssen");
        Mockito.when(accountService.getAccountsForCustomer(customer)).thenReturn(List.of(account));
        Mockito.when(customerService.getCustomerByAccountId(1)).thenReturn(List.of(customer));
        session.setAttribute("customer", customer);
        session.setAttribute("customerWithAccountOverview", accountService.getAccountsForCustomer(customer));
        try {
            MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/account_details/").session(session);
            ResultActions response = mockMvc.perform(postRequest);
            response.andDo(print()).andExpect(status().isOk());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }

}
