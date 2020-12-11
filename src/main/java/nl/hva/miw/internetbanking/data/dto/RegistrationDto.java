package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class RegistrationDto {

    private static final String EMPTY_STRING = "";

    private LegalPerson personLegal;
    private NaturalPerson personPrivate;
    private String username;
    private String password;
    private String passwordConfirmation;

    public RegistrationDto() {
        this.personLegal = new LegalPerson();
        this.personPrivate = new NaturalPerson();
        this.username = EMPTY_STRING;
        this.password = EMPTY_STRING;
        this.passwordConfirmation = EMPTY_STRING;
    }

    @Override
    public String toString() {
        return "RegistrationDto{" +
                "personLegal=" + personLegal +
                ", personPrivate=" + personPrivate +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirmation='" + passwordConfirmation + '\'' +
                '}';
    }

}
