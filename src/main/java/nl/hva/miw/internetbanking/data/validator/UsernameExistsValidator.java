package nl.hva.miw.internetbanking.data.validator;

import nl.hva.miw.internetbanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameExistsValidator
        implements ConstraintValidator<UsernameExistsConstraint, String> {

    @Autowired
    private CustomerService customerService;

    @Override
    public void initialize(UsernameExistsConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        if (customerService.getCustomerByUsername(username).isPresent()) {
            return false;
        }
        return true;
    }

}
