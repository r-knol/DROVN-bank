package nl.hva.miw.internetbanking.repository;

import nl.hva.miw.internetbanking.DTO.CustomerAccountDTO;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;

import java.util.List;
import java.util.Optional;

public interface CustomerDAO {
    CustomerAccountDTO getCustomerByUsernameAndPassword(String userName, String password);
    Customer getCustomerById(long id);

    // onderstaande methode toegevoegd door Nina 09-12-2020
    NaturalPerson getNpByCustomerId(long customerId);

    CustomerAccountDTO getCustomersByAccountId(long accountId);

    List<Customer> getCustomerByAccountId(long accountId);

    // onderstaande methode toegevoegd door Nina 09-12-2020
    // LegalPerson getLpByCustomerId(long customerId);
}
