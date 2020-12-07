package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.model.Klant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KlantRowMapper implements RowMapper<Klant> { // geeft ResultSet terug, java element

    @Override
    public Klant mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Klant(resultSet.getInt("klantID"),
                resultSet.getString("gebruikersnaam"),
                resultSet.getString("wachtwoord"));
    }
}
