package nl.hva.miw.internetbanking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee extends Customer implements Serializable {

    private EmployeeRole employeeRole;

    public Employee(EmployeeRole employeeRole) {
        this.employeeRole = employeeRole;
    }

    public Employee(String userName, String password, CustomerType customerType, EmployeeRole employeeRole) {
        super(userName, password, customerType);
        this.employeeRole = employeeRole;
    }

    public Employee(long customerID, String userName, String password, CustomerType customerType, EmployeeRole employeeRole) {
        super(customerID, userName, password, customerType);
        this.employeeRole = employeeRole;
    }

    public Employee(long id, EmployeeRole employeeRole) {
        super(id);
        this.employeeRole = employeeRole;
    }
}
