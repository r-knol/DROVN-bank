package nl.hva.miw.internetbanking.model;

import lombok.Getter;

public enum CustomerType {

    NATURAL("Zakelijke klant"),
    LEGAL("Particuliere klant");

    @Getter
    private final String label;

    CustomerType(String label) {
        this.label = label;
    }

}
