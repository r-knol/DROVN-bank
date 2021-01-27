package nl.hva.miw.internetbanking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Objects;

@SuperBuilder
@Getter
@Setter
public class NaturalPerson extends Customer implements Serializable {
    
    private static final long serialVersionUID = 2314734678022380045L;
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
    private String address;
    
    public NaturalPerson(long id, String username, String password, CustomerType customerType,
                         String initials, String firstName, String preposition,
                         String surName, String dateOfBirth, String socialSecurityNumber,
                         String email, String phone, String postalCode, String homeNumber,
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
                         String surName, String dateOfBirth, String socialSecurityNumber,
                         String email, String phone, String postalCode, String homeNumber,
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
                         String surName, String dateOfBirth, String socialSecurityNumber,
                         String email, String phone, String postalCode, String homeNumber,
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
        super(Customer.ZERO, Customer.EMPTY_STRING, Customer.EMPTY_STRING, CustomerType.NATURAL);
    }

    public NaturalPerson(String firstName, String preposition, String surName, String dateOfBirth,
                         String address, String email, String phone) {
        this.firstName = firstName;
        this.preposition = preposition;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.email = email;
        this.phone = phone;
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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof NaturalPerson) {
            return super.equals(obj) &&
                    Objects.equals(initials, ((NaturalPerson) obj).initials) &&
                    Objects.equals(firstName, ((NaturalPerson) obj).firstName) &&
                    Objects.equals(preposition, ((NaturalPerson) obj).preposition) &&
                    Objects.equals(surName, ((NaturalPerson) obj).surName) &&
                    Objects.equals(dateOfBirth, ((NaturalPerson) obj).dateOfBirth) &&
                    Objects.equals(socialSecurityNumber,
                            ((NaturalPerson) obj).socialSecurityNumber) &&
                    Objects.equals(email, ((NaturalPerson) obj).email) &&
                    Objects.equals(phone, ((NaturalPerson) obj).phone) &&
                    Objects.equals(postalCode, ((NaturalPerson) obj).postalCode) &&
                    Objects.equals(homeNumber, ((NaturalPerson) obj).homeNumber) &&
                    Objects.equals(street, ((NaturalPerson) obj).street) &&
                    Objects.equals(residence, ((NaturalPerson) obj).residence);
//                    initials == ((NaturalPerson) obj).initials &&
//                    firstName == ((NaturalPerson) obj).firstName &&
//                    preposition == ((NaturalPerson) obj).preposition &&
//                    surName == ((NaturalPerson) obj).surName &&
//                    dateOfBirth == ((NaturalPerson) obj).dateOfBirth &&
//                    socialSecurityNumber == ((NaturalPerson) obj).socialSecurityNumber &&
//                    email == ((NaturalPerson) obj).email &&
//                    phone == ((NaturalPerson) obj).phone &&
//                    postalCode == ((NaturalPerson) obj).postalCode &&
//                    homeNumber == ((NaturalPerson) obj).homeNumber &&
//                    street == ((NaturalPerson) obj).street &&
//                    residence == ((NaturalPerson) obj).residence;
        }
        return false;
    }
    
}

