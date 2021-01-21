package nl.hva.miw.internetbanking.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Transaction implements Serializable {

    private long transactionID;
    private String debitAccount;
    private String creditAccount;
    private double amount;
    private String description;
    private LocalDateTime dateTime;
    private LocalDate date;
    private Account account;
    private long numberOfTransactions;
    private List<String> contraAccountHolderNames;

    public Transaction(String debitAccount, String creditAccount, double amount, String description, LocalDateTime dateTime, Account account) {
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.amount = amount;
        this.description = description;
        this.dateTime = dateTime;
        this.date = dateTime.toLocalDate();
        this.account = account;
        this.contraAccountHolderNames = new ArrayList<>();
    }

    public Transaction(String debitAccount, String creditAccount, double amount, String description, LocalDateTime dateTime) {
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.amount = amount;
        this.description = description;
        this.dateTime = dateTime;
        this.date = dateTime.toLocalDate();
        this.contraAccountHolderNames = new ArrayList<>();
    }

    public Transaction(long transactionID, String debitAccount, String creditAccount, double amount, String description, LocalDateTime dateTime) {
        this.transactionID = transactionID;
        this.debitAccount = debitAccount;
        this.creditAccount = creditAccount;
        this.amount = amount;
        this.description = description;
        this.dateTime = dateTime;
        this.date = dateTime.toLocalDate();
        this.contraAccountHolderNames = new ArrayList<>();
    }

    public Transaction(long numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }

    public void addTransactionToAccount(Account account) {
        this.account = account;
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
        if (account.getIban().equals(creditAccount)) {
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDate getDate() {

        setDate(dateTime.toLocalDate());
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = dateTime.toLocalDate();
    }

    public List<String> getContraAccountHolderNames() {
        return contraAccountHolderNames;
    }

    public void setContraAccountHolderNames(List<String> contraAccountHolderNames) {
        this.contraAccountHolderNames = contraAccountHolderNames;
    }

    public void addDebetAccountHolderName(String name) {
        contraAccountHolderNames.add(name);
    }

    public String convertDateToString() {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("d MMMM yyyy");
        return date.format(formatDate);
    }

    public String showDateTime() {
        DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("d MMMM yyyy HH:mm");
        return dateTime.format(formatDateTime);
    }

    public String showContraAccount() {
        if (account.getIban().equals(debitAccount)) {
            return creditAccount;
        } else
            return debitAccount;
    }

    public String showAmount() {
        if (transactionAmount() > 0) {
            return String.format("€ +%.2f", transactionAmount());
        } else {
            return String.format("€ -%.2f", transactionAmount());
        }
    }

    public String showTransactionDetails() {
        return String.format("TransactieNo: %d\n\tTegenrekening: %s\n\tDatum/tijd: %s\n\tOmschrijving: %s",
                transactionID, showContraAccount(), showDateTime(),
                description);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionID=" + transactionID +
                ", debitAccount='" + debitAccount + '\'' +
                ", creditAccount='" + creditAccount + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", date as String=" + convertDateToString() +
                ", contraAccountHolderNames=" + contraAccountHolderNames +
                '}';
    }
}
