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

    public NaturalPersonHasAccountDTO(String firstName, String preposition, String surName, String iban,
                                      double balance, String address, String phoneNumber, String email, String dateOfBirth) {
        this.naturalPerson = new NaturalPerson(firstName, preposition, surName,address, phoneNumber, email, dateOfBirth);
        this.account = new Account(iban, balance);
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
