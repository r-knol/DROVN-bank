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
public class Employee implements Serializable {

    private long employeeID;
    private String userName;
    private String password;
    private String firstName;
    private String preposition;
    private String lastName;
    private EmployeeRole employeeRole;

    public Employee(long employeeID, String userName, String password, String firstName,
                    String preposition, String lastName, EmployeeRole employeeRole) {
        this.employeeID = employeeID;
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.preposition = preposition;
        this.lastName = lastName;
        this.employeeRole = employeeRole;
    }
}
