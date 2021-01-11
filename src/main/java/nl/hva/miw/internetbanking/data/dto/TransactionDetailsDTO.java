package nl.hva.miw.internetbanking.data.dto;

import lombok.*;
import nl.hva.miw.internetbanking.model.Transaction;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransactionDetailsDTO implements DTO<Transaction>{

    private String nameDebitCustomer;
    private String nameCreditCustomer;
    private String debitAccount;
    private String creditAccount;
    private String amount;
    private String description;

}
