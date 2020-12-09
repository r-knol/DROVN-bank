package nl.hva.miw.internetbanking.model;

public class NaturalPerson extends Customer {

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

    public NaturalPerson(int id, String username, String password, String initials, String firstName, String preposition, String surName, String dateOfBirth, int socialSecurityNumber,
                         String email, int phone, String postCode, String homeNumber, String street, String residence) {
        super(id, username, password);
        this.initials = initials;
        this.firstName = firstName;
        this.preposition = preposition;
        this.surName = surName;
        this.dateOfBirth = dateOfBirth;
        this.socialSecurityNumber = socialSecurityNumber;
        this.email = email;
        this.phone = phone;
        this.postalCode = postCode;
        this.homeNumber = homeNumber;
        this.street = street;
        this.residence = residence;
    }

    public NaturalPerson() {
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

    public void setSocialSecurityNumber(int socialSecurityNumber) {
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

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postCode) {
        this.postalCode = postCode;
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
                ", postCode='" + postalCode + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                ", street='" + street + '\'' +
                ", residence='" + residence + '\'' +
                '}';
    }
}

