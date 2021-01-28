package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
@ActiveProfiles("test")
class AccountDAOTest {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AccountDAOTest(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Test
    public void DAOTestReadID(){
        AccountDAO dao = new AccountDAO(jdbcTemplate);
        Optional<Account> optionaleAccount = dao.read(1L);
        assertTrue(optionaleAccount.isPresent());
    }

//    @Test
//    public void DAOTestReadIBAN(){
//        AccountDAO dao = new AccountDAO(jdbcTemplate);
//        Optional<Account> optionaleAccount = dao.read("NL34DROVN8392873654");
//        assertTrue(optionaleAccount.isPresent());
//    }
}
