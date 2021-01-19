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

//    private String firstName;
//    private String preposition;
//    private String surName;
    private String iban;
    private double balance;
//    private String address;
//    private String phoneNumber;
//    private String email;
//    private String dateOfBirth;

    public NaturalPersonHasAccountDTO(String firstName, String preposition, String surName, String iban, double balance, String address, String phoneNumber, String email, String dateOfBirth) {
      this.naturalPerson = new NaturalPerson(firstName, preposition, surName,address, phoneNumber, email, dateOfBirth);
      this.account = new Account();
//      this.firstName = firstName;
//        this.preposition = preposition;
//        this.surName = surName;
        this.iban = iban;
        this.balance = balance;
//        this.address = address;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        if (preposition == null) {
            return firstName + " " + surName;
        } else {
            return firstName + " " + preposition + " " + surName;
        }
    }

    public String getBalance() {
        DecimalFormat d = new DecimalFormat("###,###.00");
        return "€" + d.format(balance);
    }

}
