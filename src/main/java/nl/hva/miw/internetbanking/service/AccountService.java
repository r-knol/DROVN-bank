package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.repository.AccountDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccountService {

    private AccountDAO accountDao;
    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    public AccountService(AccountDAO accountDao) {
        this.accountDao = accountDao;
        logger.warn("New AccountService.");
    }

    public List<Account> getAccountsByCustomerId(long customerId) {
        return accountDao.getAccountsByCustomerId(customerId);
    }

    //@Author Veroniek
    public void saveNewAccount(Account account){
        accountDao.saveAccount(account);
    }
}
