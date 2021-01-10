package nl.hva.miw.internetbanking.service;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.data.dao.CustomerDAO;
import nl.hva.miw.internetbanking.data.dao.LegalPersonDAO;
import nl.hva.miw.internetbanking.data.dao.NaturalPersonDAO;
import nl.hva.miw.internetbanking.data.dto.BalancePerSectorDTO;
import nl.hva.miw.internetbanking.data.dto.CompanyTransactionDTO;
import nl.hva.miw.internetbanking.data.dto.DTO;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.CustomerType;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import nl.hva.miw.internetbanking.data.dto.*;
import nl.hva.miw.internetbanking.model.*;
import nl.hva.miw.internetbanking.util.DtoMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional
    public <T extends Customer> void saveCustomer(T entity) {
        try {
            customerDAO.create(entity);
            if (entity instanceof NaturalPerson) {
                naturalPersonDAO.create((NaturalPerson) entity);
            } else if (entity instanceof LegalPerson) {
                legalPersonDAO.create((LegalPerson) entity);
            }
        } catch (DataAccessException e) {
            log.warn("Exception occurred while attempting to save customer: {}", e.getMessage());
        }
        customerDAO.create(entity);
        if (entity instanceof NaturalPerson) {
            naturalPersonDAO.create((NaturalPerson) entity);
        } else if (entity instanceof LegalPerson) {
            legalPersonDAO.create((LegalPerson) entity);
        }
    }

    public List<NaturalPersonHasAccountDTO> getNaturalAccountsWithHighestBalance() {
        return naturalPersonDAO.getNaturalAccountsWithHighestBalance();
    }

    public List<LegalPersonHasAccountDTO> getClientsWithHighestBalance() {
        return legalPersonDAO.getClientsWithHighestBalance();
    }

    public List<CompanyTransactionDTO> getMostActiveClients() {
        return legalPersonDAO.getMostActiveClients();
    }

    public List<BalancePerSectorDTO> getAvgBalancePerSegment() {
        return legalPersonDAO.getAvgBalancePerSegment();
    }

    public Optional<Customer> getCustomerByUsername(String username) {
        try {
            Optional<Customer> customerOptional = customerDAO.read(username);
            return getCustomerDetails(customerOptional);
        } catch (DataAccessException e) {
            log.warn("Exception occurred while attempting to read customer data: {}",
                     e.getMessage());
            return Optional.empty();
        }
    }

    private Optional<Customer> getCustomerDetails(Optional<Customer> optionalCustomer)
            throws DataAccessException {
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            if (customer.getCustomerType().equals(CustomerType.NATURAL)) {
                return getPrivateCustomerDetails(customer);
            }
            if (customer.getCustomerType().equals(CustomerType.LEGAL)) {
                return getBusinessCustomerDetails(customer);
            }
        }
        return Optional.empty();
    }

    // TODO: Create CustomerConverter ???

    public Optional<Customer> getPrivateCustomerDetails(Customer customer)
            throws DataAccessException {
        Optional<NaturalPerson> naturalPersonOptional =
                naturalPersonDAO.read(customer.getCustomerID());
        if (naturalPersonDAO.read(customer.getCustomerID()).isPresent()) {
            NaturalPerson naturalPerson = naturalPersonOptional.get();
            naturalPerson.setUserName(customer.getUserName());
            naturalPerson.setPassword(customer.getPassword());
            customer = naturalPerson;
            return Optional.ofNullable(customer);
        }
        return Optional.empty();
    }

    public Optional<Customer> getBusinessCustomerDetails(Customer customer)
            throws DataAccessException {
        Optional<LegalPerson> legalPersonOptional =
                legalPersonDAO.read(customer.getCustomerID());
        if (legalPersonOptional.isPresent()) {
            LegalPerson legalPerson = legalPersonOptional.get();
            legalPerson.setUserName(customer.getUserName());
            legalPerson.setPassword(customer.getPassword());
            customer = legalPerson;
            return Optional.ofNullable(customer);
        }
        return Optional.empty();
    }

    // TODO: Refactor?
    public List<Customer> getCustomerByAccountId(long accountId) {
        try {
            return customerDAO.getCustomerByAccountId(accountId);
        } catch (DataAccessException e) {
            log.warn("Exception occurred while attempting to read customer data: {}",
                     e.getMessage());
        }
        return new ArrayList<>();
    }

    // TODO: Refactor?
    public Optional<Customer> getCustomerByAccountId2(long accountId) {
        try {
            return customerDAO.read(accountId);
        } catch (DataAccessException e) {
            log.warn("Exception occurred while attempting to read customer data: {}",
                     e.getMessage());
            return Optional.empty();
        }
    }

    // TODO: Get name from CustomerController (RESTController)?
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
                        return String.format("%s %s %s", np.getFirstName(), np.getPreposition(),
                                             np.getSurName());
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

    public Optional<Customer> getCustomerById(long customerID) {
        try {
            return getCustomerDetails(customerDAO.read(customerID));
        } catch (DataAccessException e) {
            log.warn("Exception occurred while attempting to read customer data: {}",
                     e.getMessage());
            return Optional.empty();
        }
    }

    // TODO: Catch DataAccessException?
    public List<BalancePerSectorDTO> getAvgBalancePerSegment() {
        return legalPersonDAO.getAvgBalancePerSegment();
    }

    // TODO: Catch DataAccessException?
    public List<CompanyTransactionDTO> getMostActiveClients() {
        return legalPersonDAO.getMostActiveClients();
    }

}
