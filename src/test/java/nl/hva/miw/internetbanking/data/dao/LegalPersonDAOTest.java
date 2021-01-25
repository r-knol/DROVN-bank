package nl.hva.miw.internetbanking.data.dao;

import lombok.extern.slf4j.Slf4j;
import nl.hva.miw.internetbanking.model.LegalPerson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
class LegalPersonDAOTest extends AbstractDAOTest {
    
    private LegalPersonDAO legalPersonDAO;
    
    @Autowired
    LegalPersonDAOTest(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
        this.legalPersonDAO = new LegalPersonDAO(jdbcTemplate);
    }
    
    @Test
    void create() {
    
    }
    
    @Test
    void read() {
        LegalPerson legalPerson = legalPersonDAO.read(4002L).get();
        log.info(legalPerson.toString());
    }
    
    @Test
    void update() {
    }
    
    @Test
    void delete() {
    }
    
    @Test
    void deleteById() {
    }
    
    @Test
    void list() {
    }
    
    @Test
    void readByKvkNumber() {
    }
}