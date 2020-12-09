package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AccountService {

//    private AccountDao accountDao;
//    private Logger logger = LoggerFactory.getLogger(AccountService.class);
//
//    @Autowired
//    public AccountService(AccountDao accountDao) {
//        this.accountDao = accountDao;
//        logger.warn("New AccountService.");
//    }
//
//    public List<Account> searchByCustomerId() { // long customerId
//        List<Account> accountList = new ArrayList<>();
//        Account a = new Account(1234567, 1999.94, "1234NL1234");
//        accountList.add(a);
//        // accountDao.searchByCustomerId(customerId);
//        return accountList;
//    }
}
