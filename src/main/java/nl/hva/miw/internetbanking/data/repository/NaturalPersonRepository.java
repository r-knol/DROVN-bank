package nl.hva.miw.internetbanking.data.repository;

import nl.hva.miw.internetbanking.data.dao.NaturalPersonDAO;
import nl.hva.miw.internetbanking.exception.NaturalPersonNotFoundException;
import nl.hva.miw.internetbanking.model.NaturalPerson;
import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class NaturalPersonRepository implements Repository<NaturalPerson, Long> {

    private final NaturalPersonDAO naturalPersonDAO;

    public NaturalPersonRepository(NaturalPersonDAO naturalPersonDAO) {
        this.naturalPersonDAO = naturalPersonDAO;
    }

    @Override
    public List<NaturalPerson> findAll() {
        Optional<List<NaturalPerson>> naturalPersonOptional = naturalPersonDAO.list();
        return naturalPersonOptional.orElse(new ArrayList<>());
    }

    @Override
    public NaturalPerson findById(Long id) throws NaturalPersonNotFoundException {
        Optional<NaturalPerson> naturalPersonOptional = naturalPersonDAO.read(id);
        return naturalPersonOptional.orElseThrow(() -> new NaturalPersonNotFoundException(
                "NaturalPerson with id '" + id + "' not found!"));
    }

    @Override
    public void save(NaturalPerson entity) throws DuplicateKeyException {
        naturalPersonDAO.create(entity);
    }

    @Override
    public void update(NaturalPerson entity) {
        naturalPersonDAO.update(entity);
    }

    @Override
    public void deleteById(Long id) {
        naturalPersonDAO.delete(id);
    }

}
