package nl.hva.miw.internetbanking.service;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.data.dao.CustomerDAO;
import nl.hva.miw.internetbanking.data.dao.LegalPersonDAO;
import nl.hva.miw.internetbanking.data.dao.TransactionDAO;
import nl.hva.miw.internetbanking.data.dto.AccountHasTransactionsDTO;
import nl.hva.miw.internetbanking.data.dto.CompanyTransactionDTO;
import nl.hva.miw.internetbanking.data.dto.DTO;
import nl.hva.miw.internetbanking.data.dto.TransactionDetailsDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.Transaction;
import nl.hva.miw.internetbanking.util.DtoMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionService {

    @Getter
    @Setter
    private TransactionDAO transactionDAO;
    private AccountDAO accountDAO;
    private CustomerDAO customerDAO;
    private CustomerService customerService;
    private AccountService accountService;
    private LegalPersonDAO legalPersonDAO;

    @Autowired
    public TransactionService(TransactionDAO transactionDAO, AccountDAO accountDAO, CustomerDAO customerDAO,
                              CustomerService customerService, AccountService accountService, LegalPersonDAO legalPersonDAO) {
        this.transactionDAO = transactionDAO;
        this.accountDAO = accountDAO;
        this.customerDAO = customerDAO;
        this.customerService = customerService;
        this.accountService = accountService;
        this.legalPersonDAO = legalPersonDAO;
    }

    public List<CompanyTransactionDTO> getMostActiveClients() {
        return transactionDAO.getMostActiveClients();
    }

    public List<Transaction> getTransactionsForAccount(Account account) {
        return transactionDAO.getTransactionsForAccount(account);
    }

    private Optional<Transaction> getTransactionDetails(Optional<Transaction> optionalTransaction) {
        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transactionDAO.read(transaction.getTransactionID());
            return Optional.of(transaction);
        }
        return optionalTransaction;
    }

    public Optional<Transaction> getTransactionById(long transactionID) {
        return getTransactionDetails(transactionDAO.read(transactionID));
    }

    public Transaction getCreditTransaction(String iban) {
        return transactionDAO.getCreditTransaction(iban);
    }

    public Transaction getDebitTransaction(String iban) {
        return transactionDAO.getDebitTransaction(iban);
    }

    public <T> void doTransaction(DTO<T> dto, Class<T> transactionDetails) {
        T transaction = DtoMapperUtil.mapDtoToEntity(dto, transactionDetails);
        saveTransaction(transaction);
    }

    @Transactional
    public <T> void saveTransaction(T transaction) {
        try {
            ((Transaction) transaction).setDateTime(LocalDateTime.now());
            transactionDAO.create((Transaction) transaction);
            doMoneyTransaction((Transaction) transaction);
        } catch (DataAccessException e) {
            log.warn("Exception occurred while attempting to save transaction: {}", e.getMessage());
        }
    }

    public String transferToDouble(String amount) {
        String amountFormatted = amount.replace(",", ".");
        System.out.println("Voor een transactie: komma's omzetten naar punten, van " + amount + " naar " + amountFormatted);
        return amountFormatted;
    }

    public void doMoneyTransaction(Transaction t) {
        // update debit account:
        Account debitAccount = accountDAO.read(t.getDebitAccount()).get();
        Double newBalanceDebAcc = debitAccount.getBalance() - t.getAmount();
        debitAccount.setBalance(newBalanceDebAcc);
        accountDAO.update(debitAccount);

        // update credit account:
        Account creditAccount = accountDAO.read(t.getCreditAccount()).get();
        Double newBalanceCredAcc = creditAccount.getBalance() + t.getAmount();
        creditAccount.setBalance(newBalanceCredAcc);
        accountDAO.update(creditAccount);
    }

    public String getDebetAccountIban(long transactionID) {
        Optional<Transaction> transaction = transactionDAO.read(transactionID);
        if (transaction.isPresent()) {
            return transaction.get().getDebitAccount();
        }
        return null;
    }

    public void setTransactionWithContraAccountNames(AccountHasTransactionsDTO accountHasTransactionsDTO, Account account) {

        // voor alle transacties de bijbehorende namen van tegenrekeningen ophalen.
        for (Transaction transaction : accountHasTransactionsDTO.getTransactionList()) {
            if (account.getIban().equals(transaction.getCreditAccount())) {
                transaction.setCreditAccount(getDebetAccountIban(transaction.getTransactionID()));
            } else {
                transaction.setDebitAccount(getDebetAccountIban(transaction.getTransactionID()));
            }
            List<Customer> contraCustomers = customerService.getCustomerListByIban(transaction.getCreditAccount());
            for (Customer c : contraCustomers) {
                transaction.addDebetAccountHolderName(customerService.printNameCustomer(c.getCustomerID()));
            }
        }
    }

    public void setTransactionWithDateAsString(AccountHasTransactionsDTO accountHasTransactionsDTO) {
        accountHasTransactionsDTO.setTransactionListByDate(accountHasTransactionsDTO.getTransactionList().stream()
                .collect(Collectors.groupingBy(Transaction::getDate)));
    }

    public boolean checkValidTransaction(TransactionDetailsDTO tDto) {
        // check of iban voorkomt in db:
        Optional<Account> a = accountService.getAccountByIban(tDto.getCreditAccount());
        Account aFound;
        if (a.isPresent()) {
            aFound = a.get();
            // check of naam en iban matchen bij een account:
            return (checkValidReceiver(aFound, tDto.getNameCreditCustomer()));
        }
        return false;
    }

    public boolean checkValidReceiver(Account a, String nameFilledIn) {
        List<Customer> customerList = customerService.getCustomerByAccountId(a.getAccountID());
        // voor iedere customer uit de lijst, haal de naam op:
        for (Customer c : customerList) {
            String nameCusFound = customerService.printNameCustomer(c.getCustomerID());
            System.out.println("Naam klant: " + nameCusFound);
            //  vergelijk de achternaam van de ontvanger met de namen uit de lijst
            if (nameCusFound.toLowerCase().contains(nameFilledIn.toLowerCase())) {
                return true;
            }
        }
        System.out.println("Klantnaam ingevoerd niet gevonden in " + customerList);
        return false;
    }
}

