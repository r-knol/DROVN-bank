package nl.hva.miw.internetbanking.data.repository;

import nl.hva.miw.internetbanking.data.dao.CustomrDAO;
import nl.hva.miw.internetbanking.model.Customer;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class CustomerRepository implements Repository<Customer, Long> {

    private final CustomrDAO customerDAO;

    public CustomerRepository(CustomrDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public List<Customer> findAll() {
        Optional<List<Customer>> optionalCustomers = customerDAO.list();
        return optionalCustomers.orElse(List.of()); // returns immutable list
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerDAO.read(id);
    }

    @Override
    public void save(Customer entity) {
        customerDAO.create(entity);
    }

    @Override
    public void update(Customer entity) {
        customerDAO.update(entity);
    }

    @Override
    public void delete(Customer entity) {
        customerDAO.delete(entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        customerDAO.delete(id);
    }

}
