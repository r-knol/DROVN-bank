package nl.hva.miw.internetbankingwebapp;

import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.repository.CustomerRepository;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setId(3);
        customer.setUsername("rknol");
        customer.setPassword("rknol");

        Customer savedCustomer = customerRepository.save(customer);
        Customer exitCustomer = testEntityManager.find(Customer.class, savedCustomer.getId());

        assertThat(exitCustomer.getUsername().equals(customer.getUsername()));

    }

}


