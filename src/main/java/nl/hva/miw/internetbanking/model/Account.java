package nl.hva.miw.internetbanking.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private long accountId;
    private double balance;
    private String IBAN;
    private List<Transaction> transactionHistory;

    public Account(long accountId, double balance, String IBAN, List<Transaction> transactionHistory) {
        this.accountId = accountId;
        this.balance = balance;
        this.IBAN = IBAN;
        this.transactionHistory = transactionHistory;
    }

    public Account() {
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<Transaction> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", IBAN='" + IBAN + '\'' +
                ", transactionHistory=" + transactionHistory +
                '}';
    }
}