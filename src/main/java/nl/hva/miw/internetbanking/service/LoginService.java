// Created by huub
// Creation date 2020-12-15

package nl.hva.miw.internetbanking.service;

import nl.hva.miw.internetbanking.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

  private Logger logger = LoggerFactory.getLogger(LoginService.class);

  public LoginService() {
    super();
  }

  public boolean validCustomer(Customer customer, String password) {
      return customer != null && customer.getPassword().equals(password);
  }
}
