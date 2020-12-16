package nl.hva.miw.internetbanking.data.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T, ID> {

    void create(T entity);

    Optional<T> read(ID id);

    void update(T entity);

    void delete(T entity);

    void deleteById(ID id);

    Optional<List<T>> list();

}
