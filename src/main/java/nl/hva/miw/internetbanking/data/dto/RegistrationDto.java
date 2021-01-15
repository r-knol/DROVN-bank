package nl.hva.miw.internetbanking.data.dto;

import lombok.*;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationDto implements Serializable {

    private static final long serialVersionUID = -8452836830504531139L;
    public String passwordConfirmation;
    private Customer customer;
    private NaturalPerson naturalPerson;
    private LegalPerson legalPerson;

}
