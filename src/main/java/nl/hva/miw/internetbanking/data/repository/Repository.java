package nl.hva.miw.internetbanking.data.repository;

import java.util.List;

public interface Repository<T, ID> {

    List<T> findAll();

    T findById(ID id);

    void save(T entity);

    void update(T entity);

    void deleteById(ID id);

}
