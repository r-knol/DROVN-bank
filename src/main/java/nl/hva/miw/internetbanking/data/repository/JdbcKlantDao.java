package nl.hva.miw.internetbanking.data.repository;

import nl.hva.miw.internetbanking.data.dao.KlantDao;
import nl.hva.miw.internetbanking.data.dao.KlantRowMapper;
import nl.hva.miw.internetbanking.model.Klant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcKlantDao implements KlantDao { // Implementatie van de interface KlantDao

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcKlantDao(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    public Klant getKlantByGebruikersnaam(String gebruikersnaam) { // Haalt data op uit MySQL
        final String sql = "SELECT * FROM klant WHERE gebruikersnaam = ?";
        return jdbcTemplate.queryForObject(sql, new KlantRowMapper(), gebruikersnaam);
    }
}
