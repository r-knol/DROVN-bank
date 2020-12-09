package nl.hva.miw.internetbanking.repository;

import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NaturalPersonRowMapper implements RowMapper<NaturalPerson> {

    @Override
    public NaturalPerson mapRow(ResultSet resultSet, int i) throws SQLException {
        return new NaturalPerson(
                resultSet.getString("initials"),
                resultSet.getString("firstName"),
                resultSet.getString("preposition"),
                resultSet.getString("surName"));
    }
}
