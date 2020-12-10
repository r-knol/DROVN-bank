package nl.hva.miw.internetbanking.DTO;

import lombok.Getter;
import lombok.Setter;
import nl.hva.miw.internetbanking.model.LegalPerson;

@Getter
@Setter
public class BusinessRegistrationDto {

    private static final String EMPTY_STRING = "";

    private LegalPerson company;
    private String contactUsername;
    private String contactPassword;

    public BusinessRegistrationDto() {
        this.company = new LegalPerson();
        this.contactUsername = EMPTY_STRING;
        this.contactPassword = EMPTY_STRING;
    }

    @Override
    public String toString() {
        return "BusinessRegistrationDto{" +
                "company=" + company +
                ", contactUsername='" + contactUsername + '\'' +
                ", contactPassword='" + contactPassword + '\'' +
                '}';
    }

}
