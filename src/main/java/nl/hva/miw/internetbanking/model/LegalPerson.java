package nl.hva.miw.internetbanking.model;

public class LegalPerson extends Customer {

    private String companyName;
    private int kvkNumber;
    private String sector;
    private String vatNumber;
    private String postalCode;
    private String homeNumber;
    private String street;
    private String residence;
    //private NaturalPerson accountmanager;

    public LegalPerson(int id, String username, String password, String companyName, int kvkNumber, String sector, String vatNumber, String postalCode,
                       String homeNumber, String street, String residence, NaturalPerson accountmanager) {
        super(id, username, password);
        this.companyName = companyName;
        this.kvkNumber = kvkNumber;
        this.sector = sector;
        this.vatNumber = vatNumber;
        this.postalCode = postalCode;
        this.homeNumber = homeNumber;
        this.street = street;
        this.residence = residence;
        //this.accountmanager = accountmanager;
    }

    public LegalPerson() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getKvkNumber() {
        return kvkNumber;
    }

    public void setKvkNumber(int kvkNumber) {
        this.kvkNumber = kvkNumber;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getVatNumber() {
        return vatNumber;
    }

    public void setVatNumber(String vatNumber) {
        this.vatNumber = vatNumber;
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

//    public NaturalPerson getAccountmanager() {
//        return accountmanager;
//    }
//
//    public void setAccountmanager(NaturalPerson accountmanager) {
//        this.accountmanager = accountmanager;
//    }

    @Override
    public String toString() {
        return "LegalPerson{" +
                "companyName='" + companyName + '\'' +
                ", kvkNumber=" + kvkNumber +
                ", sector='" + sector + '\'' +
                ", vatNumber='" + vatNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", homeNumber='" + homeNumber + '\'' +
                ", street='" + street + '\'' +
                ", residence='" + residence + '\'' +
                ", accountmanager=" + //accountmanager +
                '}';
    }
}