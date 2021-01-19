package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.data.dao.EmployeeDAO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.CustomerType;
import nl.hva.miw.internetbanking.model.Employee;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountServiceTest {

    @MockBean
    AccountDAO accountDAO = Mockito.mock(AccountDAO.class);

    AccountService accountService = new AccountService(accountDAO);

    public AccountServiceTest(){
        super();
    }

    @Test
   public void saveNewAccount() {
        Customer customer1 = new Customer();
        customer1.setCustomerID(1);
        customer1.setCustomerName("rknol");
        customer1.setPassword("rknol");
        customer1.setCustomerType(CustomerType.NATURAL);
        Account account = new Account(1, 1000, "NL63DROVN9283736");

    }

    }
