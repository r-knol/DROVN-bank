package nl.hva.miw.internetbanking.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Rekening {

    private long rekeningID;
    private double saldo;
    private String IBAN;
    private List<Transactie> transactieHistorie;

    public Rekening(long rekeningID, double saldo, String IBAN) {
        this.rekeningID = rekeningID;
        this.saldo = saldo;
        this.IBAN = IBAN;
        transactieHistorie = new ArrayList<>();
    }

    public long getRekeningID() {
        return rekeningID;
    }

    public void setRekeningID(long rekeningID) {
        this.rekeningID = rekeningID;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    @Override
    public String toString() {
        return "Rekening{" +
                "rekeningID=" + rekeningID +
                ", saldo=" + saldo +
                ", IBAN='" + IBAN + '\'' +
                ", transactieHistorie=" + transactieHistorie +
                '}';
    }
}
