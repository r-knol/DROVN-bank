package nl.hva.miw.internetbanking.data.mapper;

import nl.hva.miw.internetbanking.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Account(
                resultSet.getLong("accountID"),
                resultSet.getString("iban"),
                resultSet.getDouble("balance"));
    }

}
