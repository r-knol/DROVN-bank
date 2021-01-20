package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.data.dao.CustomerDAO;
import nl.hva.miw.internetbanking.data.dao.EmployeeDAO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.CustomerType;
import nl.hva.miw.internetbanking.model.Employee;
import org.junit.Before;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountDAO accountDAO;

    @InjectMocks
    private AccountService accountService;



    Customer customer1 = new Customer(1, "rknol", "rknol", CustomerType.NATURAL);
    Account account = new Account(1, 1000, "NL63DROVN9283736");


    public AccountServiceTest() {
        super();
    }

    @Test
    public void getAccountsByCustomerId() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account(1, 1000.00, "NL64DROVN283947389"));
        accounts.add(new Account(2, 2000.00, "NL83DROVN827362545"));
        accounts.add(new Account(3, 3000.00, "NL98DROVN839405938"));

    }

}
