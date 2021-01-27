package nl.hva.miw.internetbanking.data.dto;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Transaction;

import java.time.LocalDate;
import java.util.*;

public class AccountHasTransactionsDTO {

    private Account account;
    private Transaction transaction;
    private List<Transaction> transactionList;
    private Map<LocalDate, List<Transaction>> transactionMapByDate;

    public AccountHasTransactionsDTO(Account account) {
        super();
        this.account = account;
        this.transactionMapByDate = new HashMap<>();
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public Map<LocalDate, List<Transaction>> getTransactionMapByDate() {
        return transactionMapByDate;
    }

    public void setTransactionMapByDate(Map<LocalDate, List<Transaction>> transactionMapByDate) {
        this.transactionMapByDate = transactionMapByDate;
    }

    @Override
    public String toString() {
        return "AccountHasTransactionsDTO{" +
                "account=" + account +
                ", transactionListByDate=" + transactionMapByDate +
                ", transactionList=" + transactionList +
                '}';
    }
}
