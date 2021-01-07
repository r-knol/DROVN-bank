package nl.hva.miw.internetbanking.data.dto;

import nl.hva.miw.internetbanking.model.Sector;

public class BalancePerSectorDTO {

    private Sector sector;
    private double balance;

    public BalancePerSectorDTO(Sector sector, double balance) {
        this.sector = sector;
        this.balance = balance;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
