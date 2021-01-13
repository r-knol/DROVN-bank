package nl.hva.miw.internetbanking.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.data.dto.AccountHasTransactionDTO;
import nl.hva.miw.internetbanking.data.dto.OpenAccountDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.Transaction;
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
    }

    public List<Account> getAccountsForCustomer(Customer customer) {
        return accountDao.getAccountsForCustomer(customer);
    }

//    public List<Account> getAccountsForCustomers(List<Customer> customers) {
//        return accountDao.getAccountsForCustomers(customers);
//    }

    public List<Account> getAccountsByIban (String iban) {
        return accountDao.getAccountsByIban(iban);
    }

    public Account getAccountByIban (String iban) {
        return accountDao.getAccountByIban(iban);
    }

//    public List<Customer> getCustomersForAccount (Account account) {
//        return accountDao.getCustomersForAccount(account);
//    }

    public List<Account> getAccountsByCustomerId(long customerId) {
        return accountDao.getAccountsByCustomerId(customerId);
    }

//    public Optional<Account> getAccountByIban (String iban) {
//        return accountDao.read(iban);
//    }

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

//    public List<Account> getAllNaturalAccounts() {
//        return accountDao.getAllNaturalAccounts();
//    }

//    public List<Account> getAllLegalAccounts() {
//        return accountDao.getAllLegalAccounts();
//    }

    public void saveNewAccount(Account account, Customer customer) {
        accountDao.create(account);
        accountDao.saveAccountToCustomer(account, customer);
    }

    public OpenAccountDTO getOpenAccountDTO() {
        return openAccountDTO;
    }

    public void setOpenAccountDTO(OpenAccountDTO openAccountDTO) {
        this.openAccountDTO = openAccountDTO;
    }

    public boolean uniqueIbanViolated(String iban){
        boolean uniqueIbanViolated = false;

        Optional<Account> ibanByAccount = accountDao.read(iban);
        boolean isCreatingNew = (iban == null);

        if (isCreatingNew){
            if (ibanByAccount.isPresent()){
                uniqueIbanViolated = true;
            }
        } return uniqueIbanViolated;
    }

}
