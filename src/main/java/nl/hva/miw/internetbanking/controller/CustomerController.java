package nl.hva.miw.internetbanking.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.exception.EntityNotFoundException;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.util.Optional;

@Slf4j(topic = "CustomerController")
@RestController
@RequestMapping(path = "/api/customer", produces = "application/json")
@Validated
public class CustomerController {
    
    private static final String NOT_FOUND = "Geen klant gevonden ";
    private static final String WITH_ID = "met id=";
    private static final String WITH_USERNAME = "met username=";
    private static final String WITH_SOCIAL_SECURITY_NUMBER = "met socialSecurityNumber=";
    private static final String WITH_KVK_NUMBER = "met kvknumber=";
    
    private final CustomerService customerService;
    
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @GetMapping(path = {"/find/id/{id}"})
    public ResponseEntity<Customer> findCustomerById(
            @PathVariable @PositiveOrZero(message = "moet een positief getal zijn") long id) {
        Optional<Customer> customerOptional = customerService.getCustomerById(id);
        if (customerOptional.isEmpty()) {
            throw new EntityNotFoundException(NOT_FOUND + WITH_ID + id);
        }
        return ResponseEntity.of(customerOptional);
    }
    
    @GetMapping("/find/username/{username}")
    public ResponseEntity<Customer> findCustomerByUsername(@PathVariable
    @Pattern(regexp = "[A-Za-z0-9_]{4,16}$",
            message = "is ongeldig") String username) {
        Optional<Customer> customerOptional = customerService.getCustomerByUsername(username);
        if (customerOptional.isEmpty()) {
            throw new EntityNotFoundException(NOT_FOUND + WITH_USERNAME + username);
        }
        return ResponseEntity.of(customerOptional);
    }
    
    @GetMapping("/find/socialsecuritynumber/{socialSecurityNumber}")
    public ResponseEntity<Customer> findCustomerBySocialSecurityNumber(
            @PathVariable String socialSecurityNumber) {
        Optional<Customer> customerOptional =
                customerService.getCustomerBySocialSecurityNumber(socialSecurityNumber);
        if (customerOptional.isEmpty()) {
            throw new EntityNotFoundException(
                    NOT_FOUND + WITH_SOCIAL_SECURITY_NUMBER + socialSecurityNumber);
        }
        return ResponseEntity.of(customerOptional);
    }
    
    @GetMapping("/find/kvknumber/{kvknumber}")
    public ResponseEntity<Customer> findCustomerKvkNumber(
            @PathVariable @Pattern(regexp = "^[0-9]{8}$",
                    message = "moet uit 8-cijfers bestaan") String kvknumber) {
        Long asLong = Long.parseLong(kvknumber);
        Optional<Customer> customerOptional = customerService.getCustomerByKvkNumber(asLong);
        if (customerOptional.isEmpty()) {
            throw new EntityNotFoundException(NOT_FOUND + WITH_KVK_NUMBER + kvknumber);
        }
        return ResponseEntity.of(customerOptional);
    }
    
}
