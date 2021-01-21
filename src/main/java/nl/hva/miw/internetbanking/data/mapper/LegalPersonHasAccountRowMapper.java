package nl.hva.miw.internetbanking.data.mapper;

import nl.hva.miw.internetbanking.data.dto.LegalPersonHasAccountDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Employee;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.Sector;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LegalPersonHasAccountRowMapper implements RowMapper<LegalPersonHasAccountDTO> {

    @Override
    public LegalPersonHasAccountDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        LegalPerson lp = new LegalPerson(resultSet.getString("companyName"), resultSet.getLong("kvkNumber"),
                Sector.valueOf(resultSet.getString("sector")), resultSet.getString("address"));
        Employee employee = new Employee(resultSet.getString("firstName"), resultSet.getString("preposition"), resultSet.getString("surName"));
        Account account = new Account(resultSet.getString("iban"), resultSet.getDouble("balance"));
        return new LegalPersonHasAccountDTO(lp, employee, account);
    }
}
