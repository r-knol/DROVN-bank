package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.data.mapper.AccountRowMapper;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccountDAO implements DAO<Account, Long> {

    private final JdbcTemplate jdbcTemplate;

    public AccountDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Account account) {

    }

    @Override
    public Optional<Account> read(Long accountID) {
        return Optional.empty();
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

    public List<Account> getAccountsByCustomerId(long customerID) {
        final String sql = "SELECT customer.customerid, customer.username, account.accountid, " +
                "account.iban, account.balance\n" +
                "FROM customer_has_account JOIN customer ON customer_has_account" +
                ".customerID=customer.customerID JOIN account ON\n" +
                "account.accountID=customer_has_account.accountID WHERE customer.customerID = ?";
        return jdbcTemplate.query(sql, new AccountRowMapper(), customerID);
    }

    public Account getAccountByIban (String iban) {
        final String sql = "SELECT * FROM account WHERE iban = ?";
        return jdbcTemplate.queryForObject(sql, new AccountRowMapper(), iban);
    }

}
