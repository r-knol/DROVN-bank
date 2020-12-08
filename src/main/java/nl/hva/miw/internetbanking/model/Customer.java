package nl.hva.miw.internetbanking.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long customerID;
    private String userName;
    private String password;

    public Customer(int id, String username, String password) {
        this.customerID = id;
        this.userName = username;
        this.password = password;
    }

    public Customer() {
    }

    public long getId() {
        return customerID;
    }

    public void setId(int id) {
        this.customerID = id;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
