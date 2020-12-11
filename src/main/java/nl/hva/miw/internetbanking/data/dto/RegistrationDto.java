package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
public class RegistrationDto {

    private static final String EMPTY_STRING = "";

    private Customer customer;
    private LegalPerson personLegal;
    private NaturalPerson personPrivate;
    private String passwordConfirmation;

    public RegistrationDto() {
        customer = new Customer();
        personLegal = new LegalPerson();
        personPrivate = new NaturalPerson();
        passwordConfirmation = EMPTY_STRING;
    }

}
