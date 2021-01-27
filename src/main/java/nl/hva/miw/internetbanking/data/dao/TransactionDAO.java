package nl.hva.miw.internetbanking.data.dao;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dto.CompanyTransactionDTO;
import nl.hva.miw.internetbanking.data.mapper.CompanyTransactionRowMapper;
import nl.hva.miw.internetbanking.data.mapper.TransactionRowMapper;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Repository
public class TransactionDAO implements DAO<Transaction, Long> {

    private final JdbcTemplate jdbcTemplate;

    public TransactionDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void create(Transaction transaction) {
        String sql = "INSERT INTO transaction (debitAccount, creditAccount, amount, description, " +
                     "dateTime) " +
                     "VALUES(?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"transactionID"});
            ps.setString(1, transaction.getDebitAccount());
            ps.setString(2, transaction.getCreditAccount());
            ps.setDouble(3, transaction.getAmount());
            ps.setString(4, transaction.getDescription());
            ps.setTimestamp(5, Timestamp.valueOf(transaction.getDateTime().plusHours(1)));
            return ps;
        }, keyHolder);
        long id = Objects.requireNonNull(keyHolder.getKey().longValue());
        transaction.setTransactionID(id);
    }

    @Override
    public Optional<Transaction> read(Long transactionID) {
        String sql = "SELECT * FROM transaction WHERE transactionID = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new TransactionRowMapper(), transactionID));
    }

    @Override
    public void update(Transaction transaction) {
        String sql = "UPDATE transaction SET debitAccount = ?, creditAccount = ?, amount = ?" +
                ", description = ?, dateTime = ?" + "WHERE transactionID = ?";
        jdbcTemplate.update(sql, transaction.getDebitAccount(),
                transaction.getCreditAccount(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getDateTime());
    }

    @Override
    public void delete(Transaction transaction) {
        jdbcTemplate.update("DELETE FROM transaction WHERE transactionID = ?",
                transaction.getTransactionID());
    }

    @Override
    public void deleteById(Long transactionID) {
        jdbcTemplate.update("DELETE FROM transaction WHERE transactionID = ?", transactionID);
    }

    @Override
    public Optional<List<Transaction>> list() {
        String sql = "SELECT * FROM transaction";
        return Optional.of(jdbcTemplate.query(sql, new TransactionRowMapper()));
    }

    public List<Transaction> getTransactionsForAccount (Account account) {
        List<Transaction> transactions = getTransactionsByIban(account.getIban());
        for (Transaction t : transactions) {
            t.addTransactionToAccount(account);
            account.addTransaction(t);
        }
        return transactions;
    }

    public List<Transaction> getTransactionsByIban (String iban) {
        final String sql = "SELECT account.accountid, account.iban, " +
                "account.balance, transaction.transactionID, transaction.amount, " +
                "transaction.description,\n" +
                "transaction.dateTime, transaction.creditAccount, transaction.debitAccount " +
                "FROM transaction JOIN account ON account.iban = transaction.creditAccount \n" +
                "OR account.iban = Transaction.debitAccount\n" +
                "WHERE account.iban = ?\n" +
                "ORDER BY transaction.dateTime DESC;";
        return jdbcTemplate.query(sql, new TransactionRowMapper(), iban);
    }

    public Transaction getCreditTransaction (String iban) {
        final String sql = "SELECT * FROM transaction WHERE creditAccount = ?";
        return jdbcTemplate.queryForObject(sql, new TransactionRowMapper(), iban);
    }

    public Transaction getDebitTransaction (String iban) {
        final String sql = "SELECT * FROM transaction WHERE debitAccount =?";
        return  jdbcTemplate.queryForObject(sql, new TransactionRowMapper(), iban);
    }

    public List<CompanyTransactionDTO> getMostActiveClients() {
        final String sql = "SELECT L.companyName, COUNT(T.transactionID) numberOfTransactions, L" +
                ".kvkNumber, \n" +
                "CONCAT(L.street, \" \", L.homeNumber, \", \", L.residence) AS " +
                "address, \n" +
                "E.firstName, E.preposition, E.surName\n" +
                "FROM transaction_has_account T JOIN account A ON T.accountID=A" +
                ".accountID\n" +
                "JOIN customer_has_account C ON C.accountID=T.accountID JOIN " +
                "legalperson L\n" +
                "ON L.companyID=C.customerID JOIN employee E ON E.employeeID=L" +
                ".accountmanagerID\n" +
                "GROUP BY L.companyName\n" +
                "ORDER BY numberOfTransactions DESC LIMIT 10;";
        return jdbcTemplate.query(sql, new CompanyTransactionRowMapper());
    }
}
