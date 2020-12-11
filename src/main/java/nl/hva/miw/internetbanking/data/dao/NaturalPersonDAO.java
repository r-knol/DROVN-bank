package nl.hva.miw.internetbanking.data.dao;

import lombok.AllArgsConstructor;
import nl.hva.miw.internetbanking.data.mapper.NaturalPersonRowMapper;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class NaturalPersonDAO implements DAO<NaturalPerson, Long> {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void create(NaturalPerson entity) throws NullPointerException, DuplicateKeyException {
        String sql = "INSERT INTO NaturalPerson(customerID, initials, firstName, preposition, " +
                "surName, dateOfBirth, socialSecurityNumber, email, phone, postalCode, " +
                "homeNumber, street, residence) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql); //, new String[]{"customerId
            // "});
            ps.setLong(1, entity.getId());
            ps.setString(2, entity.getInitials());
            ps.setString(3, entity.getFirstName());
            ps.setString(4, entity.getPreposition());
            ps.setString(5, entity.getSurName());
            ps.setString(6, entity.getDateOfBirth());
            ps.setLong(7, entity.getSocialSecurityNumber());
            ps.setString(8, entity.getEmail());
            ps.setLong(9, entity.getPhone());
            ps.setString(10, entity.getPostalCode());
            ps.setString(11, entity.getHomeNumber());
            ps.setString(12, entity.getStreet());
            ps.setString(13, entity.getResidence());
            return ps;
        }); //, keyHolder);
    }

    @Override
    public Optional<NaturalPerson> read(Long id) {
        String sql = "SELECT * FROM NaturalPerson WHERE customerID = ?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,
                    new NaturalPersonRowMapper(), id)); // found
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {
            return Optional.empty(); // not found
        }
    }

    @Override
    public void update(NaturalPerson entity) throws DataAccessException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("UPDATE NaturalPerson SET initials = ?, firstName = ?, preposition = ?")
                .append(", surName = ?, dateOfBirth = ?, socialSecurityNumber = ?, email = ?")
                .append(", phone = ?, postalCode = ?, homeNumber = ?, street = ?, residence = ? ")
                .append("WHERE customerID = ")
                .append(entity.getId());
        String sql = stringBuilder.toString();
        jdbcTemplate.update(sql, entity.getInitials(), entity.getFirstName(),
                entity.getPreposition(), entity.getSurName(), entity.getDateOfBirth(),
                entity.getSocialSecurityNumber(), entity.getEmail(), entity.getPhone(),
                entity.getPostalCode(), entity.getHomeNumber(), entity.getStreet(),
                entity.getResidence());
    }

    @Override
    public void delete(Long id) throws DataAccessException {
        jdbcTemplate.update("DELETE FROM NaturalPerson WHERE customerID = " + id);
    }

    @Override
    public Optional<List<NaturalPerson>> list() throws DataAccessException {
        String sql = "SELECT * FROM NaturalPerson";
        return Optional.of(jdbcTemplate.query(sql, new NaturalPersonRowMapper()));
    }

}
