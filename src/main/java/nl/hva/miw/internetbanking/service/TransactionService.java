package nl.hva.miw.internetbanking.service;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dao.TransactionDAO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TransactionService {

    private TransactionDAO transactionDAO;

    @Autowired
    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
        log.warn("New TransactionService.");
    }

    public void doTransaction(Account fromAccount, Account toAccount) {
//        Transaction transaction = new Transaction();
//        transactionDAO.create(transaction);
    }
}
