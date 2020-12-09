package nl.hva.miw.internetbanking.repository;

import nl.hva.miw.internetbanking.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCustomerDAO implements CustomerDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCustomerDAO(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Customer getCustomerByUsername(String userName) {
        final String sql = "SELECT * FROM customer WHERE userName = ?";
        return jdbcTemplate.queryForObject(sql, new CustomerRowMapper(), userName); //todo: EmptyResultDataAccessException
    }
}
