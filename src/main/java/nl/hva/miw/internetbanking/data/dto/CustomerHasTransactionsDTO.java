package nl.hva.miw.internetbanking.data.dto;

import lombok.*;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.Transaction;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CustomerHasTransactionsDTO {

    private Customer currentCustomer;
    private List<Transaction> transactionList;

}
