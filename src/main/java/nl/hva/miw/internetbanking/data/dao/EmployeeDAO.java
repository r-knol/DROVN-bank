package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.data.mapper.EmployeeRowMapper;
import nl.hva.miw.internetbanking.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDAO implements DAO<Employee, Long> {

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
        String sql = "SELECT * FROM Employee WHERE ID = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), id));
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE Employee SET role = ? WHERE employeeID = ?";
        jdbcTemplate.update(sql, employee.getEmployeeRole().getLabel(), employee.getCustomerID());
    }

    @Override
    public void delete(Employee employee) {
        jdbcTemplate.update("DELETE FROM Employee WHERE employeeID = ?",
                employee.getCustomerID());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM Employee WHERE employeeID = ?", id);
    }

    @Override
    public Optional<List<Employee>> list() {
        String sql = "SELECT * FROM Employee";
        return Optional.of(jdbcTemplate.query(sql, new EmployeeRowMapper()));
    }
}
