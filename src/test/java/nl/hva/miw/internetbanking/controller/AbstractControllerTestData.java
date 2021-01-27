package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.model.CustomerType;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import nl.hva.miw.internetbanking.model.Sector;

public abstract class AbstractControllerTestData {
    
    protected static final String USERNAME = "ppost";
    protected static final long CUSTOMER_ID = 999L;
    private static final String PASSWORD = "Aa!123";
    private static final String INITIALS = "P.";
    private static final String FIRSTNAME = "Pieter";
    private static final String PREPOSITION = "";
    private static final String SURNAME = "Post";
    private static final String DATE_OF_BIRTH = "1981-09-16";
    protected static final String SOCIAL_SECURITY_NUMBER = "123456789";
    private static final String EMAIL = "pieter@post.nl";
    private static final String PHONE = "06-12345678";
    private static final String POSTAL_CODE = "1234 AB";
    private static final String HOME_NUMBER = "1A-F";
    private static final String STREET = "Briefstraat";
    private static final String RESIDENCE = "Poststad";
    private static final String COMPANY = "Post Unlimited";
    protected static final long KVK_NUMBER = 12345678L;
    private static final String VAT_NUMBER = "NL12345678B01";
    
    protected final NaturalPerson privateCustomer;
    protected final LegalPerson businessCustomer;
    
    protected AbstractControllerTestData() {
        this.privateCustomer = setUpPrivateCustomer();
        this.businessCustomer = setUpBusinessCustomer();
    }
    
    private NaturalPerson setUpPrivateCustomer() {
        return NaturalPerson.builder()
                .customerID(CUSTOMER_ID)
                .userName(USERNAME)
                .password(PASSWORD)
                .customerType(CustomerType.NATURAL)
                .initials(INITIALS)
                .firstName(FIRSTNAME)
                .preposition(PREPOSITION)
                .surName(SURNAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .socialSecurityNumber(SOCIAL_SECURITY_NUMBER)
                .email(EMAIL)
                .phone(PHONE)
                .postalCode(POSTAL_CODE)
                .homeNumber(HOME_NUMBER)
                .street(STREET)
                .residence(RESIDENCE)
                .build();
    }
    
    private LegalPerson setUpBusinessCustomer() {
        return LegalPerson.builder()
                .customerID(CUSTOMER_ID)
                .userName(USERNAME)
                .password(PASSWORD)
                .customerType(CustomerType.LEGAL)
                .companyName(COMPANY)
                .kvkNumber(KVK_NUMBER)
                .sector(Sector.TRANSPORT)
                .vatNumber(VAT_NUMBER)
                .postalCode(POSTAL_CODE)
                .homeNumber(HOME_NUMBER)
                .street(STREET)
                .residence(RESIDENCE)
                .accountmanagerID(LegalPerson.DEFAULT_ACCOUNTMANAGER_ID)
                .build();
    }
    
}
