package nl.hva.miw.internetbanking.data.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {

    List<T> findAll();

    T findById(ID id);

    Optional<ID> save(T entity);

    void update(T entity);

    void deleteById(ID id);

}
