package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Sector;

import java.text.DecimalFormat;

@Getter
@Setter
@ToString
public class BalancePerSectorDTO {

    private Sector sector;
    private Account account;

    public BalancePerSectorDTO(Sector sector, Account account) {
        this.sector = sector;
        this.account = new Account(account.getBalance());
    }

    public String getBalance() {
        DecimalFormat d = new DecimalFormat("###,###.00");
        return "€" + d.format(account.getBalance());
    }
}
