package nl.hva.miw.internetbanking.data.mapper;

import nl.hva.miw.internetbanking.data.dto.BalancePerSectorDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.Sector;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BalancePerSectorRowMapper implements RowMapper<BalancePerSectorDTO> {

    @Override
    public BalancePerSectorDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        LegalPerson legalPerson = new LegalPerson(Sector.valueOf(resultSet.getString("sector")));
        Account account = new Account(resultSet.getDouble("balance"));
        return new BalancePerSectorDTO(legalPerson.getSector(), account);
    }
}
