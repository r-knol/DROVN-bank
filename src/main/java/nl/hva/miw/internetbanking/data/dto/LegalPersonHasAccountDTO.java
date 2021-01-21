package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hva.miw.internetbanking.data.dao.LegalPersonDAO;
import nl.hva.miw.internetbanking.model.Account;
import nl.hva.miw.internetbanking.model.Employee;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.Sector;

import java.text.DecimalFormat;

@Getter
@Setter
@ToString
public class LegalPersonHasAccountDTO {

    private LegalPerson legalPerson;
    private Account account;
    private Sector sector;
    private Employee employee;

    public LegalPersonHasAccountDTO(LegalPerson lp, Employee employee, Account account) {
        this.legalPerson = new LegalPerson(lp.getCompanyName(), lp.getKvkNumber(), lp.getSector(),
                lp.getAddress());
        this.employee = new Employee(employee.getFirstName(), employee.getPreposition(), employee.getSurName());
        this.account = new Account(account.getIban(), account.getBalance());
    }

    public String getFullName() {
        if (employee.getPreposition() == null) {
            return employee.getFirstName() + " " + employee.getSurName();
        } else {
            return employee.getFirstName() + " " + employee.getPreposition() + " " + employee.getSurName();
        }
    }

    public String getBalance() {
        DecimalFormat d = new DecimalFormat("###,###.00");
        return "€" + d.format(account.getBalance());
    }
}
