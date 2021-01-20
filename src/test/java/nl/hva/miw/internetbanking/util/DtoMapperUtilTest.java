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
    
    private static final String EMPTY_STRING = "";
    private PrivateRegistrationDTO initializedDTO;
    private Customer initializedCustomer;
    
    @BeforeEach
    public void setUp() {
        initializedDTO = initializeDTO();
        initializedCustomer = initializeCustomer();
    }
    
    private PrivateRegistrationDTO initializeDTO() {
        // Making use of Lombok SuperBuilder to create initialized DTO
        return PrivateRegistrationDTO.builder()
                .userName("dleertouwer")
                .password("Aa!123")
                .passwordConfirmation("Aa!123")
                .initials("D.")
                .firstName("Daan")
                .preposition("")
                .surName("Leertouwer")
                .dateOfBirth("1977-11-29")
                .socialSecurityNumber("123456789")
                .email("daniel.leertouwer@outlook.com")
                .phone("06-51064410")
                .postalCode("4021 ET")
                .homeNumber("32")
                .street("Lepelaarstraat")
                .residence("Maurik")
                .build();
    }
    
    private Customer initializeCustomer() {
        // Making use of Lombok SuperBuilder to create initialized business customer
        return LegalPerson.builder()
                .customerID(999L)
                .userName("company")
                .password("Bb!123")
                .customerType(CustomerType.LEGAL)
                .companyName("Testbedrijf")
                .kvkNumber(12345678L)
                .sector(Sector.IT)
                .vatNumber("NL12345678B01")
                .postalCode("1234 AB")
                .homeNumber("99")
                .street("Bedrijfsstraat")
                .residence("Bedrijfsplaats")
                .build();
    }
    
    @Nested
    @DisplayName("Tests mapping DTO to Entity")
    class MapDtoToEntityTests {
        
        @Test
        @DisplayName("DTO with not blank fields to new entity")
        void Should_ReturnEntityWithValuesNotBlank_When_DtoFieldsAreNotBlank() {
            NaturalPerson mappedCustomer = DtoMapperUtil.mapDtoToEntity(initializedDTO,
                    NaturalPerson.class);
            // Field values should be mapped to corresponding values from initializedDTO
            assertThat(mappedCustomer.getUserName()).isEqualTo(initializedDTO.getUserName());
            assertThat(mappedCustomer.getPassword()).isEqualTo(initializedDTO.getPassword());
            assertThat(mappedCustomer.getInitials()).isEqualTo(initializedDTO.getInitials());
            assertThat(mappedCustomer.getFirstName()).isEqualTo(initializedDTO.getFirstName());
            assertThat(mappedCustomer.getPreposition()).isEqualTo(initializedDTO.getPreposition());
            assertThat(mappedCustomer.getSurName()).isEqualTo(initializedDTO.getSurName());
            assertThat(mappedCustomer.getDateOfBirth()).isEqualTo(initializedDTO.getDateOfBirth());
            assertThat(mappedCustomer.getSocialSecurityNumber()).isEqualTo(
                    initializedDTO.getSocialSecurityNumber());
            assertThat(mappedCustomer.getEmail()).isEqualTo(initializedDTO.getEmail());
            assertThat(mappedCustomer.getPhone()).isEqualTo(initializedDTO.getPhone());
            assertThat(mappedCustomer.getPostalCode()).isEqualTo(initializedDTO.getPostalCode());
            assertThat(mappedCustomer.getHomeNumber()).isEqualTo(initializedDTO.getHomeNumber());
            assertThat(mappedCustomer.getStreet()).isEqualTo(initializedDTO.getStreet());
            assertThat(mappedCustomer.getResidence()).isEqualTo(initializedDTO.getResidence());
            // Fields not in initializedDTO set with default values from NaturalPerson class
            assertThat(mappedCustomer.getCustomerID()).isEqualTo(Customer.ZERO);
            assertThat(mappedCustomer.getAccounts().size()).isEqualTo(Customer.ZERO);
            assertThat(mappedCustomer.getCustomerType()).isEqualTo(CustomerType.NATURAL);
            assertThat(mappedCustomer.getCustomerName()).isEqualTo(null);
        }
        
        @Test
        @DisplayName("DTO with blank fields to new entity")
        void Should_ReturnEntityWithDefaultValues_When_DtoCreatedWithDefaultConstructor() {
            // All fields of dto are null, except kvkNumber (= 0L), using default constructor
            BusinessRegistrationDTO dto = new BusinessRegistrationDTO();
            LegalPerson mappedCustomer = DtoMapperUtil.mapDtoToEntity(dto, LegalPerson.class);
            // Field values should be mapped to corresponding values from DTO
            assertThat(mappedCustomer.getUserName()).isEqualTo(dto.getUserName()).isNull();
            assertThat(mappedCustomer.getPassword()).isEqualTo(dto.getPassword()).isNull();
            assertThat(mappedCustomer.getCompanyName()).isEqualTo(dto.getCompanyName()).isNull();
            assertThat(mappedCustomer.getKvkNumber()).isEqualTo(dto.getKvkNumber()).isEqualTo(0L);
            assertThat(mappedCustomer.getSector()).isEqualTo(dto.getSector()).isNull();
            assertThat(mappedCustomer.getVatNumber()).isEqualTo(dto.getVatNumber()).isNull();
            assertThat(mappedCustomer.getPostalCode()).isEqualTo(dto.getPostalCode()).isNull();
            assertThat(mappedCustomer.getHomeNumber()).isEqualTo(dto.getHomeNumber()).isNull();
            assertThat(mappedCustomer.getStreet()).isEqualTo(dto.getStreet()).isNull();
            assertThat(mappedCustomer.getResidence()).isEqualTo(dto.getResidence()).isNull();
            // Fields not in DTO are set with default values by LegalPerson constructor
            assertThat(mappedCustomer.getCustomerID()).isEqualTo(Customer.ZERO);
            assertThat(mappedCustomer.getCustomerType()).isEqualTo(CustomerType.LEGAL);
            assertThat(mappedCustomer.getAccountmanagerID()).isEqualTo(
                    LegalPerson.DEFAULT_ACCOUNTMANAGER_ID);
            assertThat(mappedCustomer.getAccounts().size()).isEqualTo(Customer.ZERO);
        }
    }
    
    @Nested
    @DisplayName("Tests mapping Entity to DTO")
    class MapEntityToDtoTests {
        
        @Test
        @DisplayName("Entity with fields not blank to new DTO")
        void Should_ReturnDtoWithValuesNotBlank_When_EntityFieldsAreNotBlank() {
            DtoMapperUtil.mapEntityToDto(initializedCustomer, BusinessRegistrationDTO.class);
        }
        
        @Test
        @DisplayName("Entity with blank fields to new DTO")
        void Should_ReturnDtoWithDefaultValues_When_EntityCreatedWithDefaultConstructor() {
            NaturalPerson entity = new NaturalPerson();
            PrivateRegistrationDTO mappedDTO = DtoMapperUtil.mapEntityToDto(entity,
                    PrivateRegistrationDTO.class);
            // Field values should be mapped to corresponding fields from entity
            // userName & password set to empty string by default constructor of NaturalPerson class
            assertThat(mappedDTO.getUserName()).isEqualTo(entity.getUserName()).isEqualTo(
                    Customer.EMPTY_STRING);
            assertThat(mappedDTO.getPassword()).isEqualTo(entity.getPassword()).isEqualTo(
                    Customer.EMPTY_STRING);
            // All other fields of mappedDTO should be null, because the corresponding fields in
            // NaturalPerson class are null when using default constructor
            assertThat(mappedDTO.getInitials()).isEqualTo(entity.getInitials()).isNull();
            assertThat(mappedDTO.getPreposition()).isEqualTo(entity.getPreposition()).isNull();
            assertThat(mappedDTO.getSurName()).isEqualTo(entity.getSurName()).isNull();
            assertThat(mappedDTO.getDateOfBirth()).isEqualTo(entity.getDateOfBirth()).isNull();
            assertThat(mappedDTO.getSocialSecurityNumber()).isEqualTo(
                    entity.getSocialSecurityNumber()).isNull();
            assertThat(mappedDTO.getEmail()).isEqualTo(entity.getEmail()).isNull();
            assertThat(mappedDTO.getPhone()).isEqualTo(entity.getPhone()).isNull();
            assertThat(mappedDTO.getPostalCode()).isEqualTo(entity.getPostalCode()).isNull();
            assertThat(mappedDTO.getHomeNumber()).isEqualTo(entity.getHomeNumber()).isNull();
            assertThat(mappedDTO.getStreet()).isEqualTo(entity.getStreet()).isNull();
            assertThat(mappedDTO.getResidence()).isEqualTo(entity.getResidence()).isNull();
            // passwordConfirmation is not a field in NaturalPerson class, so should remain null
            assertThat(mappedDTO.getPasswordConfirmation()).isNull();
        }
    }
}