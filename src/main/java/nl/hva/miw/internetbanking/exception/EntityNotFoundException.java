package nl.hva.miw.internetbanking.exception;

public class EntityNotFoundException extends RuntimeException {
    
    private static final long serialVersionUID = -6815982061725738496L;
    
    public EntityNotFoundException(String message) {
        super(message);
    }
    
}
