package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.data.dao.CustomerDAO;
import nl.hva.miw.internetbanking.data.dao.LegalPersonDAO;
import nl.hva.miw.internetbanking.data.dao.NaturalPersonDAO;
import nl.hva.miw.internetbanking.data.dto.CustomerAccountDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

  private NaturalPersonDAO naturalPersonDAO;
  private LegalPersonDAO legalPersonDAO;
  private CustomerDAO customerDAO;
  private AccountDAO accountDAO;

  @Autowired
  public CustomerService(
      CustomerDAO customerDAO,
      NaturalPersonDAO naturalPersonDAO,
      LegalPersonDAO legalPersonDAO,
      AccountDAO accountDAO) {
    this.naturalPersonDAO = naturalPersonDAO;
    this.legalPersonDAO = legalPersonDAO;
    this.customerDAO = customerDAO;
    this.accountDAO = accountDAO;
  }

  // DataIntegrityViolationException - bij onjuist datum format
  // SQLIntegrityConstraintViolationException - bij null waarden voor non-null
  public <T extends Customer> void saveCustomer(T entity) {
    //TODO CustomerDAO, CustomrDAO, JDBCCustomerDAO... FIX IT!
    //customerDAO.create(entity);
    if (entity instanceof NaturalPerson) {
      naturalPersonDAO.create((NaturalPerson) entity);
    } else if (entity instanceof LegalPerson) {
      legalPersonDAO.create((LegalPerson) entity);
    }
  }

  public Optional<Customer> getCustomerById(long id) {
    Optional<Customer> optionalCustomer = customerDAO.read(id);
    if (optionalCustomer.isPresent()) {
      Customer customer = optionalCustomer.get();
      switch (customer.getCustomerType()) {
        case NATURAL:
          Optional<NaturalPerson> naturalPersonOptional = naturalPersonDAO.read(id);
          if (naturalPersonOptional.isPresent()) {
            customer = naturalPersonOptional.get();
          }
          break;
        case LEGAL:
          Optional<LegalPerson> legalPersonOptional = legalPersonDAO.read(id);
          if (legalPersonOptional.isPresent()) {
            customer = legalPersonOptional.get();
          }
          break;
      }
      return Optional.of(customer);
    }
    return Optional.empty();
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
    // eerst checken welk type klant het is:
//        try {
//            Customer customer = findById(customerId);

//        // in case of NaturalPerson:
//        if (customer instanceof NaturalPerson) {
//        NaturalPerson np = ((NaturalPerson) customer);
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

    // Methode met optional gebruiken
//    public Customer getCustomerById(long id) {
//        return customerDAO.getCustomerById(id);
//    }

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
