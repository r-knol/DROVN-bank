package nl.hva.miw.internetbanking.data.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TransactionDetailsDTO {

    private String nameDebitCustomer;
    private String nameCreditCustomer;
    private String debitAccount;
    private String creditAccount;
    private String amount;

}
