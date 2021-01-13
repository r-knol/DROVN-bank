package nl.hva.miw.internetbanking.data.dto;

import nl.hva.miw.internetbanking.model.Customer;

public class CustomerNameDTO {

    private Customer customer;
    private String customerName;

    public CustomerNameDTO(Customer customer) {
        this.customer = customer;
    }


}
