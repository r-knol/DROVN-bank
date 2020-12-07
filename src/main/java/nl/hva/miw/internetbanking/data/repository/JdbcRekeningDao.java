package nl.hva.miw.internetbanking.data.repository;

import nl.hva.miw.internetbanking.data.dao.RekeningDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class JdbcRekeningDao implements RekeningDao {

    private Logger logger = LoggerFactory.getLogger(JdbcRekeningDao.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRekeningDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        logger.warn("New JdbcRekeningDao.");
    }

//    @Override
//    public Rekening zoekRekOpKlant(Klant klant) {
//        return null;
//    }
}
