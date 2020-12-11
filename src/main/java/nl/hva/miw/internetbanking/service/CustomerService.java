package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.data.dto.CustomerAccountDTO;
import nl.hva.miw.internetbanking.data.dao.CustomerDAO;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerDAO customerDAO;

    @Autowired
    public CustomerService(CustomerDAO customerDAO) { // Zorgt dat JdbcKlantDao object wordt geïnjecteerd
        this.customerDAO = customerDAO;
    }

    public CustomerAccountDTO getCustomerByUsernameAndPassword(String userName, String password) {
        return customerDAO.getCustomerByUsernameAndPassword(userName, password);
    }

    // onderstaande methode toegevoegd door Nina 09-12-2020
    public NaturalPerson getNpByCustomerId(long customerId) {
        return customerDAO.getNpByCustomerId(customerId);
    }

    public Customer getCustomerById(long id) {
        return customerDAO.getCustomerById(id);
    }

    public List<Customer> getCustomerByAccountId(long accountId) {
        return customerDAO.getCustomerByAccountId(accountId);
    }

    public CustomerAccountDTO getCustomersByAccount(long accountId) {
        return customerDAO.getCustomersByAccountId(accountId);
    }

}
