package nl.hva.miw.internetbanking.model;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {

    private long transactionID;
    private String debetAccount;
    private String creditAccount;
    private double amount;
    private String description;
    private Date date;

    public Transaction(String debetAccount, String creditAccount, double amount, String description, Date date) {
        this.debetAccount = debetAccount;
        this.creditAccount = creditAccount;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public Transaction(long transactionID, String debetAccount, String creditAccount, double amount, String description, Date date) {
        this.transactionID = transactionID;
        this.debetAccount = debetAccount;
        this.creditAccount = creditAccount;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }
    public Transaction(long transactionID){
        this.transactionID = transactionID;
    }

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public String getDebetAccount() {
        return debetAccount;
    }

    public void setDebetAccount(String debetAccountNo) {
        this.debetAccount = debetAccountNo;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccountNo(String creditAccountNo) {
        this.creditAccount = creditAccountNo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", debetAccountNo='" + debetAccount + '\'' +
                ", creditAccountNo='" + creditAccount + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
