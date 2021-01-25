package nl.hva.miw.internetbanking.data.dto;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Transaction;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.yaml.snakeyaml.tokens.Token.ID.Key;

public class AccountHasTransactionsDTO {

    private Account account;
    private Transaction transaction;
    private List<Transaction> transactionList;
    private Map<Long, List<Transaction>> transactionMap;
    private Map<String, List<Transaction>> transactionListByDate;

    public AccountHasTransactionsDTO(Account account) {
        super();
        this.account = account;
        this.transactionMap = new HashMap<>();
        this.transactionListByDate = new HashMap<>();
    }

    public AccountHasTransactionsDTO() {
        super();
        this.transactionMap = new HashMap<>();
        this.transactionListByDate = new HashMap<>();
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

    public Map<Long, List<Transaction>> getTransactionMap() {
        return transactionMap;
    }

    public void setTransactionMap(Map<Long, List<Transaction>> transactionMap) {
        this.transactionMap = transactionMap;
    }

    public Map<String, List<Transaction>> getTransactionListByDate() {
        return transactionListByDate;
    }

    public void setTransactionListByDate(Map<String, List<Transaction>> transactionListByDate) {
        this.transactionListByDate = transactionListByDate;
    }

    @Override
    public String toString() {
        return "AccountHasTransactionsDTO{" +
                "account=" + account +
                ", transactionListByDate=" + transactionListByDate +
                ", transactionList=" + transactionList +
                '}';
    }

    public <R, T, K> void setTransactionListByDate(R collect, Collector<T,?, Map<K, List<T>>> groupingBy) {
    }
}
