package nl.hva.miw.internetbanking.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import nl.hva.miw.internetbanking.data.validator.KvkNumberConstraint;
import nl.hva.miw.internetbanking.data.validator.SectorConstraint;
import nl.hva.miw.internetbanking.data.validator.UsernameExistsConstraint;
import nl.hva.miw.internetbanking.model.LegalPerson;
import nl.hva.miw.internetbanking.model.Sector;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class BusinessRegistrationDTO implements DTO<LegalPerson> {

    @NotBlank
    @Size(min = 4, max = 16)
    @Pattern(regexp = "^[A-Za-z0-9_]+$")
    @UsernameExistsConstraint
    private String userName;

    @NotBlank
    @Size(min = 6, max = 16)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,16}$")
    private String password;

    @NotBlank
    @Size(min = 6, max = 16)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,16}$")
    private String passwordConfirmation;

    @NotBlank
    @Size(max = 45)
    private String companyName;

    @KvkNumberConstraint
    private long kvkNumber;

    @SectorConstraint
    private Sector sector;

    @NotBlank
    @Pattern(regexp = "^(NL)?[0-9]{9}B[0-9]{2}$")
    @Size(min = 14, max = 15)
    private String vatNumber;

    @NotBlank
    @Pattern(regexp = "^[1-9][0-9]{3}[\\s]?[A-Za-z]{2}$")
    @Size(min = 6, max = 7)
    private String postalCode;

    @NotBlank
    @Pattern(regexp = "([-.,&_`() ]|[A-z0-9])+")
    @Size(max = 10)
    private String homeNumber;

    @NotBlank(message = "street")
    @Pattern(regexp = "^([-.\\s]|[a-zA-Z0-9]|[À-Ö]|[Ø-ö]|[ø-ǿ]|[Ȁ-ʯ]|[̀-ͯ]|[Ḁ-ỿ]|[Ⅰ-ↈ])+$")
    @Size(max = 45)
    private String street;

    @NotBlank
    @Pattern(regexp = "^([-.\\s]|[a-zA-Z0-9]|[À-Ö]|[Ø-ö]|[ø-ǿ]|[Ȁ-ʯ]|[̀-ͯ]|[Ḁ-ỿ]|[Ⅰ-ↈ])+$")
    @Size(max = 45)
    private String residence;

}
