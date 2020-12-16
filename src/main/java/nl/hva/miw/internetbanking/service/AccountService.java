package nl.hva.miw.internetbanking.service;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountService {

    private AccountDAO accountDao;

    @Autowired
    public AccountService(AccountDAO accountDAO) {
        this.accountDao = accountDAO;
        log.warn("New AccountService.");
    }

    public List<Account> getAccountsByCustomerId(long customerId) {
        return accountDao.getAccountsByCustomerId(customerId);
    }

    //@Author Veroniek
    public void saveNewAccount(Account account) {
        accountDao.create(account);
    }

}
