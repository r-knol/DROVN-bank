package nl.hva.miw.internetbanking.data.mapper;

import nl.hva.miw.internetbanking.model.LegalPerson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LegalPersonRowMapper implements RowMapper<LegalPerson> {

    @Override
    public LegalPerson mapRow(ResultSet resultSet, int i) throws SQLException {
        return new LegalPerson(
                resultSet.getLong("companyID"),
                resultSet.getString("companyName"),
                resultSet.getLong("kvkNumber"),
                resultSet.getString("sector"),
                resultSet.getString("vatNumber"),
                resultSet.getString("postalCode"),
                resultSet.getString("homeNumber"),
                resultSet.getString("street"),
                resultSet.getString("residence"),
                resultSet.getLong("accountmanagerID"));
    }
}
