package nl.hva.miw.internetbanking.model;

public class Klant {

    private long klantId;
    private String gebruikersnaam;
    private String wachtwoord;
    private String straatnaam;
    private String huisnummer;
    private String postcode;
    private String woonplaats;

    public Klant(long klantId, String gebruikersnaam, String wachtwoord, String straatnaam,
                 String huisnummer, String postcode, String woonplaats) {
        this.klantId = klantId;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
    }

    public Klant(String gebruikersnaam, String wachtwoord, String straatnaam,
                 String huisnummer, String postcode, String woonplaats) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
        this.straatnaam = straatnaam;
        this.huisnummer = huisnummer;
        this.postcode = postcode;
        this.woonplaats = woonplaats;
    }

    public Klant() {
    }

    public long getKlantId() {
        return klantId;
    }

    public void setKlantId(long klantId) {
        this.klantId = klantId;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
    }

    public String getStraatnaam() {
        return straatnaam;
    }

    public void setStraatnaam(String straatnaam) {
        this.straatnaam = straatnaam;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    @Override
    public String toString() {
        return "Klant{" +
                "klantId=" + klantId +
                ", gebruikersnaam='" + gebruikersnaam + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                ", straatnaam='" + straatnaam + '\'' +
                ", huisnummer='" + huisnummer + '\'' +
                ", postcode='" + postcode + '\'' +
                ", woonplaats='" + woonplaats + '\'' +
                '}';
    }
}
