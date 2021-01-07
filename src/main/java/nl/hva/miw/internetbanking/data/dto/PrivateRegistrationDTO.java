package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hva.miw.internetbanking.model.NaturalPerson;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class PrivateRegistrationDTO implements DTO<NaturalPerson> {

    @NotBlank(message = "Dit is een verplicht veld.")
    @Size(min = 4, max = 16, message = "Minimaal 6 karakters, maximaal 16.")
    @Pattern(regexp = "^[A-Za-z0-9_]$",
             message = "Hoofdletters, kleine letters, cijfers  of underscore '_'.")
    // Min. 4 characters, lower- or uppercase characters, numbers or underscore
    private String userName;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Size(min = 6, max = 16, message = "Minimaal 6 karakters, maximaal 16.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,16}$",
             message = "Tenminste één hoofdletter, één kleine letter, een cijfer en een speciaal " +
                       "teken.")
    // Min. 6 characters, min 1 number, 1 lower- and uppercase letter, 1 special character
    private String password;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Size(min = 6, max = 16, message = "Minimaal 6 karakters, maximaal 16.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,16}$",
             message = "Tenminste één hoofdletter, één kleine letter, één cijfer en één speciaal " +
                       "teken.")
    private String passwordConfirmation;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Pattern(regexp = "^.*[a-zA-Z. ]{0,10}$", message = "Invoer bevat ongeldige tekens.")
    @Size(max = 10, message = "Maximaal 10 karakters.")
    private String initials;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Pattern(regexp = "^.*[a-zA-Z-. ]{0,15}$", message = "Invoer bevat ongeldige tekens.")
    @Size(max = 15, message = "Maximaal 15 karakters.")
    private String firstName;

    @Pattern(regexp = "^.*[a-zA-Z-. ]{0,15}$", message = "Invoer bevat ongeldige tekens.")
    @Size(max = 15, message = "Maximaal 15 karakters.")
    private String preposition;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Pattern(regexp = "^.*[a-zA-Z-. ]{0,45}$", message = "Invoer bevat ongeldige tekens.")
    @Size(max = 45, message = "Maximaal 45 karakters.")
    private String surName;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$", message = "Onjuist datum format.")
    @Size(min = 10, max = 10, message = "Moet uit 10 karakters bestaan.")
    private String dateOfBirth;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Pattern(regexp = "^[0-9]{8,9}$", message = "Voer een geldig BSN in (8 of 9 cijfers).")
    @Size(min = 8, max = 9, message = "BSN bestaat uit 8 of 9 cijfers.")
    private String socialSecurityNumber;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Email(message = "Voer een geldig e-mailadres in.")
    @Size(max = 45, message = "Maximaal 45 karakters.")
    private String email;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Pattern(regexp = "^[06]{2}-[0-9]{8}$", message = "Format is '06-12345678'.")
    @Size(max = 11)
    private String phone;

    @NotBlank
    @Pattern(regexp = "^[0-9]{4}[ A-Z]{2,3}$", message = "Voer een geldige postcode in.")
    @Size(max = 7, message = "Maximaal 7 karakters.")
    @Size(min = 6, message = "Minimaal 6 karakters.")
    private String postalCode;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Pattern(regexp = "^.*[0-9a-zA-z-. ]{0,10}$", message = "Invoer bevat ongeldige tekens.")
    @Size(max = 10, message = "Maximaal 10 karakters.")
    private String homeNumber;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Pattern(regexp = "^.*[0-9a-zA-z-. ]{0,10}$", message = "Invoer bevat ongeldige tekens.")
    @Size(max = 45, message = "Maximaal 45 karakters.")
    private String street;

    @NotBlank(message = "Dit is een verplicht veld.")
    @Pattern(regexp = "^.*[0-9a-zA-z-. ]{0,10}$", message = "Invoer bevat ongeldige tekens.")
    @Size(max = 45, message = "Maximaal 45 karakters.")
    private String residence;

}
