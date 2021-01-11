package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;
import nl.hva.miw.internetbanking.model.Sector;

import java.text.DecimalFormat;

@Getter
@Setter
public class BalancePerSectorDTO {

    private Sector sector;
    private double balance;

    public BalancePerSectorDTO(Sector sector, double balance) {
        this.sector = sector;
        this.balance = balance;
    }

    public String getBalance() {
        DecimalFormat d = new DecimalFormat("##.00");
        return d.format(balance);
    }
}
