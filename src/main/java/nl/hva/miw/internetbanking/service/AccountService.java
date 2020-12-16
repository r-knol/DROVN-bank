package nl.hva.miw.internetbanking.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.data.dto.AccountTransactionDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountService {

    private AccountDAO accountDao;
    @Getter
    @Setter
    private AccountTransactionDTO accountTransactionDTO;

    @Autowired
    public AccountService(AccountDAO accountDAO) {
        this.accountDao = accountDAO;
        accountTransactionDTO = new AccountTransactionDTO();
        log.warn("New AccountService.");
    }

    public List<Account> getAccountsByCustomerId(long customerId) {
        return accountDao.getAccountsByCustomerId(customerId);
    }

    //@Author Veroniek
    public void saveNewAccount(Account account) {
        accountDao.create(account);
    }

    //@author Olaf
    public Account getAccountByIban (String iban) {
        return accountDao.getAccountByIban(iban);
    }

}
