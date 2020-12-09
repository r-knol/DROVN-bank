package nl.hva.miw.internetbanking.repository;

import nl.hva.miw.internetbanking.model.Customer;

import java.util.Optional;

public interface CustomerDAO {
    Customer getCustomerByUsername(String userName);
}
