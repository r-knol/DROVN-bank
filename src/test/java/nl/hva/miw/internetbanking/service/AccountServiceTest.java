package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.data.dao.CustomerDAO;
import nl.hva.miw.internetbanking.data.dao.EmployeeDAO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.CustomerType;
import nl.hva.miw.internetbanking.model.Employee;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {


    Customer customer1 = new Customer(1, "rknol", "rknol", CustomerType.NATURAL);
    Account account = new Account(1, 1000, "NL63DROVN9283736");
    Account account2 = new Account(2, 2000, "NL87DROVN928370");

    CustomerDAO customerDAO = Mockito.mock(CustomerDAO.class);
    AccountDAO accountDAO = Mockito.mock(AccountDAO.class);

    AccountService accountService = new AccountService(customerDAO, accountDAO);


    public AccountServiceTest() {
        super();
    }

    @Test
    public void returnAccountsForCustomer() {
        List<Account> accounts = Arrays.asList(account, account2);
        when(accountDAO.getAccountsByCustomerId(1L)).thenReturn(accounts);
        List<Account> result = accountService.getAccountsByCustomerId(1L);
        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getAccountID(), 1);
        assertEquals(result.get(1).getAccountID(), 2);
    }

    }

