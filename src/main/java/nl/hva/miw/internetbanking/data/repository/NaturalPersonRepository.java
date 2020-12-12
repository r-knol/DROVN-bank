package nl.hva.miw.internetbanking.data.repository;

import nl.hva.miw.internetbanking.data.dao.NaturalPersonDAO;
import nl.hva.miw.internetbanking.model.NaturalPerson;

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
        return naturalPersonOptional.orElse(List.of()); // returns immutable list
    }

    @Override
    public Optional<NaturalPerson> findById(Long id) {
        return naturalPersonDAO.read(id);
    }

    @Override
    public void save(NaturalPerson entity) {
        naturalPersonDAO.create(entity);
    }

    @Override
    public void update(NaturalPerson entity) {
        naturalPersonDAO.update(entity);
    }

    @Override
    public void delete(NaturalPerson entity) {
        naturalPersonDAO.delete(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        naturalPersonDAO.delete(id);
    }

}
