package nl.hva.miw.internetbanking.data.dto;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;

import java.util.List;

public class CustomerAccountDTO {

    private Customer customer;
    private List<Account> accountList;

    public CustomerAccountDTO(Customer customer) {
      super();
      this.customer = customer;
    }

    public CustomerAccountDTO() {}


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

    @Override
    public String toString() {
        return "CustomerAccountDTO{" +
                "customer=" + customer +
                ", accountList=" + accountList +
                '}';
    }
}
