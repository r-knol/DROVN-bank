package nl.hva.miw.internetbanking.util;

import nl.hva.miw.internetbanking.data.CustomerTestData;
import nl.hva.miw.internetbanking.data.dto.PrivateRegistrationDTO;
import nl.hva.miw.internetbanking.model.Customer;
import nl.hva.miw.internetbanking.model.CustomerType;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static nl.hva.miw.internetbanking.model.Customer.EMPTY_STRING;
import static org.assertj.core.api.Assertions.assertThat;

class DtoMapperUtilTest extends CustomerTestData {
    
    private final NaturalPerson referenceCustomer;
    private final PrivateRegistrationDTO referenceDTO;
    
    DtoMapperUtilTest() {
        referenceCustomer = buildReferenceEntity();
        referenceDTO = buildReferenceDTO();
    }
    
    private NaturalPerson buildReferenceEntity() {
        return NaturalPerson.builder()
                .customerID(Customer.ZERO) // not in dto
                .userName(USERNAME)
                .password(PASSWORD)
                .customerType(CustomerType.NATURAL) // not in dto
                .accounts(ACCOUNTS) // not in dto
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
    
    private PrivateRegistrationDTO buildReferenceDTO() {
        return PrivateRegistrationDTO.builder()
                .userName(USERNAME)
                .password(PASSWORD)
                .passwordConfirmation(PASSWORD) // not in entity
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
    
    @Nested
    @DisplayName("Tests mapping DTO to Entity")
    class MapDtoToEntityTests {
        
        @Test
        @DisplayName("Return Entity Equal to Reference Entity when mapping matching DTO")
        void Should_ReturnEntityEqualToReferenceEntity_When_MappingDtoWithMatchingFieldsAndValues() {
            NaturalPerson actualCustomer =
                    DtoMapperUtil.mapDtoToEntity(referenceDTO, NaturalPerson.class);
            assertThat(actualCustomer).isEqualTo(referenceCustomer);
        }
        
        @Test
        @DisplayName("Skip non-matching fields & fields with null value")
        void Should_SkipNonMatchingFielsOrFieldsWithNullValue_When_MappingDtoToEntity() {
            referenceDTO.setUserName(null);
            referenceDTO.setPassword(null);
            NaturalPerson actual = DtoMapperUtil.mapDtoToEntity(referenceDTO, NaturalPerson.class);
            // Initially entity is constructed with default constructor, then matching fields are
            // mapped. Default constructor sets CustomerID to 0, userName and password to empty
            // string and CustomerType CustomerType.NATURAL. CustomerType is non-existent in dto.
            assertThat(actual.getUserName()).isEqualTo(EMPTY_STRING);
            assertThat(actual.getPassword()).isEqualTo(EMPTY_STRING);
            assertThat(actual.getCustomerType()).isEqualTo(CustomerType.NATURAL);
        }
    }
    
    @Nested
    @DisplayName("Tests mapping Entity to DTO")
    class MapEntityToDtoTests {
    
        @Test
        @DisplayName("Return DTO with equal values for matching fields")
        void Should_ReturnDtoEqualToReferenceDto_When_MappingEntityWithMatchingFieldsAndValues() {
            PrivateRegistrationDTO actual =
                    DtoMapperUtil.mapEntityToDto(referenceCustomer, PrivateRegistrationDTO.class);
            assertThat(actual.getUserName()).isEqualTo(referenceCustomer.getUserName());
            assertThat(actual.getPassword()).isEqualTo(referenceCustomer.getPassword());
            assertThat(actual.getPasswordConfirmation())
                    .isEqualTo(EMPTY_STRING); // non-existent skipped
            assertThat(actual.getInitials()).isEqualTo(referenceCustomer.getInitials());
            assertThat(actual.getPreposition()).isEqualTo(referenceCustomer.getPreposition());
            assertThat(actual.getFirstName()).isEqualTo(referenceCustomer.getFirstName());
            assertThat(actual.getSurName()).isEqualTo(referenceCustomer.getSurName());
            assertThat(actual.getDateOfBirth()).isEqualTo(referenceCustomer.getDateOfBirth());
            assertThat(actual.getSocialSecurityNumber())
                    .isEqualTo(referenceCustomer.getSocialSecurityNumber());
            assertThat(actual.getEmail()).isEqualTo(referenceCustomer.getEmail());
            assertThat(actual.getPhone()).isEqualTo(referenceCustomer.getPhone());
            assertThat(actual.getPostalCode()).isEqualTo(referenceCustomer.getPostalCode());
            assertThat(actual.getHomeNumber()).isEqualTo(referenceCustomer.getHomeNumber());
            assertThat(actual.getStreet()).isEqualTo(referenceCustomer.getStreet());
            assertThat(actual.getResidence()).isEqualTo(referenceCustomer.getResidence());
        }
    
        @Test
        @DisplayName("Skip non-matching fields & fields with null value")
        void Should_SkipNonMatchingFielsOrFieldsWithNullValue_When_MappingDtoToEntity() {
            referenceCustomer.setUserName(null);
            referenceCustomer.setPassword(null);
            PrivateRegistrationDTO actual =
                    DtoMapperUtil.mapEntityToDto(referenceCustomer, PrivateRegistrationDTO.class);
            assertThat(actual.getUserName()).isEqualTo(EMPTY_STRING);
            assertThat(actual.getPassword()).isEqualTo(EMPTY_STRING);
            assertThat(actual.getPasswordConfirmation())
                    .isEqualTo(EMPTY_STRING); // was, and should remain, null
        }
    }
}