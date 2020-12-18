package nl.hva.miw.internetbanking.data.mapper;

import nl.hva.miw.internetbanking.model.Employee;
import nl.hva.miw.internetbanking.model.EmployeeRole;
import nl.hva.miw.internetbanking.model.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;

public class TransactionRowMapper implements RowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet rs, int i) throws SQLException {
        return new Transaction(
                rs.getLong("transactionID"),
                rs.getString("debetAccount"),
                rs.getString("creditAccount"),
                rs.getDouble("amount"),
                rs.getString("description"),
                Instant.ofEpochMilli(rs.getDate("date").getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime()
                );
    }
}
