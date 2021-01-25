package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.data.dto.BalancePerSectorDTO;
import nl.hva.miw.internetbanking.data.dto.LegalPersonHasAccountDTO;
import nl.hva.miw.internetbanking.data.dto.NaturalPersonHasAccountDTO;
import nl.hva.miw.internetbanking.data.mapper.BalancePerSectorRowMapper;
import nl.hva.miw.internetbanking.data.mapper.LegalPersonHasAccountRowMapper;
import nl.hva.miw.internetbanking.data.mapper.NaturalPersonHasAccountRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BalanceDAO {

    private final JdbcTemplate jdbcTemplate;

    public BalanceDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<NaturalPersonHasAccountDTO> getNaturalAccountsWithHighestBalance() {
        final String sql = "SELECT n.firstName, n.preposition, n.surName, a.iban, a.balance, " +
                "concat(n.street, \" \", n.homeNumber, \", \", n.residence) as " +
                "address,\n" +
                "n.phone, n.email, n.dateOfBirth\n" +
                "FROM customer_has_account cha JOIN customer c ON cha.customerID = c" +
                ".customerID\n" +
                "JOIN account a ON cha.accountID = a.accountID JOIN naturalperson n " +
                "ON\n" +
                "n.customerID = cha.customerID WHERE c.customerType = 'NATURAL'\n" +
                "ORDER BY balance DESC LIMIT 10;";
        return jdbcTemplate.query(sql, new NaturalPersonHasAccountRowMapper());
    }

    public List<LegalPersonHasAccountDTO> getClientsWithHighestBalance() {
        final String sql = "SELECT l.companyName, a.iban, a.balance, l.kvkNumber, l.sector, CONCAT(l.street, \" \", l.homeNumber, \" \", l.residence) AS address,\n" +
                "e.firstName, e.preposition, e.surName\n" +
                "FROM customer_has_account cha JOIN customer c ON cha.customerID = c.customerID\n" +
                "JOIN account a ON cha.accountID = a.accountID JOIN legalperson l ON l.companyID = cha.customerID\n" +
                "JOIN employee e ON e.employeeID=l.accountmanagerID\n" +
                "WHERE c.customerType = 'LEGAL' ORDER BY balance DESC LIMIT 10;";
        return jdbcTemplate.query(sql, new LegalPersonHasAccountRowMapper());
    }

    public List<BalancePerSectorDTO> getAvgBalancePerSector() {
        final String sql = "SELECT l.sector, AVG(a.balance) balance\n" +
                "FROM legalperson l JOIN customer c ON c.customerID=l.companyID \n" +
                "JOIN customer_has_account cha ON cha.customerID=l.companyID \n" +
                "JOIN account a ON a.accountID=cha.accountID\n" +
                "GROUP BY sector\n" +
                "ORDER BY balance DESC;";
        return jdbcTemplate.query(sql, new BalancePerSectorRowMapper());
    }
}
