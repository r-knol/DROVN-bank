package nl.hva.miw.internetbanking.data.mapper;

import nl.hva.miw.internetbanking.data.dto.CompanyTransactionDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyTransactionRowMapper implements RowMapper<CompanyTransactionDTO> {

    @Override
    public CompanyTransactionDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        return new CompanyTransactionDTO(
                resultSet.getString("companyName"),
                resultSet.getLong("numberOfTransactions"),
                resultSet.getLong("kvkNumber"),
                resultSet.getString("address"),
                resultSet.getString("firstName"),
                resultSet.getString("preposition"),
                resultSet.getString("surName"));
    }
}
