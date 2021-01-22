package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.NaturalPerson;

import java.text.DecimalFormat;

@Getter
@Setter
@ToString
public class NaturalPersonHasAccountDTO {

    private NaturalPerson naturalPerson;
    private Account account;

    public NaturalPersonHasAccountDTO(NaturalPerson np, Account account) {
        this.naturalPerson = new NaturalPerson(np.getFirstName(), np.getPreposition(), np.getSurName(), np.getDateOfBirth(), np.getAddress(), np.getEmail(), np.getPhone());
        this.account = new Account(account.getIban(), account.getBalance());
    }

    public String getFullName() {
        if (naturalPerson.getPreposition() == null) {
            return naturalPerson.getFirstName() + " " + naturalPerson.getSurName();
        } else {
            return naturalPerson.getFirstName() + " " + naturalPerson.getPreposition() + " " + naturalPerson.getSurName();
        }
    }

    public String getBalance() {
        DecimalFormat d = new DecimalFormat("###,###.00");
        return "€" + d.format(account.getBalance());
    }
}
