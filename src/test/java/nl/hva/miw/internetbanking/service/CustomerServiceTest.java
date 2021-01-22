package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.data.dao.CustomerDAO;
import nl.hva.miw.internetbanking.data.dao.LegalPersonDAO;
import nl.hva.miw.internetbanking.data.dao.NaturalPersonDAO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;
import static nl.hva.miw.internetbanking.model.CustomerType.NATURAL;

class CustomerServiceTest {
    Customer nina = new Customer(1, "nvanloo", "myPassword", NATURAL);

    NaturalPerson ninaNp = new NaturalPerson(1, "N.", "Nina", "van", "Loo", "07-02-1987",
                                             "123456789", "ninavanloo@gmail.com", "06-10087058",
                                             "1056 AC", "2-H",
                                             "James Rosskade", "Amsterdam");
    List<Account> accountsNina = List.of(
            new Account(1, 99.95, "84NL0563171251"),
            new Account(2, 1234.56, "56NL0563172222")
    );
    Customer richard = new Customer(2, "rknol", "knolPassword", NATURAL);
    List<Customer> customerList = List.of(nina, richard);

    NaturalPersonDAO naturalPersonDAO = Mockito.mock(NaturalPersonDAO.class);
    LegalPersonDAO legalPersonDAO = Mockito.mock(LegalPersonDAO.class);
    CustomerDAO customerDAO = Mockito.mock(CustomerDAO.class);

    CustomerService customerService = new CustomerService(naturalPersonDAO, legalPersonDAO, customerDAO);

    public CustomerServiceTest() {
        super();
    }

    @BeforeEach
    public void testSetup() {

    }

    @Test
    void printNameCustomerTest() {
        Mockito.when(customerDAO.read(1L)).thenReturn(Optional.of(nina));
        Mockito.when(naturalPersonDAO.read(1L)).thenReturn(Optional.of(ninaNp));
        System.out.println("printNameCustomerTest");
        String actual = customerService.printNameCustomer(1);
        String expected = "Nina van Loo";
        Assertions.assertEquals(expected, actual);
    }
}