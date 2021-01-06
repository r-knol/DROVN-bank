package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.data.mapper.AccountRowMapper;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Repository
public class AccountDAO implements DAO<Account, Long> {

    private final JdbcTemplate jdbcTemplate;
    Logger logger = LoggerFactory.getLogger(AccountDAO.class);

    public AccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Account account) {
        String sql = "INSERT INTO Account(iban, balance) VALUES (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"accountID"});
            ps.setString(1, account.getIban());
            ps.setDouble(2,account.getBalance());
            return ps;
        }, keyHolder);
        long id = Objects.requireNonNull(keyHolder.getKey().longValue());
        account.setAccountID(id);
    }

    @Override
    public Optional<Account> read(Long accountID) {
        try {
            String sql = "SELECT * FROM Account WHERE accountID = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new AccountRowMapper(), accountID));
        } catch (EmptyResultDataAccessException e) {
            logger.info("No record found for account " + accountID, e);
            return Optional.empty();
        }
    }

    public Optional <Account> read(String iban) {
        try {
            String sql = "SELECT * FROM Account WHERE iban = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new AccountRowMapper(), iban));
        } catch (EmptyResultDataAccessException e) {
            logger.info("No record found for account " + iban, e);
            return Optional.empty();
        }
    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void delete(Account account) {

    }

    @Override
    public void deleteById(Long accountID) {

    }

    @Override
    public Optional<List<Account>> list() {
        return Optional.empty();
    }

    public List<Account> getAccountsForCustomer(Customer customer) {
        List<Account> accounts = getAccountsByCustomerId(customer.getCustomerID());
        for (Account acc : accounts) {
            acc.addAccountHolder(customer);
            customer.addAccount(acc);
        }
        return accounts;
    }

    public List<Account> getAllNaturalAccounts() {
        final String sql = "SELECT customer.customerID, naturalperson.firstName, naturalperson.preposition, naturalperson.surName,\n" +
                "customer.customerType, account.accountID, account.iban, account.balance\n" +
                "FROM customer_has_account JOIN customer ON customer_has_account.customerID = customer.customerID\n" +
                "JOIN account ON customer_has_account.accountID = account.accountID JOIN naturalperson ON \n" +
                "naturalperson.customerID = customer_has_account.customerID\n" +
                "WHERE customer.customerType = 'NATURAL' ORDER BY balance DESC LIMIT 10";
        return jdbcTemplate.query(sql, new AccountRowMapper());
    }

    public List<Account> getAllLegalAccounts() {
        final String sql = "SELECT customer.customerID, legalperson.companyID, legalperson.companyName,\n" +
                "customer.customerType, account.accountID, account.iban, account.balance\n" +
                "FROM customer_has_account JOIN customer ON customer_has_account.customerID = customer.customerID\n" +
                "JOIN account ON customer_has_account.accountID = account.accountID JOIN legalperson ON \n" +
                "legalperson.companyID = customer_has_account.customerID\n" +
                "WHERE customer.customerType = 'LEGAL' ORDER BY balance DESC LIMIT 10;";
        return jdbcTemplate.query(sql, new AccountRowMapper());
    }

    public List<Account> getAccountsByCustomerId(long customerID) {
        final String sql = "SELECT customer.customerid, customer.username, account.accountid, " +
                "account.iban, account.balance\n" +
                "FROM customer_has_account JOIN customer ON customer_has_account" +
                ".customerID=customer.customerID JOIN account ON\n" +
                "account.accountID=customer_has_account.accountID WHERE customer.customerID = ?";
        return jdbcTemplate.query(sql, new AccountRowMapper(), customerID);
    }

}
