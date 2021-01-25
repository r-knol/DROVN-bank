package nl.hva.miw.internetbanking.exception;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    private static final String INVALID_ARGUMENT = "Ongeldige parameter";
    
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        List<ConstraintViolation<?>> cvList = new ArrayList<>(e.getConstraintViolations());
        ConstraintViolation<?> cv = cvList.get(0);
        String cvParameter = ((PathImpl) cv.getPropertyPath()).getLeafNode().asString();
        String cvMessage = cv.getMessage();
        String errorMessage = String.format("Parameter '%s' %s", cvParameter, cvMessage);
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMessage));
    }
    
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        String methodParameter = e.getParameter().getParameterName();
        String errorMessage = String.format("%s '%s'", INVALID_ARGUMENT, methodParameter);
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, errorMessage));
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e) {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, e));
    }
    
    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
    
}
