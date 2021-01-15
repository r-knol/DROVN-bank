package nl.hva.miw.internetbanking.data.mapper;

import nl.hva.miw.internetbanking.data.dto.LegalPersonHasAccountDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LegalPersonHasAccountRowMapper implements RowMapper<LegalPersonHasAccountDTO> {

    @Override
    public LegalPersonHasAccountDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        return new LegalPersonHasAccountDTO(
                resultSet.getString("companyName"),
                resultSet.getString("iban"),
                resultSet.getDouble("balance"),
                resultSet.getLong("kvkNumber"),
                resultSet.getString("sector"),
                resultSet.getString("address"),
                resultSet.getString("firstName"),
                resultSet.getString("preposition"),
                resultSet.getString("surName"));
    }
}
