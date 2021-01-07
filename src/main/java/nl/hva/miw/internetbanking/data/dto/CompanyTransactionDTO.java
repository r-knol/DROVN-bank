package nl.hva.miw.internetbanking.data.dto;

public class CompanyTransactionDTO {

    private String companyName;
    private long numberOfTransactions;

    public CompanyTransactionDTO(String companyName, long numberOfTransactions) {
        this.companyName = companyName;
        this.numberOfTransactions = numberOfTransactions;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public void setNumberOfTransactions(long numberOfTransactions) {
        this.numberOfTransactions = numberOfTransactions;
    }
}
