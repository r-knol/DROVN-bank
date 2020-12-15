package nl.hva.miw.internetbanking.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dto.RegistrationDto;
import nl.hva.miw.internetbanking.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "RegistrationService")
public class RegistrationService {

    private final CustomerService customerService;
    @Getter
    @Setter
    private RegistrationDto registrationDto;

    @Autowired
    public RegistrationService(CustomerService customerService) {
        this.customerService = customerService;
        registrationDto = new RegistrationDto();
    }

    public void registerPrivateCustomer() {
        Customer customer = createCustomerObject(registrationDto.getNaturalPerson());
        customerService.saveCustomer(customer);
    }

    public <T extends Customer> Customer createCustomerObject(T customer) {
        customer.setCustomerID(registrationDto.getCustomer().getCustomerID());
        customer.setUserName(registrationDto.getCustomer().getUserName());
        customer.setPassword(registrationDto.getCustomer().getPassword());
        return customer;
    }

    public void registerBusinessCustomer() {
        Customer customer = createCustomerObject(registrationDto.getLegalPerson());
        customerService.saveCustomer(customer);
    }

}