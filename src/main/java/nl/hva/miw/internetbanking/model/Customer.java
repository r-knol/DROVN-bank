package nl.hva.miw.internetbanking.model;

public class Customer {

    private long customerID;
    private String userName;
    private String password;

    public Customer(long id, String username, String password) {
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

    @Override
    public String toString() {
        return "Customer{" +
                "customerID=" + customerID +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
