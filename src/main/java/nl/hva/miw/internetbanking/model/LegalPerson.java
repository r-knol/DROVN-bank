package nl.hva.miw.internetbanking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LegalPerson extends Customer {

    private String companyName;
    private long kvkNumber;
    private String sector;
    private String vatNumber;
    private String postalCode;
    private String homeNumber;
    private String street;
    private String residence;
    private long accountmanagerID;

    public LegalPerson(long id, String username, String password, CustomerType customerType,
                       String companyName, long kvkNumber, String sector, String vatNumber,
                       String postalCode,
                       String homeNumber, String street, String residence, long accountmanagerID) {
        super(id, username, password, customerType);
        this.companyName = companyName;
        this.kvkNumber = kvkNumber;
        this.sector = sector;
        this.vatNumber = vatNumber;
        this.postalCode = postalCode;
        this.homeNumber = homeNumber;
        this.street = street;
        this.residence = residence;
        this.accountmanagerID = accountmanagerID;
    }

    public LegalPerson(long id, String companyName, long kvkNumber, String sector,
                       String vatNumber, String postalCode, String homeNumber, String street,
                       String residence, long accountmanagerID) {
        super(id);
        this.companyName = companyName;
        this.kvkNumber = kvkNumber;
        this.sector = sector;
        this.vatNumber = vatNumber;
        this.postalCode = postalCode;
        this.homeNumber = homeNumber;
        this.street = street;
        this.residence = residence;
        this.accountmanagerID = accountmanagerID;
    }

    public LegalPerson() {
        super(0, "", "", CustomerType.LEGAL);
    }

}