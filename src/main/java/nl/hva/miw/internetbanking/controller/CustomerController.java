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
            // Returns HTTP status code 404 if optional is empty
        } catch (NumberFormatException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @GetMapping("/api/customer/find/username/{username}")
    public ResponseEntity<Customer> findCustomerByUsername(@PathVariable String username) {
        return ResponseEntity.of(customerService.getCustomerByUsername(username));
        // Returns HTTP status code 404 if optional is empty
    }

}
