package nl.hva.miw.internetbanking.DTO;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;

import java.util.List;

public class CustomerAccountDTO {

    private Customer customer;
    private List<Account> accountList;
    private Account account;
    private List<Customer> customerList;

    public CustomerAccountDTO(Customer customer, List<Account> list) {
        this.customer = customer;
        this.accountList = list;
    }

    public CustomerAccountDTO(Account account, List<Customer> customerList) {
        this.account = account;
        this.customerList = customerList;
    }

    // companyName
    // firstName, prepostion, lastNAme
    // iban, balance

    public CustomerAccountDTO() {
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    @Override
    public String toString() {
        return "CustomerAccountDTO{" +
                "customer=" + customer +
                ", accountList=" + accountList +
                ", account=" + account +
                ", customerList=" + customerList +
                '}';
    }
}
