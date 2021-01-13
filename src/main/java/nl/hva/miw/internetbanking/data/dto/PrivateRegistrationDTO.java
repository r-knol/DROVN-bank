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

    @NotBlank
    @Size(min = 4, max = 16)
    @Pattern(regexp = "^[A-Za-z0-9_]+$")
    private String userName;

    @NotBlank
    @Size(min = 6, max = 16)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{0,}$")
    private String password;

    @NotBlank
    @Size(min = 6, max = 16)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{0,}$")
    private String passwordConfirmation;

    @NotBlank
    @Pattern(regexp = "^([\\s.]|[a-zA-Z0-9]|[À-Ö]|[Ø-ö]|[ø-ǿ]|[Ȁ-ʯ]|[̀-ͯ]|[Ḁ-ỿ]|[Ⅰ-ↈ])+$")
    @Size(max = 15)
    private String initials;

    @NotBlank
    @Pattern(regexp = "^([-\\s]|[a-zA-Z0-9]|[À-Ö]|[Ø-ö]|[ø-ǿ]|[Ȁ-ʯ]|[̀-ͯ]|[Ḁ-ỿ]|[Ⅰ-ↈ])+$")
    @Size(max = 25)
    private String firstName;

    @Pattern(regexp = "^([-.\\s]|[a-zA-Z0-9]|[À-Ö]|[Ø-ö]|[ø-ǿ]|[Ȁ-ʯ]|[̀-ͯ]|[Ḁ-ỿ]|[Ⅰ-ↈ])+$")
    @Size(max = 15)
    private String preposition;

    @NotBlank
    @Pattern(regexp = "^([-.\\s]|[a-zA-Z0-9]|[À-Ö]|[Ø-ö]|[ø-ǿ]|[Ȁ-ʯ]|[̀-ͯ]|[Ḁ-ỿ]|[Ⅰ-ↈ])+$")
    @Size(max = 45)
    private String surName;

    @NotBlank
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$")
    @Size(min = 10, max = 10)
    private String dateOfBirth;

    @NotBlank
    @Pattern(regexp = "^[0-9]{8,9}$")
    @Size(min = 8, max = 9)
    private String socialSecurityNumber;

    @NotBlank
    @Email
    @Size(max = 45)
    private String email;

    @NotBlank
    @Pattern(regexp = "^[06]{2}-[0-9]{8}$")
    @Size(min = 11, max = 11)
    private String phone;

    @NotBlank
    @Pattern(regexp = "^[1-9][0-9]{3}[\\s]?[A-Za-z]{2}$")
    @Size(min = 6, max = 7)
    private String postalCode;

    @NotBlank
    @Pattern(regexp = "([-.,&_`() ]|[A-z0-9])+")
    @Size(max = 10)
    private String homeNumber;

    @NotBlank
    @Pattern(regexp = "^([-.\\s]|[a-zA-Z0-9]|[À-Ö]|[Ø-ö]|[ø-ǿ]|[Ȁ-ʯ]|[̀-ͯ]|[Ḁ-ỿ]|[Ⅰ-ↈ])+$")
    @Size(max = 45)
    private String street;

    @NotBlank
    @Pattern(regexp = "^([-.\\s]|[a-zA-Z0-9]|[À-Ö]|[Ø-ö]|[ø-ǿ]|[Ȁ-ʯ]|[̀-ͯ]|[Ḁ-ỿ]|[Ⅰ-ↈ])+$")
    @Size(max = 45)
    private String residence;

}
