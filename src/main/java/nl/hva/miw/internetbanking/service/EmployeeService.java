package nl.hva.miw.internetbanking.service;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dao.EmployeeDAO;
import nl.hva.miw.internetbanking.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Optional<Employee> getEmployeeByUsername(String userName) {
        return getEmployeeDetails(employeeDAO.read(userName));
    }

    private Optional<Employee> getEmployeeDetails(Optional<Employee> optionalEmployee) {
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            return Optional.of(employee);
        }
        return Optional.empty();
    }
}
