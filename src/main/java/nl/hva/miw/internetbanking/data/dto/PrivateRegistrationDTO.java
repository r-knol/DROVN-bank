package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hva.miw.internetbanking.model.NaturalPerson;

@Getter
@Setter
@ToString
public class PrivateRegistrationDTO implements DTO<NaturalPerson> {

    private String userName;
    private String password;
    private String initials;
    private String firstName;
    private String preposition;
    private String surName;
    private String dateOfBirth;
    private String socialSecurityNumber;
    private String email;
    private String phone;
    private String postalCode;
    private String homeNumber;
    private String street;
    private String residence;
    private String passwordConfirmation;

}
