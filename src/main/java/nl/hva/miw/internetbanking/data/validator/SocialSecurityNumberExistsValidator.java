package nl.hva.miw.internetbanking.data.validator;

import nl.hva.miw.internetbanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SocialSecurityNumberExistsValidator
        implements ConstraintValidator<SocialSecurityNumberExistsConstraint, String> {

    @Autowired
    private CustomerService customerService;

    @Override
    public void initialize(SocialSecurityNumberExistsConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(String socialSecurityNumber,
                           ConstraintValidatorContext constraintValidatorContext) {
        if (customerService.getCustomerBySocialSecurityNumber(socialSecurityNumber).isPresent()) {
            return false;
        }
        return true;
    }

}
