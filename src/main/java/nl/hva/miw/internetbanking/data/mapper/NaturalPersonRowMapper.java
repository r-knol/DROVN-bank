package nl.hva.miw.internetbanking.data.mapper;

import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NaturalPersonRowMapper implements RowMapper<NaturalPerson> {

    @Override
    public NaturalPerson mapRow(ResultSet resultSet, int i) throws SQLException {
        return new NaturalPerson(
                resultSet.getLong("ID"),
                resultSet.getString("initials"),
                resultSet.getString("firstName"),
                resultSet.getString("preposition"),
                resultSet.getString("surName"),
                resultSet.getString("dateOfBirth"),
                resultSet.getString("socialSecurityNumber"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getString("postalCode"),
                resultSet.getString("homeNumber"),
                resultSet.getString("street"),
                resultSet.getString("residence")
        );
    }
}
