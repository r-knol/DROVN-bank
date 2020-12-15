package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.data.mapper.CustomerRowMapper;
import nl.hva.miw.internetbanking.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class CustomrDAO implements DAO<Customer, Long> {

    private final JdbcTemplate jdbcTemplate;

    public CustomrDAO(JdbcTemplate jdbcTemplate) {
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
    public Optional<Customer> read(Long id) {
        String sql = "SELECT * FROM Customer WHERE customerID = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), id));
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

}
