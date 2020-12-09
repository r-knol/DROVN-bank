package nl.hva.miw.internetbanking.model;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private long accountID;
    private double balance;
    private String iban;
    private List<Transaction> transactions;

    public Account(long accountID, double balance, String iban) {
        this.accountID = accountID;
        this.balance = balance;
        this.iban = iban;
        this.transactions = new ArrayList<>();
    }

    public long getAccountID() {
        return accountID;
    }

    public void setAccountID(long accountID) {
        this.accountID = accountID;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", balance=" + balance +
                ", iban='" + iban + '\'' +
                ", transactions=" + transactions +
                '}';
    }
}
