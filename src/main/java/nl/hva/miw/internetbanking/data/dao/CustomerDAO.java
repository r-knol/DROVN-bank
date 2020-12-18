package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.data.mapper.CustomerRowMapper;
import nl.hva.miw.internetbanking.data.mapper.LegalPersonRowMapper;
import nl.hva.miw.internetbanking.data.mapper.NaturalPersonRowMapper;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CustomerDAO implements DAO<Customer, Long> {

    private final JdbcTemplate jdbcTemplate;

    public CustomerDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Customer customer) {
        String sql = "INSERT INTO Customer(userName, password, customerType) VALUES(?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"customerId"});
            ps.setString(1, customer.getUserName());
            ps.setString(2, customer.getPassword());
            ps.setString(3, customer.getCustomerType().name());
            return ps;
        }, keyHolder);
        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        customer.setCustomerID(id);
    }

    @Override
    public Optional<Customer> read(Long customerID) {
        String sql = "SELECT * FROM Customer WHERE customerID = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new CustomerRowMapper(),
                customerID));
    }

    public Optional<Customer> read(String userName) {
        try {
            String sql = "SELECT * FROM Customer WHERE userName = ?";
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new CustomerRowMapper(),
                    userName));
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Customer customer) {
        String sql = "UPDATE Customer SET userName = ?, password = ? WHERE customerID = ?";
        jdbcTemplate.update(sql, customer.getUserName(), customer.getPassword(),
                customer.getCustomerID());
    }

    @Override
    public void delete(Customer customer) {
        jdbcTemplate.update("DELETE FROM Customer WHERE customerID = ?", customer.getCustomerID());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM Customer WHERE customerID = ?", id);
    }

    @Override
    public Optional<List<Customer>> list() {
        String sql = "SELECT * FROM Customer";
        return Optional.of(jdbcTemplate.query(sql, new CustomerRowMapper()));
    }

    public NaturalPerson getNpByCustomerId(long customerId) {
        final String sql = "SELECT * FROM NaturalPerson WHERE customerID = ?";
        NaturalPerson np = jdbcTemplate.queryForObject(sql, new NaturalPersonRowMapper(),
                customerId);
        return np;
    }

    public LegalPerson getLpByCustomerId(long customerId) {
        final String sql = "SELECT * FROM LegalPerson WHERE companyID = ?";
        LegalPerson lp = jdbcTemplate.queryForObject(sql, new LegalPersonRowMapper(), customerId);
        return lp;
    }

    public List<Customer> getCustomerByAccountId(long accountId) {
        final String sql = "SELECT customer.customerid, customer.username, customer.password, " +
                "customer.customerType, account.accountid, account.iban, account.balance\n" +
                "FROM customer_has_account JOIN customer ON customer_has_account" +
                ".customerID=customer.customerID JOIN account ON\n" +
                "account.accountID=customer_has_account.accountID WHERE account.accountID = ?";
        return jdbcTemplate.query(sql, new CustomerRowMapper(), accountId);
    }

}
