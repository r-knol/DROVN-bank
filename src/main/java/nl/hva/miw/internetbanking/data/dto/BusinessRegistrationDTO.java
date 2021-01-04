package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.Sector;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class BusinessRegistrationDTO implements DTO<LegalPerson> {

    @NotBlank(message = "Dit is een verplicht veld.")
    @Size(min = 4, max = 16, message = "Minimaal 6 karakters, maximaal 16.")
    @Pattern(regexp = "^(?=[a-zA-Z0-9_]{4,}$)", message = "Hoofdletters, kleine letters, cijfers " +
                                                          "of underscore '_'.")
    // Min. 4 characters, lower- or uppercase characters, numbers or underscore
    private String userName;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Size(min = 6, max = 16, message = "Minimaal 6 karakters, maximaal 16.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
             message = "Tenminste één hoofdletter, één kleine letter, een cijfer en een speciaal " +
                       "teken.")
    // Min. 6 characters, min 1 number, 1 lower- and uppercase letter, 1 special character
    private String password;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Size(min = 6, max = 16, message = "Minimaal 6 karakters, maximaal 16.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
             message = "Tenminste één hoofdletter, één kleine letter, één cijfer en één speciaal " +
                       "teken.")
    private String passwordConfirmation;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Size(max = 45, message = "Maximaal 45 karakters.")
    private String companyName;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Pattern(regexp = "^[0-9]{8}$", message = "Voer een geldig KvK-nummer in (8 cijfers).")
    @Size(min = 8, max = 8, message = "KvK-nummer moet uit 8 cijfers bestaan.")
    private long kvkNumber;

    @NotBlank(message = "Dit is een verplicht veld.")
    private Sector sector;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Pattern(regexp = "^(NL)?[0-9]{9}B[0-9]{2}$", message = "Voer een geldig Btw-nummer in (9 " +
                                                            "cijfers, letter B, 2 cijfers).")
    @Size(min = 12, max = 12, message = "9 cijfers, letter B, 2 cijfers.")
    private String vatNumber;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Pattern(regexp = "^[0-9]{4}[ A-Z]{2,3}$", message = "Voer een geldige postcode in.")
    @Size(max = 7, message = "Maximaal 7 karakters.")
    @Size(min = 6, message = "Minimaal 6 karakters.")
    private String postalCode;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Pattern(regexp = "^.*[0-9a-zA-z-. ]{0,}$", message = "Invoer bevat ongeldige tekens.")
    @Size(max = 10, message = "Maximaal 10 karakters.")
    private String homeNumber;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Pattern(regexp = "^.*[0-9a-zA-z-. ]{0,}$", message = "Invoer bevat ongeldige tekens.")
    @Size(max = 45, message = "Maximaal 45 karakters.")
    private String street;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Pattern(regexp = "^.*[0-9a-zA-z-. ]{0,}$", message = "Invoer bevat ongeldige tekens.")
    @Size(max = 45, message = "Maximaal 45 karakters.")
    private String residence;

}
