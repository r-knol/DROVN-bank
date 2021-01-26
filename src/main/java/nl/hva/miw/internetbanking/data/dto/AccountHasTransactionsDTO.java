package nl.hva.miw.internetbanking.data.dto;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Transaction;

import java.time.LocalDate;
import java.util.*;

public class AccountHasTransactionsDTO {

    private Account account;
    private Transaction transaction;
    private List<Transaction> transactionList;
//    private Map<Long, List<Transaction>> transactionMap;
    private Map<LocalDate, List<Transaction>> transactionMapByDate;

    public AccountHasTransactionsDTO(Account account) {
        super();
        this.account = account;
//        this.transactionMap = new HashMap<>();
        this.transactionMapByDate = new HashMap<>();
    }

    public AccountHasTransactionsDTO() {
        super();
//        this.transactionMap = new HashMap<>();
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

//    public Map<Long, List<Transaction>> getTransactionMap() {
//        return transactionMap;
//    }

//    public void setTransactionMap(Map<Long, List<Transaction>> transactionMap) {
//        this.transactionMap = transactionMap;
//    }

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
