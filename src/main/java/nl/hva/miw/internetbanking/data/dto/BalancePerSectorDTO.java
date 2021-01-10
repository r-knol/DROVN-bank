package nl.hva.miw.internetbanking.data.dto;

import nl.hva.miw.internetbanking.model.Sector;

import java.text.DecimalFormat;

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

    public String getBalance() {
        DecimalFormat d = new DecimalFormat("##.00");
        return d.format(balance);
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
