package nl.hva.miw.internetbanking.data.dto;

import lombok.*;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OpenAccountDTO {

    private Account account;
    private Customer customer;

}
