// Created by huub
// Creation date 2020-12-15

package nl.hva.miw.internetbanking.service;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.Employee;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {

    public LoginService() {
        super();
    }

    public boolean validCustomer(Customer customer, String password) {
        return customer != null && customer.getPassword().equals(password);
    }

    public boolean validEmployee(Employee employee, String password) {
      return employee != null && employee.getPassword().equals(password);
    }
}
