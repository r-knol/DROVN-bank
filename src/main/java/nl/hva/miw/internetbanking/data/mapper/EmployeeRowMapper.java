package nl.hva.miw.internetbanking.data.mapper;

import nl.hva.miw.internetbanking.model.Employee;
import nl.hva.miw.internetbanking.model.EmployeeRole;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Employee(
                resultSet.getLong("employeeID"),
                resultSet.getString("userName"),
                resultSet.getString("password"),
                resultSet.getString("firstName"),
                resultSet.getString("preposition"),
                resultSet.getString("surName"),
                EmployeeRole.valueOf(resultSet.getString("role")
        ));
    }
}
