package nl.hva.miw.internetbanking.model;

public class Klant {

    private int klantID;
    private String gebruikersnaam;
    private String wachtwoord;

    public Klant() {
    }

    public Klant(int klantID, String gebruikersnaam, String wachtwoord) {
        this.klantID = klantID;
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public Klant(String gebruikersnaam, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public int getKlantID() {
        return klantID;
    }

    public void setKlantID(int klantID) {
        this.klantID = klantID;
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

    @Override
    public String toString() {
        return "Klant{" +
                "klantID=" + klantID +
                ", gebruikersnaam='" + gebruikersnaam + '\'' +
                ", wachtwoord='" + wachtwoord + '\'' +
                '}';
    }
}
