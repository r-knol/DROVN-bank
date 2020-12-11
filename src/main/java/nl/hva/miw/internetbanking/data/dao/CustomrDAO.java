package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.data.mapper.CustomerRowMapper;
import nl.hva.miw.internetbanking.model.Customer;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public void create(Customer entity) throws NullPointerException, DuplicateKeyException {
        String sql = "INSERT INTO Customer(userName, password) VALUES(?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"customerId"});
            ps.setString(1, entity.getUsername());
            ps.setString(2, entity.getPassword());
            return ps;
        }, keyHolder);
        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        entity.setId(id);
    }

    @Override
    public Optional<Customer> read(Long id) {
        String sql = "SELECT * FROM Customer WHERE customerID = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    new CustomerRowMapper(), id));
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return Optional.empty();
        }
    }

    @Override
    public void update(Customer entity) throws DataAccessException {
        String sql = "UPDATE Customer SET userName = ?, password = ? WHERE customerID = ?";
        jdbcTemplate.update(sql, entity.getUsername(), entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(Long id) throws DataAccessException {
        jdbcTemplate.update("DELETE FROM Customer WHERE customerID = ?", id);
    }

    @Override
    public Optional<List<Customer>> list() throws DataAccessException {
        String sql = "SELECT * FROM NaturalPerson";
        return Optional.of(jdbcTemplate.query(sql, new CustomerRowMapper()));
    }

}
