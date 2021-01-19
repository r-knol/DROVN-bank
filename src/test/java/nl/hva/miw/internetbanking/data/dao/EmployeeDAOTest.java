package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest // aan Spring aangeven dat data/een DAO getest gaat worden
@ActiveProfiles("test")
class EmployeeDAOTest {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDAOTest(JdbcTemplate jdbcTemplate) {
        super();
        this.jdbcTemplate = jdbcTemplate;
    }

    @Test
    public void daoTest1() {
        EmployeeDAO dao = new EmployeeDAO(jdbcTemplate); // nieuwe DAO gemaakt
        Optional<Employee> optionalEmployee = dao.read(1L);
        assertTrue(optionalEmployee.isPresent());
    }
}