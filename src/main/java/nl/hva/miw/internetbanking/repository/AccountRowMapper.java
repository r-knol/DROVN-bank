package nl.hva.miw.internetbanking.repository;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// aangemaakt door Nina 09-12-2020

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Account(resultSet.getLong("accountID"),
                resultSet.getDouble("balance"),
                resultSet.getString("iban"));
    }

}
