package nl.hva.miw.internetbanking.data.repository;

import lombok.AllArgsConstructor;
import nl.hva.miw.internetbanking.data.dao.CustomrDAO;
import nl.hva.miw.internetbanking.exception.CustomerNotFoundException;
import nl.hva.miw.internetbanking.model.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
@AllArgsConstructor
public class CustomerRepository implements Repository<Customer, Long> {

    private final CustomrDAO customerDAO;

    @Override
    public List<Customer> findAll() {
        Optional<List<Customer>> optionalCustomers = customerDAO.list();
        // If customers found, return list of them, else return empty list
        return optionalCustomers.orElse(new ArrayList<>());
    }

    @Override
    public Customer findById(Long id) throws CustomerNotFoundException {
        Optional<Customer> optionalCustomer = customerDAO.read(id);
        return optionalCustomer.orElseThrow(() -> new CustomerNotFoundException(
                "Customer with id '" + id + "' not found!"));
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
    public void deleteById(Long id) {
        customerDAO.delete(id);
    }

}
