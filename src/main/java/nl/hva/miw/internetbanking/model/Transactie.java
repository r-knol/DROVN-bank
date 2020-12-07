package nl.hva.miw.internetbanking.model;

import java.time.LocalDateTime;

public class Transactie {

    private int transactieID;
    private String debetrekeningNr;
    private String creditrekeningNr;
    private double bedrag;
    private String omschrijving;
    private LocalDateTime datumTijd;

    public Transactie(int transactieID, String debetrekeningNr, String creditrekeningNr, double bedrag, String omschrijving, LocalDateTime datumTijd) {
        this.transactieID = transactieID;
        this.debetrekeningNr = debetrekeningNr;
        this.creditrekeningNr = creditrekeningNr;
        this.bedrag = bedrag;
        this.omschrijving = omschrijving;
        this.datumTijd = datumTijd;
    }

    public Transactie() {
    }

    public int getTransactieID() {
        return transactieID;
    }

    public void setTransactieID(int transactieID) {
        this.transactieID = transactieID;
    }

    public String getDebetrekeningNr() {
        return debetrekeningNr;
    }

    public void setDebetrekeningNr(String debetrekeningNr) {
        this.debetrekeningNr = debetrekeningNr;
    }

    public String getCreditrekeningNr() {
        return creditrekeningNr;
    }

    public void setCreditrekeningNr(String creditrekeningNr) {
        this.creditrekeningNr = creditrekeningNr;
    }

    public double getBedrag() {
        return bedrag;
    }

    public void setBedrag(double bedrag) {
        this.bedrag = bedrag;
    }

    public String getOmschrijving() {
        return omschrijving;
    }

    public void setOmschrijving(String omschrijving) {
        this.omschrijving = omschrijving;
    }

    public LocalDateTime getDatumTijd() {
        return datumTijd;
    }

    public void setDatumTijd(LocalDateTime datumTijd) {
        this.datumTijd = datumTijd;
    }

    @Override
    public String toString() {
        return "Transactie{" +
                "transactieID=" + transactieID +
                ", debetrekeningNr='" + debetrekeningNr + '\'' +
                ", creditrekeningNr='" + creditrekeningNr + '\'' +
                ", bedrag=" + bedrag +
                ", omschrijving='" + omschrijving + '\'' +
                ", datumTijd=" + datumTijd +
                '}';
    }
}
