package nl.hva.miw.internetbanking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hva.miw.internetbanking.exception.ApiError;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// Integration test, that also tests HTTP-layer
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = CustomerController.class)
//@Slf4j(topic = "CustomerControllerTest")
class CustomerControllerTest extends AbstractControllerTest {
    
    private static final String BASE_URL_ID = "/api/customer/find/id/";
    private static final String BASE_URL_USERNAME = "/api/customer/find/username/";
    
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CustomerService customerService;
    
    public CustomerControllerTest() {
        super();
    }
    
    @Nested
    @DisplayName("Endpoint '" + BASE_URL_ID + "'")
    class FindCustomerByIdTests {
        
        private static final long PARAM_ID_NOT_FOUND = 9L;
        private static final long PARAM_ID_CONSTRAINT = -1L;
        private static final String PARAM_ID_INVALID = "invalid";
        private static final String MSG_ID_NOT_FOUND = "Geen klant gevonden met id=9";
        private static final String MSG_ID_CONSTRAINT =
                "Parameter 'id' moet een positief getal zijn";
        private static final String MSG_ID_INVALID = "Ongeldige parameter 'id'";
        
        @BeforeEach
        void setUp() {
            Mockito.when(customerService.getCustomerById(CUSTOMER_ID))
                    .thenReturn(Optional.of(privateCustomer));
        }
        
        @Nested
        @DisplayName("Verify HTTP-status")
        class HttpStatusTests {
            
            @Test
            @DisplayName("OK when id valid & customer exists")
            void When_ValidIdAndCustomerExists_Then_Status200() throws Exception {
                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                        .get(BASE_URL_ID + CUSTOMER_ID).contentType("application/json");
                ResultMatcher statusIsOk = status().isOk();
                mockMvc.perform(request).andExpect(statusIsOk);
            }
            
            @Test
            @DisplayName("NOT FOUND when id valid & customer not found")
            void When_ValidIdAndCustomerDoesNotExist_Then_Status404() throws Exception {
                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                        .get(BASE_URL_ID + PARAM_ID_NOT_FOUND)
                        .contentType("application/json");
                ResultMatcher statusIsNotFound = status().isNotFound();
                mockMvc.perform(request).andExpect(statusIsNotFound);
            }
            
            @Test
            @DisplayName("BAD REQUEST when id invalid, validation fails")
            void When_InvalidId_Then_Status400() throws Exception {
                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                        .get(BASE_URL_ID + PARAM_ID_INVALID)
                        .contentType("application/json");
                ResultMatcher statusIsBadRequest = status().isBadRequest();
                mockMvc.perform(request).andExpect(statusIsBadRequest);
            }
            
        }
        
        @Nested
        @DisplayName("Verify output")
        class VerifyOutputTests {
            
            @Test
            @DisplayName("Return correct entity when exists")
            void When_ValidIDAndCustomerExists_Then_ReturnsNaturalPersonEntity() throws Exception {
                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                        .get(BASE_URL_ID + CUSTOMER_ID);
                String expectedResponseBody = objectMapper.writeValueAsString(privateCustomer);
                ResultMatcher isEqualContent = content().json(expectedResponseBody);
                mockMvc.perform(request).andExpect(isEqualContent);
            }
            
        }
        
        @Nested
        @DisplayName("Exception handling")
        class ExceptionHandlingTests {
            
            @Test
            @DisplayName("EntityNotFoundException when id valid & customer not found")
            void When_ValidIdAndCustomerNotFound_Then_Returns404AndApiError() throws Exception {
                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                        .get(BASE_URL_ID + PARAM_ID_NOT_FOUND);
                // EntityNotFoundException is thrown by CustomerController and handled in
                // exception.RestExceptionHandler
                ApiError expectedApiError = new ApiError(HttpStatus.NOT_FOUND, MSG_ID_NOT_FOUND);
                String expectedResponseBody = objectMapper.writeValueAsString(expectedApiError);
                ResultMatcher contentIsEqual = content().json(expectedResponseBody);
                ResultMatcher statusIsNotFound = status().isNotFound();
                mockMvc.perform(request).andExpect(contentIsEqual).andExpect(statusIsNotFound);
            }
            
            @Test
            @DisplayName("ConstraintViolationException when validation fails")
            void When_ConstraintViolated_Then_Returns400AndApiError() throws Exception {
                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                        .get(BASE_URL_ID + PARAM_ID_CONSTRAINT);
                // ConstraintViolationException is handled in exception.RestExceptionHandler
                ApiError expectedApiError = new ApiError(HttpStatus.BAD_REQUEST, MSG_ID_CONSTRAINT);
                String expectedResponseBody = objectMapper.writeValueAsString(expectedApiError);
                ResultMatcher contentIsEqual = content().json(expectedResponseBody);
                ResultMatcher statusIsBadRequest = status().isBadRequest();
                mockMvc.perform(request).andExpect(contentIsEqual).andExpect(statusIsBadRequest);
            }
            
