package nl.hva.miw.internetbanking.data.dao;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.mapper.TransactionRowMapper;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
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
        String sql = "INSERT INTO TRANSACTION (debitAccount, creditAccount, amount, description, dateTime) " +
                "VALUES(?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        System.out.println("!!!!!!!!!!!!! transaction = " + transaction);
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"transactionID"});
            ps.setString(1, transaction.getDebitAccount());
            ps.setString(2, transaction.getCreditAccount());
            ps.setDouble(3, transaction.getAmount());
            ps.setString(4, transaction.getDescription());
            ps.setTimestamp(5, Timestamp.valueOf(transaction.getDate()));
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
                ", description = ?, date = ?" + "WHERE transactionID = ?";
        jdbcTemplate.update(sql, transaction.getDebitAccount(),
                transaction.getCreditAccount(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getDate());
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
        List<Transaction> transactions = getTransactionsByAccountId(account.getAccountID());
        System.out.println(account.getAccountID());
        for (Transaction t : transactions) {
            t.addTransactionToAccount(account);
            account.addTransaction(t);
        }
        System.out.println(transactions);
        return transactions;
    }

    public List<Transaction> getTransactionsByAccountId (long accountID) {
        final String sql = "SELECT account.accountid, account.iban, account.balance, transaction.transactionID, transaction.amount, transaction.description, \n" +
                    "transaction.dateTime, transaction.creditAccount, transaction.debitAccount FROM transaction_has_account JOIN Transaction ON \n" +
                    "Transaction_has_account.transactionID=transaction.transactionID JOIN account ON account.accountID=transaction_has_account.accountID \n" +
                    "WHERE account.accountID = ?";
        return jdbcTemplate.query(sql, new TransactionRowMapper(), accountID);
    }

    public Transaction getCreditTransaction (String iban) {
        final String sql = "SELECT * FROM transaction WHERE creditAccount = ?";
        return jdbcTemplate.queryForObject(sql, new TransactionRowMapper(), iban);
    }

    public Transaction getDebitTransaction (String iban) {
        final String sql = "SELECT * FROM transaction WHERE debitAccount =?";
        return  jdbcTemplate.queryForObject(sql, new TransactionRowMapper(), iban);
    }
}
