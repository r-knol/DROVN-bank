// Created by huub
// Creation date 2020-12-15

package nl.hva.miw.internetbanking.service;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {

  public LoginService() {
    super();
    log.warn("New LoginService.");
  }

  public boolean validCustomer(Customer customer, String password) {
      return customer != null && customer.getPassword().equals(password);
  }
}