            @Test
            @DisplayName("MethodArgumentTypeMismatchException")
            void When_ParameterIsOfWrongType_Then_Returns400AndApiError() throws Exception {
                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                        .get(BASE_URL_ID + PARAM_ID_INVALID);
                // MethodArgumentTypeMismatchException is handled in exception.RestExceptionHandler
                ApiError expectedApiError = new ApiError(HttpStatus.BAD_REQUEST, MSG_ID_INVALID);
                String expectedResponseBody = objectMapper.writeValueAsString(expectedApiError);
                ResultMatcher contentIsEqual = content().json(expectedResponseBody);
                ResultMatcher statusIsBadRequest = status().isBadRequest();
                mockMvc.perform(request).andExpect(contentIsEqual).andExpect(statusIsBadRequest);
            }
            
        }
        
    }
    
    @Nested
    @DisplayName("Endpoint '" + BASE_URL_USERNAME + "'")
    class findCustomerByUsernameTests {
        
        private static final String PARAM_USERNAME_NOT_FOUND = "does_not_exist";
        private static final String PARAM_USERNAME_INVALID = "inv";
        private static final String MSG_USERNAME_NOT_FOUND =
                "Geen klant gevonden met username=does_not_exist";
        private static final String MSG_USERNAME_INVALID = "Parameter 'username' is ongeldig";
        
        @BeforeEach
        void setUp() {
            Mockito.when(customerService.getCustomerByUsername(USERNAME))
                    .thenReturn(Optional.of(privateCustomer));
        }
        
        @Nested
        @DisplayName("Verify HTTP-status")
        class HttpStatusTests {
            
            @Test
            @DisplayName("OK when username valid & customer exists")
            void When_ValidUsernameAndCustomerExists_Then_Status200() throws Exception {
                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                        .get(BASE_URL_USERNAME + USERNAME)
                        .contentType("application/json");
                ResultMatcher statusIsOk = status().isOk();
                mockMvc.perform(request).andExpect(statusIsOk);
            }
            
            @Test
            @DisplayName("NOT FOUND when username valid & customer not found")
            void When_ValidUsernameAndCustomerDoesNotExist_Then_Status404() throws Exception {
                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                        .get(BASE_URL_USERNAME + PARAM_USERNAME_NOT_FOUND)
                        .contentType("application/json");
                ResultMatcher statusIsNotFound = status().isNotFound();
                mockMvc.perform(request).andExpect(statusIsNotFound);
            }
            
            @Test
            @DisplayName("BAD REQUEST when username invalid, validation fails")
            void When_InvalidUsername_Then_Status400() throws Exception {
                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                        .get(BASE_URL_USERNAME + PARAM_USERNAME_INVALID)
                        .contentType("application/json");
                ResultMatcher statusIsBadRequest = status().isBadRequest();
                mockMvc.perform(request).andExpect(statusIsBadRequest);
            }
            
        }
        
        @Nested
        @DisplayName("Verify output")
        class VerifyOutputTests {
            
            @Test
            @DisplayName("Return correct entity when exists")
            void When_ValidUsernameAndCustomerExists_Then_ReturnsNaturalPersonEntity() throws
                    Exception {
                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                        .get(BASE_URL_USERNAME + USERNAME);
                String expectedResponseBody = objectMapper.writeValueAsString(privateCustomer);
                ResultMatcher isEqualContent = content().json(expectedResponseBody);
                mockMvc.perform(request).andExpect(isEqualContent);
            }
            
        }
        
        @Nested
        @DisplayName("Exception handling")
        class ExceptionHandlingTests {
            
            @Test
            @DisplayName("EntityNotFoundException when username valid & customer not found")
            void When_ValidUsernameAndCustomerNotFound_Then_Returns404AndApiError() throws
                    Exception {
                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                        .get(BASE_URL_USERNAME + PARAM_USERNAME_NOT_FOUND);
                // EntityNotFoundException is thrown by CustomerController and handled in
                // exception.RestExceptionHandler
                ApiError expectedApiError =
                        new ApiError(HttpStatus.NOT_FOUND, MSG_USERNAME_NOT_FOUND);
                String expectedResponseBody = objectMapper.writeValueAsString(expectedApiError);
                ResultMatcher contentIsEqual = content().json(expectedResponseBody);
                ResultMatcher statusIsNotFound = status().isNotFound();
                mockMvc.perform(request).andExpect(contentIsEqual).andExpect(statusIsNotFound);
            }
            
            @Test
            @DisplayName("ConstraintViolationException when validation fails")
            void When_ConstraintViolated_Then_Returns400AndApiError() throws Exception {
                MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                        .get(BASE_URL_USERNAME + PARAM_USERNAME_INVALID);
                // ConstraintViolationException is handled in exception.RestExceptionHandler
                ApiError expectedApiError =
                        new ApiError(HttpStatus.BAD_REQUEST, MSG_USERNAME_INVALID);
                String expectedResponseBody = objectMapper.writeValueAsString(expectedApiError);
                ResultMatcher contentIsEqual = content().json(expectedResponseBody);
                ResultMatcher statusIsBadRequest = status().isBadRequest();
                mockMvc.perform(request).andExpect(contentIsEqual).andExpect(statusIsBadRequest);
            }
            
        }
        
    }
}