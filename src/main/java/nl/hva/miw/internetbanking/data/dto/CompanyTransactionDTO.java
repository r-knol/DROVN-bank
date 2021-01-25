package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hva.miw.internetbanking.model.Employee;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.Transaction;

@Getter
@Setter
@ToString
public class CompanyTransactionDTO {

    private LegalPerson legalPerson;
    private Transaction transaction;
    private Employee employee;

    public CompanyTransactionDTO(LegalPerson lp, Transaction transaction, Employee employee) {
        this.legalPerson = new LegalPerson(lp.getCompanyName(), lp.getKvkNumber(), lp.getAddress());
        this.transaction = new Transaction(transaction.getNumberOfTransactions());
        this.employee = new Employee(employee.getFirstName(), employee.getPreposition(), employee.getSurName());
    }

    public String getFullName() {
        if (employee.getPreposition() == null) {
            return employee.getFirstName() + " " + employee.getSurName();
        } else {
            return employee.getFirstName() + " " + employee.getPreposition() + " " + employee.getSurName();
        }
    }
}