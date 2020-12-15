package nl.hva.miw.internetbanking.data.mapper;

import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.CustomerType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Customer(
                resultSet.getLong("customerID"),
                resultSet.getString("userName"),
                resultSet.getString("password"),
                CustomerType.valueOf(resultSet.getString("customerType"))
        );
    }

}
