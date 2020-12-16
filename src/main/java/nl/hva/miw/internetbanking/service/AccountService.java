package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.data.dao.OldAccountDAO;
import nl.hva.miw.internetbanking.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AccountService {

    private OldAccountDAO oldAccountDao;
    private Logger logger = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    public AccountService(OldAccountDAO oldAccountDao) {
        this.oldAccountDao = oldAccountDao;
        logger.warn("New AccountService.");
    }

    public List<Account> getAccountsByCustomerId(long customerId) {
        return oldAccountDao.getAccountsByCustomerId(customerId);
    }

    //@Author Veroniek
    public void saveNewAccount(Account account){
        oldAccountDao.saveAccount(account);
    }
}
