package nl.hva.miw.internetbanking.model;

import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
public class Customer {

    private long customerID;
    private String userName;
    private String password;
    private CustomerType customerType;

    public Customer(String userName, String password, CustomerType customerType) {
        this.userName = userName;
        this.password = password;
        this.customerType = customerType;
    }

    public Customer(long customerID, String userName, String password, CustomerType customerType) {
        this.customerID = customerID;
        this.userName = userName;
        this.password = password;
        this.customerType = customerType;
    }

    public Customer(long id) {
        this.customerID = id;
    }

    public long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(long customerID) {
        this.customerID = customerID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

}
