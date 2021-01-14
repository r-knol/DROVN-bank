package nl.hva.miw.internetbanking.data.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = KvkNumberValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface KvkNumberConstraint {

    String message() default "Ongeldige invoer.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
