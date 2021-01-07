package nl.hva.miw.internetbanking.data.dto;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;

import java.util.List;

public class AccountHasCustomersDTO {

    private Account account;
    private List<Customer> customerList;
    private List<Account> accountList;

    public AccountHasCustomersDTO(Account account) {
        this.account = account;
    }

    public AccountHasCustomersDTO(List<Account> accountList) {
        this.accountList = accountList;
    }

    public AccountHasCustomersDTO() {
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
        return "AccountHasCustomersDTO{" +
                "account=" + account +
                ", customerList=" + customerList +
                '}';
    }
}
