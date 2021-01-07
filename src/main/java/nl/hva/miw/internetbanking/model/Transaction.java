package nl.hva.miw.internetbanking.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Transaction implements Serializable {

    private long transactionID;
    private String debitAccount;
    private String creditAccount;
    private double amount;
    private String description;
    private LocalDateTime date;
    private Account account;

    public Transaction(String debitAccount, String creditAccount, double amount, String description, LocalDateTime date, Account account) {
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.account = account;
    }

    public Transaction(long transactionID, String debitAccount, String creditAccount, double amount, String description, LocalDateTime date) {
        this.transactionID = transactionID;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public void addTransactionToAccount (Account account) {
        this.account = account;
    }

    public Transaction(long transactionID){
        this.transactionID = transactionID;
    }

    public Transaction() {
    }

    public long getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(long transactionID) {
        this.transactionID = transactionID;
    }

    public String getDebitAccount() {
        return debitAccount;
    }

    public void setDebitAccount(String debetAccountNo) {
        this.debitAccount = debetAccountNo;
    }

    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccountNo(String creditAccountNo) {
        this.creditAccount = creditAccountNo;
    }

    public double getAmount() {
        if (account.getIban().equals(debitAccount)) {
            amount = 0 - amount;
        }
        if(account.getIban().equals(creditAccount)) {

        }
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

//    public double getTransactionAmount (double amount) {
//        if (account.getIban().equals(debitAccount)) {
//             amount = 0 - amount;
//        }
//        if(account.getIban().equals(creditAccount)) {
//
//        }
//        return amount;
//    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", debetAccountNo='" + debitAccount + '\'' +
                ", creditAccountNo='" + creditAccount + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
