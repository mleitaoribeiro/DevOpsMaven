package switch2019.project.dataModel.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "transactions")
public class TransactionJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    @Column(name = "ledger_id")
    private LedgerIdJpa ledgerIdJpa;
    //@OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "ledger_id", referencedColumnName = "id")
    //private LedgerJpa ledger;
    private Double amount;
    private String currency;
    private String description;
    private String date;
    private String category;
    private String accountFrom;
    private String accountTo;
    private String type;

    protected TransactionJpa() {}

    public TransactionJpa(String ledgerId, Double amount, String currency, String description, String date,
                          String category, String accountFrom, String accountTo, String type) {
        this.ledgerIdJpa = new LedgerIdJpa(ledgerId);
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
        return Objects.equals(id, that.id) &
                Objects.equals(ledgerIdJpa, that.ledgerIdJpa);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ledgerIdJpa);
    }

    public long getId() { return id;}

    public String getLedgerIdJpaToString() { return ledgerIdJpa.toString(); }

    public LedgerIdJpa getLedgerIdJpa() { return ledgerIdJpa; }

    public void setLedgerIdJpa(LedgerIdJpa ledgerIdJpa) { this.ledgerIdJpa = ledgerIdJpa; }

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
