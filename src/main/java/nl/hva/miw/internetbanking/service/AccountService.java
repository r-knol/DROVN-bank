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
//        List<Account> accountList = new ArrayList<>();
//        Account a = new Account(1234567, 1999.94, "1234NL1234");
//        Account b = new Account(654321, 80.43, "6543NL4321");
//        accountList.add(a);
//        accountList.add(b);
        return accountDao.getAccountsByCustomerId(customerId);
    }
}
