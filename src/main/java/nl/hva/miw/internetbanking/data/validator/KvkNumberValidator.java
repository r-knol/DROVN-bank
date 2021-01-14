package nl.hva.miw.internetbanking.data.validator;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

@Slf4j
public class KvkNumberValidator implements ConstraintValidator<KvkNumberConstraint, Long> {

    @Autowired
    private CustomerService customerService;

    @Override
    public void initialize(KvkNumberConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(Long kvkNumber, ConstraintValidatorContext constraintValidatorContext) {
        String asString = Long.toString(kvkNumber);
        int length = asString.length();
        Optional<Customer> customer = customerService.getCustomerByKvkNumber(kvkNumber);
        return length >= 8 && asString.matches("^[0-9]{8}$") && customer.isEmpty();
    }

}
