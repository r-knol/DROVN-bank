package nl.hva.miw.internetbanking.data.dto;

import lombok.*;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationDto {

    private Customer customer;
    public String passwordConfirmation;
    private NaturalPerson naturalPerson;
    private LegalPerson legalPerson;

}
