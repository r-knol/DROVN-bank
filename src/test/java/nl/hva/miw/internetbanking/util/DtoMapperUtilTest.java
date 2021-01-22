package nl.hva.miw.internetbanking.util;

import nl.hva.miw.internetbanking.data.dto.BusinessRegistrationDTO;
import nl.hva.miw.internetbanking.data.dto.PrivateRegistrationDTO;
import nl.hva.miw.internetbanking.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DtoMapperUtilTest {
    
    private static final String USERNAME = "ppost";
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
    private static final long CUSTOMER_ID = 999L;
    private static final CustomerType BUSINESS_CUSTOMER_TYPE = CustomerType.LEGAL;
    private static final String COMPANY = "Post Unlimited";
    private static final long KVK_NUMBER = 12345678L;
    private static final Sector SECTOR = Sector.TRANSPORT;
    private static final String VAT_NUMBER = "NL12345678B01";
    
    private PrivateRegistrationDTO srcDTO;
    private LegalPerson srcEntity;
    
    @BeforeEach
    public void setUp() {
        srcDTO = initializeDTO();
        srcEntity = initializeEntity();
    }
    
    private PrivateRegistrationDTO initializeDTO() {
        // Making use of Lombok SuperBuilder to create DTO with initialized fields
        return PrivateRegistrationDTO.builder()
                .userName(USERNAME)
                .password(PASSWORD)
                .passwordConfirmation(PASSWORD)
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
    
    private LegalPerson initializeEntity() {
        // Making use of Lombok SuperBuilder to create entity with initialized fields
        return LegalPerson.builder()
                .customerID(CUSTOMER_ID)
                .userName(USERNAME)
                .password(PASSWORD)
                .customerType(BUSINESS_CUSTOMER_TYPE)
                .companyName(COMPANY)
                .kvkNumber(KVK_NUMBER)
                .sector(SECTOR)
                .vatNumber(VAT_NUMBER)
                .postalCode(POSTAL_CODE)
                .homeNumber(HOME_NUMBER)
                .street(STREET)
                .residence(RESIDENCE)
                .build();
    }
    
    @Nested
    @DisplayName("Tests mapping DTO to Entity")
    class MapDtoToEntityTests {
        
        @Test
        @DisplayName("DTO with not blank fields to new entity")
        void Should_ReturnEntityWithValuesNotBlank_When_DtoFieldsAreNotBlank() {
            NaturalPerson destination =
                    DtoMapperUtil.mapDtoToEntity(srcDTO, NaturalPerson.class);
    
            // Field values should be mapped to corresponding values from srcDTO
            assertThat(destination.getUserName())
                    .isEqualTo(srcDTO.getUserName())
                    .isEqualTo(USERNAME);
            assertThat(destination.getPassword())
                    .isEqualTo(srcDTO.getPassword())
                    .isEqualTo(PASSWORD);
            assertThat(destination.getInitials())
                    .isEqualTo(srcDTO.getInitials())
                    .isEqualTo(INITIALS);
            assertThat(destination.getFirstName())
                    .isEqualTo(srcDTO.getFirstName())
                    .isEqualTo(FIRSTNAME);
            assertThat(destination.getPreposition())
                    .isEqualTo(srcDTO.getPreposition())
                    .isEqualTo(PREPOSITION);
            assertThat(destination.getSurName())
                    .isEqualTo(srcDTO.getSurName())
                    .isEqualTo(SURNAME);
            assertThat(destination.getDateOfBirth())
                    .isEqualTo(srcDTO.getDateOfBirth())
                    .isEqualTo(DATE_OF_BIRTH);
            assertThat(destination.getSocialSecurityNumber())
                    .isEqualTo(srcDTO.getSocialSecurityNumber())
                    .isEqualTo(SOCIAL_SECURITY);
            assertThat(destination.getEmail())
                    .isEqualTo(srcDTO.getEmail())
                    .isEqualTo(EMAIL);
            assertThat(destination.getPhone())
                    .isEqualTo(srcDTO.getPhone())
                    .isEqualTo(PHONE);
            assertThat(destination.getPostalCode())
                    .isEqualTo(srcDTO.getPostalCode())
                    .isEqualTo(POSTAL_CODE);
            assertThat(destination.getHomeNumber())
                    .isEqualTo(srcDTO.getHomeNumber())
                    .isEqualTo(HOME_NUMBER);
            assertThat(destination.getStreet())
                    .isEqualTo(srcDTO.getStreet())
                    .isEqualTo(STREET);
            assertThat(destination.getResidence())
                    .isEqualTo(srcDTO.getResidence())
                    .isEqualTo(RESIDENCE);
    
            // Fields not in srcDTO set with default values in NaturalPerson class
            assertThat(destination.getCustomerID()).isEqualTo(Customer.ZERO);
            assertThat(destination.getAccounts().size()).isEqualTo(Customer.ZERO);
            assertThat(destination.getCustomerType()).isEqualTo(CustomerType.NATURAL);
            assertThat(destination.getCustomerName()).isEqualTo(null);
        }
        
        @Test
        @DisplayName("DTO with blank fields to new entity")
        void Should_ReturnEntityWithDefaultValues_When_DtoCreatedWithDefaultConstructor() {
            // All fields of source are null, except kvkNumber (= 0L), using default constructor
            BusinessRegistrationDTO source = new BusinessRegistrationDTO();
            LegalPerson destination = DtoMapperUtil.mapDtoToEntity(source, LegalPerson.class);
    
            // Field values should be mapped to corresponding values from source
            assertThat(destination.getUserName())
                    .isEqualTo(source.getUserName())
                    .isNull();
            assertThat(destination.getPassword())
                    .isEqualTo(source.getPassword())
                    .isNull();
            assertThat(destination.getCompanyName())
                    .isEqualTo(source.getCompanyName())
                    .isNull();
            assertThat(destination.getKvkNumber())
                    .isEqualTo(source.getKvkNumber())
                    .isEqualTo(0L);
            assertThat(destination.getSector())
                    .isEqualTo(source.getSector())
                    .isNull();
            assertThat(destination.getVatNumber())
                    .isEqualTo(source.getVatNumber())
                    .isNull();
            assertThat(destination.getPostalCode())
                    .isEqualTo(source.getPostalCode())
                    .isNull();
            assertThat(destination.getHomeNumber())
                    .isEqualTo(source.getHomeNumber())
                    .isNull();
            assertThat(destination.getStreet())
                    .isEqualTo(source.getStreet())
                    .isNull();
            assertThat(destination.getResidence())
                    .isEqualTo(source.getResidence())
                    .isNull();
    
            // Fields not in source are set with default values in LegalPerson class
            assertThat(destination.getCustomerID()).isEqualTo(Customer.ZERO);
            assertThat(destination.getCustomerType()).isEqualTo(CustomerType.LEGAL);
            assertThat(destination.getAccountmanagerID()).isEqualTo(
                    LegalPerson.DEFAULT_ACCOUNTMANAGER_ID);
            assertThat(destination.getAccounts().size()).isEqualTo(Customer.ZERO);
        }
    }
    
    @Nested
    @DisplayName("Tests mapping Entity to DTO")
    class MapEntityToDtoTests {
        
        @Test
        @DisplayName("Entity with fields not blank to new DTO")
        void Should_ReturnDtoWithValuesNotBlank_When_EntityFieldsAreNotBlank() {
            BusinessRegistrationDTO destination = DtoMapperUtil.mapEntityToDto(srcEntity,
                    BusinessRegistrationDTO.class);
    
            // Field values should be mapped to corresponding values from srcEntity
            assertThat(destination.getUserName())
                    .isEqualTo(srcEntity.getUserName())
                    .isEqualTo(USERNAME);
            assertThat(destination.getPassword())
                    .isEqualTo(srcEntity.getPassword())
                    .isEqualTo(PASSWORD);
            assertThat(destination.getCompanyName())
                    .isEqualTo(srcEntity.getCompanyName())
                    .isEqualTo(COMPANY);
            assertThat(destination.getKvkNumber())
                    .isEqualTo(srcEntity.getKvkNumber())
                    .isEqualTo(KVK_NUMBER);
            assertThat(destination.getSector())
                    .isEqualTo(srcEntity.getSector())
                    .isEqualTo(SECTOR);
            assertThat(destination.getVatNumber())
                    .isEqualTo(srcEntity.getVatNumber())
                    .isEqualTo(VAT_NUMBER);
            assertThat(destination.getPostalCode())
                    .isEqualTo(srcEntity.getPostalCode())
                    .isEqualTo(POSTAL_CODE);
            assertThat(destination.getHomeNumber())
                    .isEqualTo(srcEntity.getHomeNumber())
                    .isEqualTo(HOME_NUMBER);
            assertThat(destination.getStreet())
                    .isEqualTo(srcEntity.getStreet())
                    .isEqualTo(STREET);
            assertThat(destination.getResidence())
                    .isEqualTo(srcEntity.getResidence())
                    .isEqualTo(RESIDENCE);
            // passwordConfirmation is not a field in LegalPerson class, so should remain null
            assertThat(destination.getPasswordConfirmation()).isNull();
        }
        
        @Test
        @DisplayName("Entity with blank fields to new DTO")
        void Should_ReturnDtoWithDefaultValues_When_EntityCreatedWithDefaultConstructor() {
            NaturalPerson source = new NaturalPerson();
            PrivateRegistrationDTO destination = DtoMapperUtil.mapEntityToDto(source,
                    PrivateRegistrationDTO.class);
            // Field values should be mapped to corresponding values from source
            // userName & password set to empty string by default constructor of NaturalPerson class
            assertThat(destination.getUserName())
                    .isEqualTo(source.getUserName())
                    .isEqualTo(Customer.EMPTY_STRING);
            assertThat(destination.getPassword())
                    .isEqualTo(source.getPassword())
                    .isEqualTo(Customer.EMPTY_STRING);
            // All other fields of destination should be null, because the corresponding fields in
            // NaturalPerson class are null when using default constructor
            assertThat(destination.getInitials())
                    .isEqualTo(source.getInitials())
                    .isNull();
            assertThat(destination.getPreposition())
                    .isEqualTo(source.getPreposition())
                    .isNull();
            assertThat(destination.getSurName())
                    .isEqualTo(source.getSurName())
                    .isNull();
            assertThat(destination.getDateOfBirth())
                    .isEqualTo(source.getDateOfBirth())
                    .isNull();
            assertThat(destination.getSocialSecurityNumber())
                    .isEqualTo(source.getSocialSecurityNumber())
                    .isNull();
            assertThat(destination.getEmail())
                    .isEqualTo(source.getEmail())
                    .isNull();
            assertThat(destination.getPhone())
                    .isEqualTo(source.getPhone())
                    .isNull();
            assertThat(destination.getPostalCode())
                    .isEqualTo(source.getPostalCode())
                    .isNull();
            assertThat(destination.getHomeNumber())
                    .isEqualTo(source.getHomeNumber())
                    .isNull();
            assertThat(destination.getStreet())
                    .isEqualTo(source.getStreet())
                    .isNull();
            assertThat(destination.getResidence())
                    .isEqualTo(source.getResidence())
                    .isNull();
            // passwordConfirmation is not a field in NaturalPerson class, so should remain null
            assertThat(destination.getPasswordConfirmation()).isNull();
        }
    }
}