package nl.hva.miw.internetbanking.repository;

import nl.hva.miw.internetbanking.model.Account;

import java.util.List;

public interface AccountDAO {
    public List<Account> getAccountsByCustomerId(long customerId);
}
