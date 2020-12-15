package nl.hva.miw.internetbanking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
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

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPreposition() {
        return preposition;
    }

    public void setPreposition(String preposition) {
        this.preposition = preposition;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public long getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(long socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }
}

