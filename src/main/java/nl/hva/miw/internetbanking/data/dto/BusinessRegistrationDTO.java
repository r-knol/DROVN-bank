package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.Sector;

@Getter
@Setter
@ToString
public class BusinessRegistrationDTO implements DTO<LegalPerson> {

    private String userName;
    private String password;
    private String passwordConfirmation;
    private String companyName;
    private long kvkNumber;
    private Sector sector;
    private String vatNumber;
    private String postalCode;
    private String homeNumber;
    private String street;
    private String residence;

}
