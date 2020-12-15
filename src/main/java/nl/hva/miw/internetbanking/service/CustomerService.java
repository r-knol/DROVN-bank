package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.data.dao.JdbcCustomerDAO;
import nl.hva.miw.internetbanking.data.dto.CustomerAccountDTO;
import nl.hva.miw.internetbanking.data.dao.CustomerDAO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private CustomerDAO customerDAO;
    private AccountDAO accountDAO;

    @Autowired
    public CustomerService(CustomerDAO customerDAO, AccountDAO accountDAO) { // Zorgt dat JdbcKlantDao object wordt geïnjecteerd
      super();
      this.customerDAO = customerDAO;
      this.accountDAO = accountDAO;
    }

    public CustomerAccountDTO getCustomerByUsernameAndPassword(String userName, String password) {
        return customerDAO.getCustomerByUsernameAndPassword(userName, password);
    }

    // onderstaande methode toegevoegd door Nina 09-12-2020
    public NaturalPerson getNpByCustomerId(long customerId) {
        return customerDAO.getNpByCustomerId(customerId);
    }

    // onderstaande methode toegevoegd door Nina 11-12-2020
    public LegalPerson getLpbyCustomerId(long customerId) {
        return customerDAO.getLpByCustomerId(customerId);
    }

    public Customer createCustomer(long customerId) {
      Customer customer = customerDAO.getCustomerById(customerId);
      List<Account> customerAccounts = accountDAO.getAccountsForCustomer(customer);
      customer.setAccounts(customerAccounts);
      return customer;
    }

    // // onderstaande methode toegevoegd door Nina 11-12-2020
    public String printNameCustomer(long customerId) {
        // eerst checken of klant een NaturalPerson is:
        NaturalPerson np = getNpByCustomerId(customerId);
        String nameNp;

//        if (!np.equals(null)) { // als NaturalPerson, dan:
            // afhandeling voorvoegsel:
            if (np.getPreposition() != null) {
                nameNp = String.format("%s %s %s", np.getFirstName(), np.getPreposition(), np.getSurName());
                return nameNp;
            }
            // bij geen voorvoegsel:
            nameNp = String.format("%s %s", np.getFirstName(), np.getSurName());
            return nameNp;
            // als het geen NaturalPerson is, dan kijken in tabel LegalPerson:
//        }
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

  public Customer getCustomerByName(String userName) {
      return customerDAO.getCutomerByName(userName);
  }
}
