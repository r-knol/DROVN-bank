package nl.hva.miw.internetbanking.data.dto;

import lombok.*;
import nl.hva.miw.internetbanking.model.Transaction;
import java.time.LocalDateTime;

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
    private LocalDateTime date;

}
