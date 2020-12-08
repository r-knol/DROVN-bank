package nl.hva.miw.internetbankingwebapp;

import nl.hva.miw.internetbanking.InternetBankingApplication;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {InternetBankingApplication.class})
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

//    @Autowired
//    TestEntityManager testEntityManager;

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setId(3);
        customer.setUsername("rknol");
        customer.setPassword("rknol");

        Customer savedCustomer = customerRepository.save(customer);
        Customer exitCustomer = customerRepository.findAll().stream().findFirst().get();

        assertThat(exitCustomer.getUsername().equals(customer.getUsername()));
    }
}


