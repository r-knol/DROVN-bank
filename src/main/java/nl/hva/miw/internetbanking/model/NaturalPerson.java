package nl.hva.miw.internetbanking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NaturalPerson extends Customer {

    private static final String EMPTY_STRING = "";
    private static final long LONG_0 = 0;

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
        super(username, password, customerType);
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
        super(id);
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
        super(LONG_0, EMPTY_STRING, EMPTY_STRING, CustomerType.NATURAL);
    }

}

