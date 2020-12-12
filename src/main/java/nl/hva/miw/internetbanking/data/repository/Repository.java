package nl.hva.miw.internetbanking.data.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {

    List<T> findAll();

    Optional<T> findById(ID id);

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    void deleteById(ID id);

}
