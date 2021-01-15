package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.data.mapper.EmployeeRowMapper;
import nl.hva.miw.internetbanking.model.Employee;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class EmployeeDAO implements DAO<Employee, Long> {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void create(Employee employee) {
        String sql = "INSERT INTO employee(employeeID, userName, password, firstName, " +
                     "preposition, surName, role) " +
                     "VALUES(?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"employeeId"});
            ps.setLong(1, employee.getEmployeeID());
            ps.setString(1, employee.getUserName());
            ps.setString(2, employee.getPassword());
            ps.setString(3, employee.getFirstName());
            ps.setString(4, employee.getPreposition());
            ps.setString(5, employee.getSurName());
            ps.setString(2, employee.getEmployeeRole().getLabel());
            return ps;
        }, keyHolder);
        int id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        employee.setEmployeeID(id);
    }

    @Override
    public Optional<Employee> read(Long id) {
        String sql = "SELECT * FROM employee WHERE employeeID = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), id));
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employee SET role = ? WHERE employeeID = ?";
        jdbcTemplate.update(sql, employee.getEmployeeRole().getLabel(), employee.getEmployeeID());
    }

    @Override
    public void delete(Employee employee) {
        jdbcTemplate.update("DELETE FROM employee WHERE employeeID = ?",
                            employee.getEmployeeID());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM employee WHERE employeeID = ?", id);
    }

    @Override
    public Optional<List<Employee>> list() {
        String sql = "SELECT * FROM employee";
        return Optional.of(jdbcTemplate.query(sql, new EmployeeRowMapper()));
    }

    public Optional<Employee> read(String userName) {
        try {
            String sql = "SELECT * FROM employee WHERE userName = ?";
            return Optional.ofNullable(
                    jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), userName));
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return Optional.empty();
        }
    }

}
