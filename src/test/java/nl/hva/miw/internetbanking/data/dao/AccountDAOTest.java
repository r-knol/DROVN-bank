package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOTest {

    @MockBean
    AccountDAO accountDAO = Mockito.mock(AccountDAO.class);

    @Test
    void read() {
        Account account = new Account();
        long expected = 5;
        account.setAccountID(5);

        Mockito.when(accountDAO.read(expected)).
                thenReturn(Optional.of(account));
        assertEquals(expected, account.getAccountID());
    }

    }
