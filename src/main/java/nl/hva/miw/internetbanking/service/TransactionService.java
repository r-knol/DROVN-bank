package nl.hva.miw.internetbanking.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.data.dao.TransactionDAO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TransactionService {

    @Getter
    @Setter
    private TransactionDAO transactionDAO;
    private AccountDAO accountDAO;

    @Autowired
    public TransactionService (TransactionDAO transactionDAO, AccountDAO accountDAO) {
        this.transactionDAO = transactionDAO;
        this.accountDAO = accountDAO;
    }

    public List<Transaction> getTransactionsForAccount (Account account) {
        return transactionDAO.getTransactionsForAccount(account);
    }

    private Optional<Transaction> getTransactionDetails (Optional<Transaction> optionalTransaction) {
        if(optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transactionDAO.read(transaction.getTransactionID());
            return Optional.of(transaction);
        }
        return optionalTransaction;
    }

    public Optional <Transaction> getTransactionById (long transactionID) {
        return getTransactionDetails(transactionDAO.read(transactionID));
    }

    public Transaction getCreditTransaction (String iban) {
        return transactionDAO.getCreditTransaction(iban);
    }

    public Transaction getDebitTransaction (String iban) {
        return transactionDAO.getDebitTransaction(iban);
    }

    public void doTransaction(Account fromAccount, Account toAccount) {
//        Transaction transaction = new Transaction();
//        transactionDAO.create(transaction);
    }
}
