package nl.hva.miw.internetbanking.data.dto;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Transaction;

import java.util.List;

public class TransactionHasAccountDTO {

    private Transaction transaction;
    private List<Account> accountList;

    public TransactionHasAccountDTO (Transaction transaction) {
        super();
        this.transaction = transaction;
    }

    public TransactionHasAccountDTO () {}

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public String toString() {
        return "TransactionHasAccountDTO{" +
                "transaction=" + transaction +
                ", accountList=" + accountList +
                '}';
    }
}
