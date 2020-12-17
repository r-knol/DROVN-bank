package nl.hva.miw.internetbanking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {

    private long accountID;
    private double balance;
    private String iban;
    private List<Transaction> transactions;
    private List<Customer> accountHolders;
//    private List<String> accountHolderNames;

  public List<Customer> getAccountHolders() {
    return accountHolders;
  }

  public void setAccountHolders(List<Customer> accountHolders) {
    this.accountHolders = accountHolders;
  }

  public void addAccountHolder(Customer customer) {
    this.accountHolders.add(customer);
  }

  public Account(long accountID, double balance, String iban) {
        this.accountID = accountID;
        this.balance = balance;
        this.iban = iban;
        this.transactions = new ArrayList<>();
        this.accountHolders = new ArrayList<>();
    //    this.accountHolderNames = new ArrayList<>();
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
//
//    public List<String> getAccountHolderNames() {
//        return accountHolderNames;
//    }
//
//    public void setAccountHolderNames(List<String> accountHolderNames) {
//        this.accountHolderNames = accountHolderNames;
//    }
//
//    public void addAccountHolderName(String name) {
//        accountHolderNames.add(name);
//    }

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
