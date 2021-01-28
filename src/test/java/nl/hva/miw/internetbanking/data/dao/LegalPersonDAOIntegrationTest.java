package nl.hva.miw.internetbanking.data.dao;

import nl.hva.miw.internetbanking.model.LegalPerson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class LegalPersonDAOIntegrationTest extends AbstractDAOIntegrationTest {
    
    private LegalPersonDAO legalPersonDAO;
    
    @Autowired
    LegalPersonDAOIntegrationTest(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        this.legalPersonDAO = new LegalPersonDAO(jdbcTemplate);
    }
    
    @Test
    void read() {
        Optional<LegalPerson> legalPersonOptional = legalPersonDAO.read(4002L);
        assertThat(legalPersonOptional).isPresent();
        LegalPerson legalPerson = legalPersonOptional.get();
        assertThat(legalPerson.getCustomerID()).isEqualTo(4002L);
        assertThat(legalPerson.getCompanyName()).isEqualTo("Secure Computing");
        // etcetera
        
    }
    
}