package nl.hva.miw.internetbanking.data.dto;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CustomerHasTransactionsDTO {

    private List<Customer> customerList;
    private Transaction transaction;
    private String accountHolderName;
    private Customer contraCustomer;
    private List<Account> accountList;
    private Account contraAccount;
    private List<String> accountHolderNames;


    public CustomerHasTransactionsDTO () {
        super();
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public String getAccountHolderNames() {
        return accountHolderName;
    }

    public void setAccountHolderNames(List<String> accountHolderNames) {
        this.accountHolderNames = accountHolderNames;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Customer getContraCustomer() {
        return contraCustomer;
    }

    public void setContraCustomer(Customer contraCustomer) {
        this.contraCustomer = contraCustomer;
    }

    public Account getContraAccount() {
        return contraAccount;
    }

    public void setContraAccount(Account contraAccount) {
        this.contraAccount = contraAccount;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public void addAccountHolderName (String name) {
        accountHolderNames.add(name);
    }

    @Override
    public String toString() {
        return "CustomerHasTransactionsDTO{" +
                "customerList=" + customerList +
                ", transaction=" + transaction +
                ", accountHolderName='" + accountHolderName + '\'' +
                ", contraCustomer=" + contraCustomer +
                ", accountList=" + accountList +
                ", contraAccount=" + contraAccount +
                ", accountHolderNames=" + accountHolderNames +
                '}';
    }
}
