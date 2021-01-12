package nl.hva.miw.internetbanking.data.dto;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.Transaction;

import java.util.List;

public class CustomerHasTransactionsDTO {

    private List<Customer> customerList;
    private List<Account> accountHoldersList;
    private Transaction transaction;
    private List<String> accountHolderNames;


    public CustomerHasTransactionsDTO (Transaction transaction) {
        super();
        this.transaction = transaction;
    }


    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Account> getAccountList() {
        return accountHoldersList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountHoldersList = accountList;
    }

    public List<String> getAccountHolderNames() {
        return accountHolderNames;
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



    public void addAccountHolderName(String name) {
        accountHolderNames.add(name);
    }

    @Override
    public String toString() {
        return "CustomerHasTransactionsDTO{" +
                "customers=" + customerList +
                ", accounts=" + accountHoldersList +
                ", transaction=" + transaction +
                '}';
    }
}
