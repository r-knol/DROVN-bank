package nl.hva.miw.internetbanking.service;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.data.dto.RegistrationDto;
import nl.hva.miw.internetbanking.data.repository.CustomerRepository;
import nl.hva.miw.internetbanking.data.repository.NaturalPersonRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j(topic = "RegistrationService")
public class RegistrationService {

    private final NaturalPersonRepository naturalPersonRepository;
    private final CustomerRepository customerRepository;
    private RegistrationDto registrationDto;

    public RegistrationService(NaturalPersonRepository naturalPersonRepository,
                               CustomerRepository customerRepository,
                               RegistrationDto registrationDto) {
        this.naturalPersonRepository = naturalPersonRepository;
        this.customerRepository = customerRepository;
        this.registrationDto = registrationDto;
    }

    public void registerNaturalPerson() {
        registerCustomer();
        registrationDto.getPersonPrivate().setId((int) registrationDto.getCustomer().getId());
        naturalPersonRepository.save(registrationDto.getPersonPrivate());
    }

    private void registerCustomer() {
        customerRepository.save(registrationDto.getCustomer());
    }

    // TODO: Implement method
    public void registerLegalPerson() {

    }

    public RegistrationDto getRegistrationDto() {
        RegistrationDto registrationDto = this.registrationDto;
        return registrationDto; // Now not returning direct reference to instance variable
    }

    public void setRegistrationDto(RegistrationDto registrationDto) {
        this.registrationDto = registrationDto;
    }

}
