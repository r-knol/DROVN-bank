package nl.hva.miw.internetbanking.data.mapper;

import nl.hva.miw.internetbanking.data.dto.NaturalPersonHasAccountDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NaturalPersonHasAccountRowMapper implements RowMapper<NaturalPersonHasAccountDTO> {

    @Override
    public NaturalPersonHasAccountDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        return new NaturalPersonHasAccountDTO(
                resultSet.getString("firstName"),
                resultSet.getString("preposition"),
                resultSet.getString("surName"),
                resultSet.getString("iban"),
                resultSet.getDouble("balance"),
                resultSet.getString("address"),
                resultSet.getString("phone"),
                resultSet.getString("email"),
                resultSet.getString("dateOfBirth"));
    }
}
