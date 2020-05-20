package switch2019.project.dataModel.entities;

import javax.persistence.*;

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

    public TransactionJpa(int id, Double amount, String currency, String description, String date, String category, String accountFrom, String accountTo, String type) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.date = date;
        this.category = category;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.type = type;
    }

}
