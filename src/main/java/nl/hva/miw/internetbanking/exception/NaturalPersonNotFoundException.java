package nl.hva.miw.internetbanking.exception;

public class NaturalPersonNotFoundException extends RuntimeException {

    public NaturalPersonNotFoundException(String message) {
        super(message);
    }

    public NaturalPersonNotFoundException() {
        super();
    }

}
