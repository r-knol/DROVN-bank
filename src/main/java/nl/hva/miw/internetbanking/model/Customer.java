package nl.hva.miw.internetbanking.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer implements Serializable {

    public static final String EMPTY_STRING = "";
    public static final long ZERO = 0;

    private long customerID;
    private String userName;
    private String password;
    private List<Account> accounts;
    private CustomerType customerType;

    public Customer(String userName, String password, CustomerType customerType) {
        this.userName = userName;
        this.password = password;
        this.accounts = new ArrayList<>();
        this.customerType = customerType;
    }

    public Customer(long customerID, String userName, String password, CustomerType customerType) {
        this.customerID = customerID;
        this.userName = userName;
        this.password = password;
        this.customerType = customerType;
        this.accounts = new ArrayList<>();
    }

    public Customer(long id) {
        this.customerID = id;
    }

    public void addAccount(Account acc) {
        accounts.add(acc);
    }

}
