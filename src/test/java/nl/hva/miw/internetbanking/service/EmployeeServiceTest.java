package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.data.dao.EmployeeDAO;
import nl.hva.miw.internetbanking.model.Employee;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeServiceTest {

    @MockBean
    EmployeeDAO employeeDAO = Mockito.mock(EmployeeDAO.class);

    EmployeeService employeeService = new EmployeeService(employeeDAO);

    public EmployeeServiceTest() {
        super();
    }

    @Test
    void getEmployeeByUsername() {
        Employee employee = new Employee();
        String expected = "pdeboer";
        employee.setUserName("pdeboer");

        Mockito.when(employeeService.getEmployeeByUsername(expected)).
                thenReturn(java.util.Optional.of(employee));

        assertEquals(expected, employee.getUserName());
    }
}