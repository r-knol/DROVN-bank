package nl.hva.miw.internetbanking.controller;

import nl.hva.miw.internetbanking.model.CustomerType;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import nl.hva.miw.internetbanking.model.Sector;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

@ActiveProfiles("test-nodb")
public abstract class AbstractControllerTest {
    
    public static final String USERNAME = "ppost";
    public static final long CUSTOMER_ID = 999L;
    private static final String PASSWORD = "Aa!123";
    private static final String INITIALS = "P.";
    private static final String FIRSTNAME = "Pieter";
    private static final String PREPOSITION = "";
    private static final String SURNAME = "Post";
    private static final String DATE_OF_BIRTH = "1981-09-16";
    private static final String SOCIAL_SECURITY = "123456789";
    private static final String EMAIL = "pieter@post.nl";
    private static final String PHONE = "06-12345678";
    private static final String POSTAL_CODE = "1234 AB";
    private static final String HOME_NUMBER = "1A-F";
    private static final String STREET = "Briefstraat";
    private static final String RESIDENCE = "Poststad";
    private static final CustomerType BUSINESS_CUSTOMER_TYPE = CustomerType.LEGAL;
    private static final String COMPANY = "Post Unlimited";
    private static final long KVK_NUMBER = 12345678L;
    private static final Sector SECTOR = Sector.TRANSPORT;
    private static final String VAT_NUMBER = "NL12345678B01";
    
    protected final NaturalPerson privateCustomer;
    protected final LegalPerson businessCustomer;
    
    protected AbstractControllerTest() {
        this.privateCustomer = setUpPrivateCustomer();
        this.businessCustomer = setUpBusinessCustomer();
    }
    
    
    private NaturalPerson setUpPrivateCustomer() {
        return NaturalPerson.builder()
                .customerID(CUSTOMER_ID)
                .userName(USERNAME)
                .password(PASSWORD)
                .accounts(new ArrayList<>())
                .customerType(CustomerType.NATURAL)
                .initials(INITIALS)
                .firstName(FIRSTNAME)
                .preposition(PREPOSITION)
                .surName(SURNAME)
                .dateOfBirth(DATE_OF_BIRTH)
                .socialSecurityNumber(SOCIAL_SECURITY)
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
