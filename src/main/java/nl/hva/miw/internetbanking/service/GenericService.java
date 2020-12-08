package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;

public interface GenericService {
    void saveCustomer(Customer customer);
    void saveNaturalPerson(NaturalPerson naturalPerson);
    void saveLegalPerson(LegalPerson legalPerson);
    Customer getCustomerById(long id);

}
