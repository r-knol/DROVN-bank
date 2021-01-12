package nl.hva.miw.internetbanking.data.mapper;

import nl.hva.miw.internetbanking.data.dto.AccountHasTransactionDTO;
import nl.hva.miw.internetbanking.data.dto.BalancePerSectorDTO;
import nl.hva.miw.internetbanking.data.dto.CompanyTransactionDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.Sector;
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
                resultSet.getString("accountmanager"));
    }
}
