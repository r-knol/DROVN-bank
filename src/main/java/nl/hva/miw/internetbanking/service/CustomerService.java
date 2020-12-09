package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.repository.CustomerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private CustomerDAO customerDAO;

    @Autowired
    public CustomerService(CustomerDAO customerDAO) { // Zorgt dat JdbcKlantDao object wordt geïnjecteerd
        this.customerDAO = customerDAO;
    }

    public boolean valideerLogin(String userName, String password) { // Checkt ingevoerde gegevens
        Customer customer = customerDAO.getCustomerByUsername(userName);
        return customer != null && customer.getUsername().equals(userName) && customer.getPassword().equals(password);
    }
}
