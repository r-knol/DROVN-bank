package nl.hva.miw.internetbanking.model;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Account implements Serializable {

    private long accountID;
    private String iban;
    private double balance;
    private List<Transaction> transactions;
    private List<Customer> accountHolders;
    private List<String> accountHolderNames;

    public List<Customer> getAccountHolders() {
        return accountHolders;
    }

  public void setAccountHolders(List<Customer> accountHolders) {
    this.accountHolders = accountHolders;
  }

  public void addAccountHolder(Customer customer) {
    this.accountHolders.add(customer);
  }

  public Account(long accountID,String iban, double balance ) {
        this.accountID = accountID;
        this.iban = iban;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        this.accountHolders = new ArrayList<>();
        this.accountHolderNames = new ArrayList<>();
    }

    public Account() {
    }

    public Account(String iban, double balance) {
        this(0,iban, balance);
    }

    public Account(long accountID) {
        this.accountID = accountID;
    }

    public String generateNewAccount() {
        Random rand = new Random();
        String iban = "DROVN";
        for (int i = 0; i < 14; i++) {
            int n = rand.nextInt(10) + 0;
            iban += Integer.toString(n);
        }
        for (int i = 0; i < 16; i++) {
            iban.charAt(i);
        }
        return iban;
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
