package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.model.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDAOTest {

    @MockBean
    EmployeeDAO employeeDAO = Mockito.mock(EmployeeDAO.class);

    @Test
    void read() {
        Employee employee = new Employee();
        long expected = 3;
        employee.setEmployeeID(3);

        Mockito.when(employeeDAO.read(expected)).
                thenReturn(Optional.of(employee));

        assertEquals(expected, employee.getEmployeeID());
    }
}