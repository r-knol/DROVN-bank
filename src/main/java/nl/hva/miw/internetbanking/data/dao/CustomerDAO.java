package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.data.dto.CustomerAccountDTO;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;

import java.util.List;

public interface CustomerDAO {
    CustomerAccountDTO getCustomerByUsernameAndPassword(String userName, String password);
    Customer getCustomerById(long id);

    // onderstaande methode toegevoegd door Nina 09-12-2020
    NaturalPerson getNpByCustomerId(long customerId);

    CustomerAccountDTO getCustomersByAccountId(long accountId);

    // onderstaande methode toegevoegd door Nina 11-12-2020
    // voor Legal Person:
    LegalPerson getLpByCustomerId(long customerId);

    List<Customer> getCustomerByAccountId(long accountId);

  Customer getCutomerByName(String userName);

  // onderstaande methode toegevoegd door Nina 09-12-2020
    // LegalPerson getLpByCustomerId(long customerId);
}
