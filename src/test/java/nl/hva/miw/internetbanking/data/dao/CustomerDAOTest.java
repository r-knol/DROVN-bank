package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOTest extends JDBCSetupDAOTest {

    @Autowired
    public CustomerDAOTest(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Test
    public void readCustomerTest(){
        CustomerDAO customerDAO = new CustomerDAO(jdbcTemplate);
        Optional<Customer> optionalCustomer = customerDAO.read(1L);
        assertTrue(optionalCustomer.isPresent());
    }
}