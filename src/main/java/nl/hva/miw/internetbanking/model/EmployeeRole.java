package nl.hva.miw.internetbanking.model;

import lombok.Getter;

public enum EmployeeRole {

    HEAD_PRIVATE("Hoofd Particulier"),
    HEAD_LEGAL("Hoofd MKB"),
    ACCOUNTMANAGER("Accountmanager");

    @Getter
    private final String label;

    EmployeeRole(String label) {
        this.label = label;
    }
}
