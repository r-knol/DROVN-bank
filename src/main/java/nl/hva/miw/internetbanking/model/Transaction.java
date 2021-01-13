package nl.hva.miw.internetbanking.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public Transaction(String debitAccount, String creditAccount, double amount, String description, LocalDateTime date) {
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.amount = amount;
        this.description = description;
        this.date = date;
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

    public void setCreditAccount(String creditAccountNo) {
        this.creditAccount = creditAccountNo;
    }

    public double transactionAmount() {
        if (account.getIban().equals(debitAccount)) {
            amount = 0 - amount;
        }
        if(account.getIban().equals(creditAccount)) {

        }
        return amount;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String showDate() {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("d MMMM yyyy");
        return date.format(formatDate);
    }

    public String showDateTime() {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm aaa");
        return date.format(formatDateTime);
    }

    public String showContraAccount() {
        if (account.getIban().equals(debitAccount)) {
            return creditAccount;
        } else
            return debitAccount;
    }

    public String showAmount() {
        if (transactionAmount() > 0) {
            return String.format("+%.2f", transactionAmount());
        } else {
            return String.format("-%.2f", transactionAmount());
        }
    }

    public String showDetails() {
        return String.format("Datum: %s\nOmschrijving: %s\nIBAN: %s", showDateTime(), description, showContraAccount());
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", debitAccountNo='" + debitAccount + '\'' +
                ", creditAccountNo='" + creditAccount + '\'' +
                ", TegenrekeningNaam='" + showContraAccount() +'\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + showDate() +
                '}';
    }
}
