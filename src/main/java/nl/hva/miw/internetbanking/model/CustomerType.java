package nl.hva.miw.internetbanking.model;

import lombok.Getter;

public enum CustomerType {
    
    LEGAL("Zakelijke klant"),
    NATURAL("Particuliere klant");
    
    @Getter
    private final String label;
    
    CustomerType(String label) {
        this.label = label;
    }
    
}
