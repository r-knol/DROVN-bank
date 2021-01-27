package nl.hva.miw.internetbanking.data;

import nl.hva.miw.internetbanking.model.*;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomerTestData {
    
    protected static final long CUSTOMER_ID = 999L;
    protected static final String USERNAME = "ppost";
    protected static final String PASSWORD = "Aa!123";
    protected static final List<Account> ACCOUNTS = new ArrayList<>();
    protected static final String INITIALS = "P.";
    protected static final String FIRSTNAME = "Pieter";
    protected static final String PREPOSITION = "";
    protected static final String SURNAME = "Post";
    protected static final String DATE_OF_BIRTH = "1981-09-16";
    protected static final String SOCIAL_SECURITY_NUMBER = "123456789";
    protected static final String EMAIL = "pieter@post.nl";
    protected static final String PHONE = "06-12345678";
    protected static final String POSTAL_CODE = "1234 AB";
    protected static final String HOME_NUMBER = "1A-F";
    protected static final String STREET = "Briefstraat";
    protected static final String RESIDENCE = "Poststad";
    protected static final String COMPANY = "Post Unlimited";
    protected static final long KVK_NUMBER = 12345678L;
    protected static final String VAT_NUMBER = "NL12345678B01";
    
    protected final NaturalPerson privateCustomer;
    protected final LegalPerson businessCustomer;
    
    protected CustomerTestData() {
        privateCustomer = setUpPrivateCustomer();
        businessCustomer = setUpBusinessCustomer();
    }
    
    private NaturalPerson setUpPrivateCustomer() {
        return NaturalPerson.builder()
                .customerID(CUSTOMER_ID)
                .userName(USERNAME)
                .password(PASSWORD)
                .customerType(CustomerType.NATURAL)
                .accounts(ACCOUNTS)
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
                .accounts(ACCOUNTS)
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
