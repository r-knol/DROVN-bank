package nl.hva.miw.internetbanking.controller;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j(topic = "CustomerController")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(value = {"/api/customer/find/id/{id}"})
    public ResponseEntity<Customer> findCustomerById(@PathVariable String id) {
        try {
            return ResponseEntity.of(customerService.getCustomerById(Long.parseLong(id)));
        } catch (NumberFormatException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/api/customer/find/username/{username}")
    public ResponseEntity<Customer> findCustomerByUsername(@PathVariable String username) {
        return ResponseEntity.of(customerService.getCustomerByUsername(username));
    }

    @GetMapping("/api/customer/find/socialsecuritynumber/{socialSecurityNumber}")
    public ResponseEntity<Customer> findCustomerBySocialSecurityNumber(
            @PathVariable String socialSecurityNumber) {
        return ResponseEntity
                .of(customerService.getCustomerBySocialSecurityNumber(socialSecurityNumber));
    }

    @GetMapping("/api/customer/find/kvknumber/{kvknumber}")
    public ResponseEntity<Customer> findCustomerKvkNumber(@PathVariable String kvknumber) {
        try {
            return ResponseEntity
                    .of(customerService.getCustomerByKvkNumber(Long.parseLong(kvknumber)));
        } catch (NumberFormatException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
