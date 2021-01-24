package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.data.dao.CustomerDAO;
import nl.hva.miw.internetbanking.data.dao.LegalPersonDAO;
import nl.hva.miw.internetbanking.data.dao.TransactionDAO;
import nl.hva.miw.internetbanking.data.dto.AccountHasTransactionsDTO;
import nl.hva.miw.internetbanking.data.dto.CustomerHasAccountsDTO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import nl.hva.miw.internetbanking.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static nl.hva.miw.internetbanking.model.CustomerType.NATURAL;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class TransactionServiceTest {

    TransactionDAO transactionDAO = Mockito.mock(TransactionDAO.class);
    AccountDAO accountDAO = Mockito.mock(AccountDAO.class);
    CustomerDAO customerDAO = Mockito.mock(CustomerDAO.class);
    CustomerService customerService = Mockito.mock(CustomerService.class);
    AccountService accountService = Mockito.mock(AccountService.class);
    LegalPersonDAO legalPersonDAO = Mockito.mock(LegalPersonDAO.class);

    CustomerHasAccountsDTO customerHasAccountsDTO;
    AccountHasTransactionsDTO accountHasTransactionsDTO;



    TransactionService transactionService = new TransactionService(transactionDAO, accountDAO, customerDAO, customerService, accountService, legalPersonDAO);

    public TransactionServiceTest() {
        super();
    }

    @BeforeEach
    public void testSetup() {
    }

    @Test
    void getTransactionsForAccountTest () {
        Account account = new Account(1L, 99.99 ,"NL77DRVN0414254784");
        List<Transaction> transactionList = List.of (new Transaction(1L, "NL77DRVN0414254784", "NL11DRVN0324157894", 1.00, "Test", LocalDateTime.now()),
                                                    new Transaction(2L, "NL77DRVN0414254784","NL11DRVN0324157894", 2.00, "Test2", LocalDateTime.now()));
        when(transactionService.getTransactionsForAccount(account)).thenReturn(transactionList);
        List<Transaction> result = transactionService.getTransactionsForAccount(account);
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertEquals(result.get(0).getTransactionID(), 1L);
        Assertions.assertEquals(result.get(1).getTransactionID(), 2L);
    }




}




