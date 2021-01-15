package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.data.dao.EmployeeDAO;
import nl.hva.miw.internetbanking.model.Employee;
import nl.hva.miw.internetbanking.model.EmployeeRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    EmployeeDAO employeeDAO = Mockito.mock(EmployeeDAO.class);
    EmployeeService employeeService = new EmployeeService(employeeDAO);

    public EmployeeServiceTest() {
        super();
    }

    @Test
    void getEmployeeByUsername() {
        String userName = "pdeboer";
        Employee employee = new Employee();
        employee.setUserName("pdeboer");

        Mockito.when(employeeService.getEmployeeByUsername(userName)).
                thenReturn(java.util.Optional.of(employee));

        assertEquals(userName, employee.getUserName());
    }
}