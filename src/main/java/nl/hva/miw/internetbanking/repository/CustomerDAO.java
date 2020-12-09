package nl.hva.miw.internetbanking.repository;

import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;

import java.util.Optional;

public interface CustomerDAO {
    Customer getCustomerByUsername(String userName);

    // onderstaande methode toegevoegd door Nina 09-12-2020
    NaturalPerson getNpByCustomerId(long customerId);

    // onderstaande methode toegevoegd door Nina 09-12-2020
    // LegalPerson getLpByCustomerId(long customerId);
}
