package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

public class EmployeeDAO implements DAO<Employee, long> {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(Employee employee) {
        String sql = "INSERT INTO Employee(ID, role) VALUES(?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, employee.getCustomerID());
            ps.setString(2, employee.getEmployeeRole().getLabel());
            return ps;
        });
    }

    @Override
    public Optional<Employee> read(Long id) {
        return Optional.empty();
    }

    @Override
    public void update(Employee employee) {

    }

    @Override
    public void delete(Employee employee) {

    }

    @Override
    public void deleteById(Employee employee) {

    }

    @Override
    public Optional<List<Employee>> list() {
        return Optional.empty();
    }
}
