package nl.hva.miw.internetbanking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hva.miw.internetbanking.exception.ApiError;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Integration test, that also tests the HTTP-layer
@WebMvcTest(CustomerController.class)
class CustomerControllerTest extends AbstractControllerTestData {
    
    private static final String URL_ID = "/api/customer/find/id/";
    private static final String URL_USERNAME = "/api/customer/find/username/";
    private static final String URL_SSN = "/api/customer/find/socialsecuritynumber/";
    private static final String URL_KVK = "/api/customer/find/kvknumber/";
    
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CustomerService customerService;
    
    public CustomerControllerTest() {
        super();
    }
    
    private MockHttpServletRequestBuilder getUrl(String url) {
        return MockMvcRequestBuilders.get(url).accept(APPLICATION_JSON);
    }
    
    private String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
    
    @Nested
    @DisplayName("Tests endpoint '" + URL_ID + "'")
    class FindCustomerByIdTests {
        
        private static final long NOT_FOUND_ID = 9L; // only 999L will be found
        private static final String NOT_FOUND_ID_MSG = "Geen klant gevonden met id=" + NOT_FOUND_ID;
        private static final long VIOLATION_ID = -1L; // should be positive number
        private static final String VIOLATION_ID_MSG = "Parameter 'id' moet positief getal zijn";
        private static final String MISMATCH_ID = "type_mismatch"; // should be of type long
        private static final String MISMATCH_ID_MSG = "Ongeldige parameter 'id'";
        
        @BeforeEach
        void setUp() {
            Mockito.when(customerService.getCustomerById(CUSTOMER_ID)) // 999L
                    .thenReturn(Optional.of(privateCustomer));
        }
        
