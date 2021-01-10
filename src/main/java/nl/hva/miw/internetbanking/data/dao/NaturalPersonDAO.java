package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.data.dto.NaturalPersonHasAccountDTO;
import nl.hva.miw.internetbanking.data.mapper.AccountRowMapper;
import nl.hva.miw.internetbanking.data.mapper.NaturalPersonHasAccountRowMapper;
import nl.hva.miw.internetbanking.data.mapper.NaturalPersonRowMapper;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class NaturalPersonDAO implements DAO<NaturalPerson, Long> {

    private final JdbcTemplate jdbcTemplate;

    public NaturalPersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(NaturalPerson naturalPerson) {
        String sql = "INSERT INTO NaturalPerson(customerID, initials, firstName, preposition, " +
                "surName, dateOfBirth, socialSecurityNumber, email, phone, postalCode, " +
                "homeNumber, street, residence) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, naturalPerson.getCustomerID());
            ps.setString(2, naturalPerson.getInitials());
            ps.setString(3, naturalPerson.getFirstName());
            ps.setString(4, naturalPerson.getPreposition());
            ps.setString(5, naturalPerson.getSurName());
            ps.setString(6, naturalPerson.getDateOfBirth());
            ps.setString(7, naturalPerson.getSocialSecurityNumber());
            ps.setString(8, naturalPerson.getEmail());
            ps.setString(9, naturalPerson.getPhone());
            ps.setString(10, naturalPerson.getPostalCode());
            ps.setString(11, naturalPerson.getHomeNumber());
            ps.setString(12, naturalPerson.getStreet());
            ps.setString(13, naturalPerson.getResidence());
            return ps;
        });
    }

    public List<NaturalPersonHasAccountDTO> getNaturalAccountsWithHighestBalance() {
        final String sql = "SELECT n.firstName, n.preposition, n.surname, a.iban, a.balance, concat(n.street, \" \", n.homenumber, \", \", n.residence) as address,\n" +
                "n.phone, n.email, n.dateOfBirth\n" +
                "FROM customer_has_account cha JOIN customer c ON cha.customerID = c.customerID\n" +
                "JOIN account a ON cha.accountID = a.accountID JOIN naturalperson n ON\n" +
                "n.customerID = cha.customerID WHERE c.customerType = 'NATURAL'\n" +
                "ORDER BY balance DESC LIMIT 10;";
        return jdbcTemplate.query(sql, new NaturalPersonHasAccountRowMapper());
    }

    @Override
    public Optional<NaturalPerson> read(Long id) {
        String sql = "SELECT * FROM NaturalPerson WHERE customerID = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new NaturalPersonRowMapper(),
                id));
    }

    @Override
    public void update(NaturalPerson naturalPerson) {
        String sql = "UPDATE NaturalPerson SET initials = ?, firstName = ?, preposition = ?" +
                ", surName = ?, dateOfBirth = ?, socialSecurityNumber = ?, email = ?" +
                ", phone = ?, postalCode = ?, homeNumber = ?, street = ?, residence = ? " +
                "WHERE customerID = ?";
        jdbcTemplate.update(sql, naturalPerson.getInitials(), naturalPerson.getFirstName(),
                naturalPerson.getPreposition(), naturalPerson.getSurName(),
                naturalPerson.getDateOfBirth(),
                naturalPerson.getSocialSecurityNumber(), naturalPerson.getEmail(),
                naturalPerson.getPhone(),
                naturalPerson.getPostalCode(), naturalPerson.getHomeNumber(),
                naturalPerson.getStreet(),
                naturalPerson.getResidence(), naturalPerson.getCustomerID());
    }

    @Override
    public void delete(NaturalPerson naturalPerson) {
        jdbcTemplate.update("DELETE FROM NaturalPerson WHERE customerID = ?",
                naturalPerson.getCustomerID());
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM NaturalPerson WHERE customerID = ?", id);
    }

    @Override
    public Optional<List<NaturalPerson>> list() {
        String sql = "SELECT * FROM NaturalPerson";
        return Optional.of(jdbcTemplate.query(sql, new NaturalPersonRowMapper()));
    }

    public boolean existsBySocialSecurityNumber(String socialSecurityNumber) {
        final String sql = "SELECT * FROM NaturalPerson WHERE socialSecurityNumber = ?";
        return jdbcTemplate.queryForObject(sql, new NaturalPersonRowMapper(),
                                           socialSecurityNumber) != null;
    }

}
