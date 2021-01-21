package nl.hva.miw.internetbanking.data.mapper;

import nl.hva.miw.internetbanking.data.dto.CompanyTransactionDTO;
import nl.hva.miw.internetbanking.model.Employee;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyTransactionRowMapper implements RowMapper<CompanyTransactionDTO> {

    @Override
    public CompanyTransactionDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        LegalPerson lp = new LegalPerson(resultSet.getString("companyName"), resultSet.getLong("kvkNumber"),
                resultSet.getString("address"));
        Transaction transaction = new Transaction(resultSet.getLong("numberOfTransactions"));
        Employee employee = new Employee(resultSet.getString("firstName"), resultSet.getString("preposition"), resultSet.getString("surName"));
        return new CompanyTransactionDTO(lp, transaction, employee);
    }
}
