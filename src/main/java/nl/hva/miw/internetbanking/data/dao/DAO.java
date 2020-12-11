package nl.hva.miw.internetbanking.data.dao;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Optional;

public interface DAO<T, ID> {

    ID create(T entity) throws NullPointerException, SQLIntegrityConstraintViolationException;

    Optional<T> read(ID id);

    void update(T entity);

    void delete(ID id);

    Optional<List<T>> list();

}
