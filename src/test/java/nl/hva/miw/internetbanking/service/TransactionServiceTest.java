package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.data.dao.AccountDAO;
import nl.hva.miw.internetbanking.data.dao.CustomerDAO;
import nl.hva.miw.internetbanking.data.dao.TransactionDAO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.Transaction;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class TransactionServiceTest {

    @MockBean
    TransactionDAO transactionDAO = Mockito.mock(TransactionDAO.class);
    AccountDAO accountDAO = Mockito.mock(AccountDAO.class);
    CustomerDAO customerDAO = Mockito.mock(CustomerDAO.class);
    CustomerService customerService = Mockito.mock(CustomerService.class);
    AccountService accountService = Mockito.mock(AccountService.class);


    TransactionService transactionService = new TransactionService(transactionDAO, accountDAO, customerDAO, customerService, accountService );

    public TransactionServiceTest () {
        super();
    }

    @Test
    void setTransactionWithContraAccountNames() {
        List<Transaction> transactionList = new ArrayList<>();
        Transaction transaction = new Transaction();
        Account account = new Account();
        Customer customer = new Customer();


        String expectedCustomer = "jjanssen";
        Long expectedAccountID = 4L;
        account.setAccountID(4L);
        customer.setUserName("jjanssen");
//        transaction.setTransactionID(1);
//        transaction.setCreditAccount("NL47DRVN0604578744");
//        transaction.setDebitAccount("NL55DRVN0341578744");
//        transaction.setDateTime(LocalDateTime.of(2021, 01, 19, 10, 44, 00));
//        transaction.setAmount(99.99);
//        transaction.setDescription("transactie");
        transactionList.add(1, ("NL47DRVN0604578744", "NL55DRVN0341578744", LocalDateTime.of(2021, 01, 19, 10, 44, 00),99.99, "transactie");
    }
}
