package nl.hva.miw.internetbanking.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Account implements Serializable {

    private static final long serialVersionUID = 8922473667746953263L;

    private long accountID;
    private String iban;
    private double balance;
    private List<Transaction> transactions;
    private List<Customer> accountHolders;
    private List<String> accountHolderNames;
    private Map<String, List<Transaction>> transactionListByDate;

    public Account(String iban) {
        this.iban = iban;
    }

    public List<Customer> getAccountHolders() {
        return accountHolders;
    }

  public void setAccountHolders(List<Customer> accountHolders) {
    this.accountHolders = accountHolders;
  }

  public void addAccountHolder(Customer customer) {
    this.accountHolders.add(customer);
  }

  @Autowired
  public Account(long accountID, double balance, String iban) {
        this.accountID = accountID;
        this.balance = balance;
        this.iban = iban;
        this.transactions = new ArrayList<>();
        this.accountHolders = new ArrayList<>();
        this.accountHolderNames = new ArrayList<>();
        this.transactionListByDate = new HashMap<>();
    }

    public Account(double balance, String iban) {
        this(0,balance, iban);
    }

    public Account() {
    }

    public Account(long accountID) {
        this.accountID = accountID;
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

    public List<String> getAccountHolderNames() {
        return accountHolderNames;
    }

    public void setAccountHolderNames(List<String> accountHolderNames) {
        this.accountHolderNames = accountHolderNames;
    }

    public void addAccountHolderName(String name) {
        accountHolderNames.add(name);
    }

    public void addTransaction (Transaction transaction) {
      transactions.add(transaction);
    }

    public Map<String, List<Transaction>> getTransactionListByDate() {
        return transactionListByDate;
    }

    public void setTransactionListByDate(Map<String, List<Transaction>> transactionListByDate) {
        this.transactionListByDate = transactionListByDate;
    }

    public String showBalance() {
        if (balance > 0) {
            return String.format("+%.2f",balance);
        } else {
            return String.format("-%.2f", balance);
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", iban='" + iban + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }
}
