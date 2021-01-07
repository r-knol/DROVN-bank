package nl.hva.miw.internetbanking.service;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.data.dao.CustomerDAO;
import nl.hva.miw.internetbanking.data.dao.LegalPersonDAO;
import nl.hva.miw.internetbanking.data.dao.NaturalPersonDAO;
import nl.hva.miw.internetbanking.data.dto.BalancePerSectorDTO;
import nl.hva.miw.internetbanking.data.dto.CompanyTransactionDTO;
import nl.hva.miw.internetbanking.data.dto.DTO;
import nl.hva.miw.internetbanking.model.*;
import nl.hva.miw.internetbanking.util.DtoMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j(topic = "CustomerService")
public class CustomerService {

    private final NaturalPersonDAO naturalPersonDAO;
    private final LegalPersonDAO legalPersonDAO;
    private final AccountDAO accountDAO;
    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerService(NaturalPersonDAO naturalPersonDAO, LegalPersonDAO legalPersonDAO,
                           AccountDAO accountDAO, CustomerDAO customerDAO) {
        this.naturalPersonDAO = naturalPersonDAO;
        this.legalPersonDAO = legalPersonDAO;
        this.accountDAO = accountDAO;
        this.customerDAO = customerDAO;
    }

    public <T extends Customer> void registerCustomer(DTO<T> dto, Class<T> customerClass) {
        T customer = DtoMapperUtil.mapDtoToEntity(dto, customerClass);
        saveCustomer(customer);
    }

    // DataIntegrityViolationException - bij onjuist datum format
    // SQLIntegrityConstraintViolationException - bij null waarden voor non-null
    @Transactional
    public <T extends Customer> void saveCustomer(T entity) {
        customerDAO.create(entity);
        if (entity instanceof NaturalPerson) {
            naturalPersonDAO.create((NaturalPerson) entity);
        } else if (entity instanceof LegalPerson) {
            legalPersonDAO.create((LegalPerson) entity);
        }
    }

    public List<BalancePerSectorDTO> getAvgBalancePerSegment() {
        return legalPersonDAO.getAvgBalancePerSegment();
    }

    public List<CompanyTransactionDTO> getMostActiveClients() {
        return legalPersonDAO.getMostActiveClients();
    }

    public Optional<Customer> getCustomerById(long customerID) {
        return getCustomerDetails(customerDAO.read(customerID));
    }

    private Optional<Customer> getCustomerDetails(Optional<Customer> optionalCustomer) {
        try {
            if (optionalCustomer.isPresent()) {
                Customer customer = optionalCustomer.get();
                if (customer.getCustomerType().equals(CustomerType.NATURAL)) {
                    Optional<NaturalPerson> naturalPersonOptional =
                            naturalPersonDAO.read(customer.getCustomerID());
                    if (naturalPersonOptional.isPresent()) {
                        NaturalPerson naturalPerson = naturalPersonOptional.get();
                        naturalPerson.setUserName(customer.getUserName());
                        naturalPerson.setPassword(customer.getPassword());
                        customer = naturalPerson;
                    }
                } else {
                    Optional<LegalPerson> legalPersonOptional =
                            legalPersonDAO.read(customer.getCustomerID());
                    if (legalPersonOptional.isPresent()) {
                        LegalPerson legalPerson = legalPersonOptional.get();
                        legalPerson.setUserName(customer.getUserName());
                        legalPerson.setPassword(customer.getPassword());
                        customer = legalPerson;
                    }
                }
                return Optional.of(customer);
            }
            return optionalCustomer;
        } catch (Exception e) {
            log.error("Something went wrong in getCustomerDetails(optionalCustomer)." +
                    " The Optional.of(optionalCustomer) is: " + Optional.of(optionalCustomer) + e);
        }
        return Optional.empty();
    }

    public Optional<Customer> getCustomerByUsername(String username) {
        return getCustomerDetails(customerDAO.read(username));
    }

    //  public Customer createCustomer(long customerId) {
    //    Optional<Customer> optionalCustomer = getCustomerById(customerId);
    //    if(optionalCustomer.isPresent()) {
    //      Customer customer = optionalCustomer.get();
    //      List<Account> customerAccounts = oldAccountDAO.getAccountsForCustomer(customer);
    //      customer.setAccounts(customerAccounts);
    //      return customer;
    //    }
    //    return null;
    //  }

    public List<Customer> getCustomerByAccountId(long accountId) {
        return customerDAO.getCustomerByAccountId(accountId);
    }

    public Optional <Customer> getCustomerByAccountId2(long accountId) {
        return customerDAO.read(accountId);
    }

    public String printNameCustomer(long customerId) {
        // eerst checken welk type klant het is:
                try {
                    Optional<Customer> optionalCustomer = getCustomerById(customerId);
                    // in case of NaturalPerson:
                    if (optionalCustomer.isPresent())
                    if (optionalCustomer.get() instanceof NaturalPerson) {
                        NaturalPerson np = (NaturalPerson) optionalCustomer.get();
                        // afhandeling voorvoegsel:
                        if (np.getPreposition() != null) {
                            return String.format("%s %s %s", np.getFirstName(), np.getPreposition(), np.getSurName());
                        }
                        // bij geen voorvoegsel:
                        return String.format("%s %s", np.getFirstName(), np.getSurName());
                        // in case of LegalPerson:
                    } else {
                        LegalPerson lp = (LegalPerson) optionalCustomer.get();
                        return String.format("%s", lp.getCompanyName());
                    }
                } catch (Exception e) {
                log.error("Er ging iets mis bij printNameCustomer(" + customerId + ").");
                }
                return null;
    }
}
