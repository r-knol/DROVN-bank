package nl.hva.miw.internetbanking.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private long customerID;
    private String userName;
    private String password;
    private List<Account> accounts;

    public Customer(long id, String username, String password) {
        this.customerID = id;
        this.userName = username;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    public Customer(long id) {
        this.customerID = id;
    }

    public Customer() {
    }

    public long getId() {
        return customerID;
    }

    public void setId(int id) {
        this.customerID = id;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public List<Account> getAccounts() {
    return accounts;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }

  public void addAccount(Account account) {
      this.accounts.add(account);
  }

  @Override
  public String toString() {
    return "Customer{" +
        "customerID=" + customerID +
        ", userName='" + userName + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
