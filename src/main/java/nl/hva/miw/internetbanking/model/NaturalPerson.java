package nl.hva.miw.internetbanking.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NaturalPerson extends Customer implements Serializable {

    private String initials;
    private String firstName;
    private String preposition;
    private String surName;
    private String dateOfBirth;
    private long socialSecurityNumber;
    private String email;
    private long phone;
    private String postalCode;
    private String homeNumber;
    private String street;
    private String residence;

    public NaturalPerson(long id, String username, String password, CustomerType customerType,
                         String initials, String firstName, String preposition,
                         String surName, String dateOfBirth, long socialSecurityNumber,
                         String email, long phone, String postalCode, String homeNumber,
                         String street, String residence) {
        super(id, username, password, customerType);
        this.initials = initials;
        this.firstName = firstName;
        this.preposition = preposition;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.socialSecurityNumber = socialSecurityNumber;
        this.email = email;
        this.phone = phone;
        this.postalCode = postalCode;
        this.homeNumber = homeNumber;
        this.street = street;
        this.residence = residence;
    }

    public NaturalPerson(String username, String password, CustomerType customerType,
                         String initials, String firstName, String preposition,
                         String surName, String dateOfBirth, long socialSecurityNumber,
                         String email, long phone, String postalCode, String homeNumber,
                         String street, String residence) {
        super(ZERO, username, password, customerType);
        this.initials = initials;
        this.firstName = firstName;
        this.preposition = preposition;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.socialSecurityNumber = socialSecurityNumber;
        this.email = email;
        this.phone = phone;
        this.postalCode = postalCode;
        this.homeNumber = homeNumber;
        this.street = street;
        this.residence = residence;
    }

    public NaturalPerson(long id, String initials, String firstName, String preposition,
                         String surName, String dateOfBirth, long socialSecurityNumber,
                         String email, long phone, String postalCode, String homeNumber,
                         String street, String residence) {
        super(id, EMPTY_STRING, EMPTY_STRING, CustomerType.NATURAL);
        this.initials = initials;
        this.firstName = firstName;
        this.preposition = preposition;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.socialSecurityNumber = socialSecurityNumber;
        this.email = email;
        this.phone = phone;
        this.postalCode = postalCode;
        this.homeNumber = homeNumber;
        this.street = street;
        this.residence = residence;
    }

    public NaturalPerson() {
        super(ZERO, EMPTY_STRING, EMPTY_STRING, CustomerType.NATURAL);
    }

    @Override
    public String toString() {
        return "NaturalPerson{" +
                "initials='" + initials + '\'' +
                ", firstName='" + firstName + '\'' +
                ", preposition='" + preposition + '\'' +
                ", surName='" + surName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", socialSecurityNumber=" + socialSecurityNumber +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                ", postalCode='" + postalCode + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                ", street='" + street + '\'' +
                ", residence='" + residence + '\'' +
                '}' + "\n" + super.toString();
    }

}

