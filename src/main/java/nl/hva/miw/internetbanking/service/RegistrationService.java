package nl.hva.miw.internetbanking.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import nl.hva.miw.internetbanking.data.dto.RegistrationDto;
import nl.hva.miw.internetbanking.data.repository.NaturalPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@AllArgsConstructor
public class RegistrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);
    private final NaturalPersonRepository naturalPersonRepository;
    private RegistrationDto registrationDto;

    public void saveNaturalPerson() {
        naturalPersonRepository.save(registrationDto.getPersonPrivate());
    }

}
