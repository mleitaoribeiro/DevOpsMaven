package switch2019.project.dataModel.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "transactions")
public class TransactionJpa {

    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transaction_id")
    private TransactionIDJpa transactionIDJpa;
    private Double amount;
    private String currency;
    private String description;
    private String date;
    private String category;
    private String accountFrom;
    private String accountTo;
    private String type;

    protected TransactionJpa() {
    }

    public TransactionJpa(long id,String ledger_id, Double amount, String currency, String description, String date, String category, String accountFrom, String accountTo, String type) {
       transactionIDJpa=new TransactionIDJpa(id, ledger_id);
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.date = date;
        this.category = category;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionJpa that = (TransactionJpa) o;
        return Objects.equals(transactionIDJpa, that.transactionIDJpa);

    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionIDJpa);
    }

    public TransactionIDJpa getTransactionIDJpa() {
        return transactionIDJpa;
    }

    public void setTransactionIDJpa(TransactionIDJpa transactionIDJpa) {
        this.transactionIDJpa = transactionIDJpa;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
