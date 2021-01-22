package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDAOTest extends JDBCSetupDAOTest {

    @Autowired
    public TransactionDAOTest(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Test
    void createAndReadTest() {
        LocalDateTime testLocalTime = LocalDateTime.of(2021, 01, 01, 01, 20, 00);
        TransactionDAO transactionDAO = new TransactionDAO(jdbcTemplate);
        Transaction testTransaction = new Transaction("NL47DROVN687253648", "NL97DROVN527874997",
                9.99, "test transactie", testLocalTime);
        transactionDAO.create(testTransaction);
        Optional<Transaction> optionalT = transactionDAO.read(testTransaction.getTransactionID());
        Transaction actual = optionalT.get();
        System.out.println(actual);
        assertEquals(testTransaction, actual);
    }
}