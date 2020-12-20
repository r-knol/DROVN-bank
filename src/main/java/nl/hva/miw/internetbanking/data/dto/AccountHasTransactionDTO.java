package nl.hva.miw.internetbanking.data.dto;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountHasTransactionDTO {

    private Account account;
    private List<Transaction> transactionList;

    public AccountHasTransactionDTO(Account account) {
        super();
        this.account = account;
    }

    public AccountHasTransactionDTO() {}

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "AccountTransactionDTO{" +
                "account=" + account +
                ", transactionList=" + transactionList +
                '}';
    }
}
