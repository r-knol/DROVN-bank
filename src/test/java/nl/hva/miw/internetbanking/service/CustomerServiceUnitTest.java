package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.data.CustomerTestData;
import nl.hva.miw.internetbanking.data.dao.CustomerDAO;
import nl.hva.miw.internetbanking.data.dao.LegalPersonDAO;
import nl.hva.miw.internetbanking.data.dao.NaturalPersonDAO;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceUnitTest extends CustomerTestData {
    
    CustomerService customerService;
    @Mock
    NaturalPersonDAO naturalPersonDAO;
    @Mock
    LegalPersonDAO legalPersonDAO;
    @Mock
    CustomerDAO customerDAO;
    
    @BeforeEach
    void setUp() {
        customerService = new CustomerService(naturalPersonDAO, legalPersonDAO, customerDAO);
    }
    
    @Nested
    @DisplayName("Tests getCustomerByUsername")
    class GetCustomerByUsernameTests {
        
        @BeforeEach
        void setUp() {
            // First get data from Customer table
            Mockito.when(customerDAO.read(USERNAME)).thenReturn(Optional.of(customerNatural));
            // Then add details from NaturalPerson table
            Mockito.when(naturalPersonDAO.read(CUSTOMER_ID))
                    .thenReturn(Optional.of(privateCustomer));
        }
        
        @Test
        void Should_ReturnOptionalOfCustomer_When_GetCustomerByUsernameAndUsernameExists() {
            Optional<Customer> optionalCustomer = customerService.getCustomerByUsername(USERNAME);
            assertThat(optionalCustomer).isPresent();
            Customer actual = optionalCustomer.get();
            assertThat(actual).isEqualTo(privateCustomer);
        }
        
    }
    
    @Nested
    @DisplayName("Tests getCustomerBySocialSecurityNumber")
    class GetCustomerBySocialSecurityNumber {
        
        @BeforeEach
        void setUp() {
            NaturalPerson naturalPerson = privateCustomer;
            naturalPerson.setUserName("");
            naturalPerson.setPassword("");
            Mockito.when(naturalPersonDAO.read(SOCIAL_SECURITY_NUMBER))
                    .thenReturn(Optional.of(naturalPerson));
            Mockito.when(customerDAO.read(CUSTOMER_ID)).thenReturn(Optional.of(customerNatural));
        }
        
        @Test
        void Should_ReturnOptionalOfCustomer_When_GetCustomerBySocialSecurityNumberAndExists() {
            Optional<Customer> optionalCustomer = customerService
                    .getCustomerBySocialSecurityNumber(SOCIAL_SECURITY_NUMBER);
            assertThat(optionalCustomer).isPresent();
            Customer actual = optionalCustomer.get();
            assertThat(actual).isEqualTo(privateCustomer);
            assertThat(actual.getUserName()).isNotEqualTo("");
            assertThat(actual.getPassword()).isNotEqualTo("");
        }
        
    }
    
    
}
