package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.data.dto.CustomerAccountDTO;
import nl.hva.miw.internetbanking.data.mapper.AccountRowMapper;
import nl.hva.miw.internetbanking.data.mapper.CustomerRowMapper;
import nl.hva.miw.internetbanking.data.mapper.LegalPersonRowMapper;
import nl.hva.miw.internetbanking.data.mapper.NaturalPersonRowMapper;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcCustomerDAO implements CustomerDAO {

    private JdbcTemplate jdbcTemplate;
    private JdbcAccountDAO jdbcAccountDAO;

    @Autowired
    public JdbcCustomerDAO(JdbcTemplate jdbcTemplate, JdbcAccountDAO jdbcAccountDAO) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcAccountDAO = jdbcAccountDAO;
    }

    @Override //@Richard Knol
    public CustomerAccountDTO getCustomerByUsernameAndPassword(String userName, String password) {
        try {
            final String sql = "SELECT * FROM customer WHERE userName = ? AND password = ?";
            Customer customer = jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), userName, password);
            List<Account> accountList = null;
            if (customer != null) {
                accountList = jdbcAccountDAO.getAccountsByCustomerId(customer.getCustomerID());
            }
            return new CustomerAccountDTO(customer, accountList);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    @Override //todo: deze wordt gebruikt in handleLogin
    public CustomerAccountDTO getCustomersByAccountId(long accountId) {
        final String sql = "SELECT * FROM Account WHERE accountID = ?";
        Account account = jdbcTemplate.queryForObject(sql, new AccountRowMapper(), accountId);
        List<Customer> customerList = getCustomerByAccountId(accountId);
        return new CustomerAccountDTO(account, customerList);
    }

    @Override //@Richard Knol
    public Customer getCustomerById(long id) {
        final String sql = "SELECT * FROM customer WHERE customerID = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), id);
    }

    // onderstaande methode toegevoegd door Nina 09-12-2020
    @Override
    public NaturalPerson getNpByCustomerId(long customerId) {
        final String sql = "SELECT * FROM NaturalPerson WHERE customerID = ?";
        NaturalPerson np = jdbcTemplate.queryForObject(sql, new NaturalPersonRowMapper(), customerId);
//        if (!np.equals(null)) {
            return np;
//        }

        //todo: if Customer result = null
        //throw new ResponseStatusException(NOT_FOUND, "Persoon met id:" + customerId + " kan niet gevonden worden");
    }


    // onderstaande methode toegevoegd door Nina 11-12-2020
    // voor Legal Person:
    @Override
    public LegalPerson getLpByCustomerId(long customerId) {
        final String sql = "SELECT * FROM LegalPerson WHERE companyID = ?";
        LegalPerson lp = jdbcTemplate.queryForObject(sql, new LegalPersonRowMapper(), customerId);
                return lp;
    }

    @Override
    public List<Customer> getCustomerByAccountId(long accountId) {
        final String sql = "SELECT customer.customerid, customer.username, customer.password, account.accountid, account.iban, account.balance\n" +
                "FROM customer_has_account JOIN customer ON customer_has_account.customerID=customer.customerID JOIN account ON\n" +
                "account.accountID=customer_has_account.accountID WHERE account.accountID = ?";
        return jdbcTemplate.query(sql, new CustomerRowMapper(), accountId);
    }

}
