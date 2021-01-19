package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.model.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class EmployeeDAOTest {

  private JdbcTemplate jdbcTemplate;

  @Autowired
  public EmployeeDAOTest(JdbcTemplate jdbcTemplate) {
    super();
    this.jdbcTemplate = jdbcTemplate;
  }

  @Test
  public void daoTest1() {
    EmployeeDAO dao = new EmployeeDAO(jdbcTemplate);
    Optional<Employee> optionalEmployee = dao.read(1L);
    assertEquals(optionalEmployee.isPresent(), true);
  }

//    @MockBean
//    EmployeeDAO employeeDAO = Mockito.mock(EmployeeDAO.class);
//
//    @Test
//    void read() {
//        Employee employee = new Employee();
//        long expected = 3;
//        employee.setEmployeeID(3);
//
//        Mockito.when(employeeDAO.read(expected)).
//                thenReturn(Optional.of(employee));
//
//        assertEquals(expected, employee.getEmployeeID());
//    }
}