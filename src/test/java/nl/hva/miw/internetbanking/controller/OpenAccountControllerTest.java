package nl.hva.miw.internetbanking.controller;

import com.couchbase.client.core.deps.io.netty.handler.codec.http.HttpExpectationFailedEvent;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.CustomerType;
import nl.hva.miw.internetbanking.service.AccountService;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.channels.ScatteringByteChannel;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(OpenAccountController.class)
class OpenAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerService customerService;
    @MockBean
    private AccountService accountService;


    public OpenAccountControllerTest() {
        super();
    }


    @Test
    void showNewAccount() {
        MockHttpSession session = new MockHttpSession();
        Customer customer1 = new Customer("rknol", "rknol", CustomerType.NATURAL);
        session.setAttribute("customer", customer1);
        MockHttpServletRequestBuilder getRequest = MockMvcRequestBuilders.get("/openaccount").session(session);
        try {
            ResultActions response = mockMvc.perform(getRequest);
            response.andDo(print());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void saveAccount() throws Exception {
        MockHttpSession session = new MockHttpSession();
        Account account1 = new Account(1L, 0, "NL23DROVN987639204");
        Customer customer1 = new Customer("rknol", "rknol", CustomerType.NATURAL);
        session.setAttribute("customer", customer1);
        session.setAttribute("account", account1);
        try {
            MockHttpServletRequestBuilder postRequest = MockMvcRequestBuilders.post("/saveaccount").session(session);
            ResultActions response = mockMvc.perform(postRequest);
            response.andDo(print()).andExpect(status().isOk());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
    }
}