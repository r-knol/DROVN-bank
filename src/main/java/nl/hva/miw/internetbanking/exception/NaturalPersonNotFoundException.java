package nl.hva.miw.internetbanking.exception;

public class NaturalPersonNotFoundException extends RuntimeException {

    public NaturalPersonNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public NaturalPersonNotFoundException() {
        super();
    }

}