        @Test
        @DisplayName("Returns HTTP 200 OK & Customer JSON when id valid & found")
        void When_ValidIdAndCustomerExists_Then_Returns200AndCustomerJson() throws Exception {
            mockMvc.perform(getUrl(URL_ID + CUSTOMER_ID))
                    .andExpect(content().json(toJson(privateCustomer)))
                    .andExpect(jsonPath("$.customerID").value(CUSTOMER_ID))
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        
        @Test
        @DisplayName("Returns HTTP 404 NOT FOUND & ApiError JSON when id valid & not found")
        void When_ValidIdAndCustomerNotFound_Then_Returns404AndApiError() throws Exception {
            // CustomerController should throw EntityNotFoundException which is handled in
            // exception.RestExceptionHandler
            mockMvc.perform(getUrl(URL_ID + NOT_FOUND_ID))
                    .andExpect(content().json(toJson(new ApiError(NOT_FOUND, NOT_FOUND_ID_MSG))))
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }
        
        @Test
        @DisplayName("Returns HTTP 400 BAD REQUEST & ApiError JSON when id violates constraint")
        void When_IdViolatesConstraint_Then_Returns400AndApiError() throws Exception {
            // CustomerController should throw ConstraintViolationException which is handled in
            // exception.RestExceptionHandler
            mockMvc.perform(getUrl(URL_ID + VIOLATION_ID))
                    .andExpect(content().json(toJson(new ApiError(BAD_REQUEST, VIOLATION_ID_MSG))))
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
        
        @Test
        @DisplayName("Returns HTTP 400 BAD REQUEST & ApiError JSON when id type mismatch")
        void WhenIdTypeMismatch_Then_Returns400AndApiError() throws Exception {
            // CustomerController should throw MethodArgumentTypeMismatchException which is handled
            // in exception.RestExceptionHandler
            mockMvc.perform(getUrl(URL_ID + MISMATCH_ID))
                    .andExpect(content().json(toJson(new ApiError(BAD_REQUEST, MISMATCH_ID_MSG))))
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
        
    }
    
    @Nested
    @DisplayName("Tests endpoint '" + URL_USERNAME + "'")
    class findCustomerByUsernameTests {
        
        private static final String NOT_FOUND_USER = "does_not_exist"; // only "ppost" will be found
        private static final String NOT_FOUND_USER_MSG =
                "Geen klant gevonden met username=" + NOT_FOUND_USER;
        private static final String VIOLATION_USER = "inv"; // minimum length = 4
        private static final String VIOLATION_USER_MSG = "Parameter 'username' is ongeldig";
        
        @BeforeEach
        void setUp() {
            Mockito.when(customerService.getCustomerByUsername(USERNAME)) // "ppost"
                    .thenReturn(Optional.of(privateCustomer));
        }
        
        @Test
        @DisplayName("Returns HTTP 200 OK & Customer JSON when username valid & found")
        void When_ValidUsernameAndCustomerExists_Then_Returns200AndCustomerJson() throws Exception {
            mockMvc.perform(getUrl(URL_USERNAME + USERNAME))
                    .andExpect(content().json(toJson(privateCustomer)))
                    .andExpect(jsonPath("$.userName").value(USERNAME))
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        
        @Test
        @DisplayName("Returns HTTP 404 NOT FOUND & ApiError JSON when username valid & not found")
        void When_ValidUsernameAndCustomerNotFound_Then_Returns404AndApiError() throws Exception {
            // CustomerController should throw EntityNotFoundException which is handled in
            // exception.RestExceptionHandler
            mockMvc.perform(getUrl(URL_USERNAME + NOT_FOUND_USER))
                    .andExpect(content().json(toJson(new ApiError(NOT_FOUND, NOT_FOUND_USER_MSG))))
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }
        
        @Test
        @DisplayName(
                "Returns HTTP 400 BAD REQUEST & ApiError JSON when username violates constraint")
        void When_UsernameViolatesConstraint_Then_Returns400AndApiError() throws Exception {
            // CustomerController should throw ConstraintViolationException which is handled in
            // exception.RestExceptionHandler
            mockMvc.perform(getUrl(URL_USERNAME + VIOLATION_USER))
                    .andExpect(
                            content().json(toJson(new ApiError(BAD_REQUEST, VIOLATION_USER_MSG))))
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
        
    }
    
    @Nested
    @DisplayName("Tests endpoint '" + URL_SSN + "'")
    class findCustomerBySocialSecurityNumberTests {
        
        private static final String NOT_FOUND_SSN = "999999999"; // only "123456789" will be found
        private static final String NOT_FOUND_SSN_MSG =
                "Geen klant gevonden met socialSecurityNumber=" + NOT_FOUND_SSN;
        private static final String VIOLATION_SSN = "1234567"; // should be 8 or 9 numbers
        private static final String VIOLATION_SSN_MSG =
                "Parameter 'socialSecurityNumber' moet 8 of 9 cijfers zijn";
        
        @BeforeEach
        void setUp() {
            Mockito.when(customerService
                    .getCustomerBySocialSecurityNumber(SOCIAL_SECURITY_NUMBER)) // "123456789"
                    .thenReturn(Optional.of(privateCustomer)
                    );
        }
        
        @Test
        @DisplayName("Returns HTTP 200 OK & Customer JSON when ssn valid & found")
        void When_ValidSsnAndCustomerExists_Then_Returns200AndCustomerJson() throws Exception {
            mockMvc.perform(getUrl(URL_SSN + SOCIAL_SECURITY_NUMBER))
                    .andExpect(content().json(toJson(privateCustomer)))
                    .andExpect(jsonPath("$.socialSecurityNumber").value(SOCIAL_SECURITY_NUMBER))
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        
        @Test
        @DisplayName("Returns HTTP 404 NOT FOUND & ApiError JSON when ssn valid & not found")
        void When_ValidSsnAndCustomerNotFound_Then_Returns404AndApiError() throws Exception {
            // CustomerController should throw EntityNotFoundException which is handled in
            // exception.RestExceptionHandler
            mockMvc.perform(getUrl(URL_SSN + NOT_FOUND_SSN))
                    .andExpect(content().json(toJson(new ApiError(NOT_FOUND, NOT_FOUND_SSN_MSG))))
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }
        
        @Test
        @DisplayName("Returns HTTP 400 BAD REQUEST & ApiError JSON when ssn violates constraint")
        void When_SsnViolatesConstraint_Then_Returns400AndApiError() throws Exception {
            // CustomerController should throw ConstraintViolationException which is handled in
            // exception.RestExceptionHandler
            mockMvc.perform(getUrl(URL_SSN + VIOLATION_SSN))
                    .andExpect(content().json(toJson(new ApiError(BAD_REQUEST, VIOLATION_SSN_MSG))))
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
        
    }
    
    @Nested
    @DisplayName("Tests endpoint '" + URL_KVK + "'")
    class FindCustomerByKvkNumberTests {
        
        private static final long NOT_FOUND_KVK = 13579246L; // only 12345678L will be found
        private static final String NOT_FOUND_KVK_MSG =
                "Geen klant gevonden met kvknumber=" + NOT_FOUND_KVK;
        private static final long VIOLATION_KVK = 12345L; // should be 8 numbers
        private static final String VIOLATION_KVK_MSG =
                "Parameter 'kvknumber' moet uit 8 cijfers bestaan";
        
        @BeforeEach
        void setUp() {
            Mockito.when(customerService.getCustomerByKvkNumber(KVK_NUMBER)) // 12345678L
                    .thenReturn(Optional.of(businessCustomer));
        }
        
        @Test
        @DisplayName("Returns HTTP 200 OK & Customer JSON when kvknumber valid & found")
        void When_ValidKvkNumberAndCustomerExists_Then_Returns200AndCustomerJson() throws
                Exception {
            mockMvc.perform(getUrl(URL_KVK + KVK_NUMBER))
                    .andExpect(content().json(toJson(businessCustomer)))
                    .andExpect(jsonPath("$.kvkNumber").value(KVK_NUMBER))
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
        
        @Test
        @DisplayName("Returns HTTP 404 NOT FOUND & ApiError JSON when kvknumber valid & not found")
        void When_ValidKvkNumberAndCustomerNotFound_Then_Returns404AndApiError() throws Exception {
            // CustomerController should throw EntityNotFoundException which is handled in
            // exception.RestExceptionHandler
            mockMvc.perform(getUrl(URL_KVK + NOT_FOUND_KVK))
                    .andExpect(content().json(toJson(new ApiError(NOT_FOUND, NOT_FOUND_KVK_MSG))))
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }
        
        @Test
        @DisplayName(
                "Returns HTTP 400 BAD REQUEST & ApiError JSON when kvknumber violates constraint")
        void When_KvkNumberViolatesConstraint_Then_Returns400AndApiError() throws Exception {
            // CustomerController should throw ConstraintViolationException which is handled in
            // exception.RestExceptionHandler
            mockMvc.perform(getUrl(URL_KVK + VIOLATION_KVK))
                    .andExpect(content().json(toJson(new ApiError(BAD_REQUEST, VIOLATION_KVK_MSG))))
                    .andExpect(content().contentType(APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
        
    }
    
}