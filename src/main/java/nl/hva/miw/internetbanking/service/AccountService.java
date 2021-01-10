package nl.hva.miw.internetbanking.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.controller.OpenAccountController;
import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.data.dto.AccountHasTransactionDTO;
import nl.hva.miw.internetbanking.data.dto.OpenAccountDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AccountService {

    private AccountDAO accountDao;
    @Getter
    @Setter
    private AccountHasTransactionDTO accountTransactionDTO;
    private OpenAccountDTO openAccountDTO;

    @Autowired
    public AccountService(AccountDAO accountDAO) {
        this.accountDao = accountDAO;
        openAccountDTO = new OpenAccountDTO();

        log.warn("New AccountService.");
    }

    public List<Account> getAccountsForCustomer(Customer customer) {
        return accountDao.getAccountsForCustomer(customer);
    }

    public List<Account> getAccountsByCustomerId(long customerId) {
        return accountDao.getAccountsByCustomerId(customerId);
    }

    private Optional <Account> getAccountDetails (Optional<Account> optionalAccount) {
        if(optionalAccount.isPresent()) {
            Account account = optionalAccount.get();
            accountDao.read(account.getAccountID());
            return Optional.of(account);
        }
        return optionalAccount;
    }

    public Optional <Account> getAccountById (long accountID) {
        return getAccountDetails(accountDao.read(accountID));
    }

    public <T extends Account> Account createAccountObject(Account account) {
        account.setAccountID(openAccountDTO.getAccount().getAccountID());
        account.setIban(openAccountDTO.getAccount().getIban());
        account.setBalance(openAccountDTO.getAccount().getBalance());
        return account;
    }

  /*  public void createNewAccount() {
        Account account = createAccountObject(openAccountDTO.getAccount());
        accountDao.create(account);
    }*/

//    public List<Account> getAllNaturalAccounts() {
//        return accountDao.getAllNaturalAccounts();
//    }

//    public List<Account> getAllLegalAccounts() {
//        return accountDao.getAllLegalAccounts();
//    }

    public void saveNewAccount(Account account, Customer customer) {
        accountDao.create(account);
    }

    public OpenAccountDTO getOpenAccountDTO() {
        return openAccountDTO;
    }

    public void setOpenAccountDTO(OpenAccountDTO openAccountDTO) {
        this.openAccountDTO = openAccountDTO;
    }
}
