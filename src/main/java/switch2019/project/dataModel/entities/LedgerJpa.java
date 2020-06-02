package switch2019.project.dataModel.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "ledger")
public class LedgerJpa {

    @Id
    @Column(name = "ledger_id")
    private String owner;
    private String creationDate;

    @OneToMany(mappedBy="ledger", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TransactionJpa> transactions;

    protected LedgerJpa() {}

    public LedgerJpa (String owner, String creationDate) {
        this.owner = owner;
        this.creationDate = creationDate;
        transactions = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        LedgerJpa that = (LedgerJpa) obj;
        return Objects.equals(owner, that.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, creationDate);
    }

    //GETTERS:

    public String getOwner() {
        return owner;
    }

    public String getCreationDate() {
        return creationDate;
    }

    //SETTERS:

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }


    @Override
    public String toString() {
        return owner + ", " + creationDate;
    }

    /**
     * Method that adds a transactionJpa to the LedgerJpa
     * @param transactionJpa
     */

    public void addTransaction(TransactionJpa transactionJpa) {
        transactions.add(transactionJpa);
    }

    /**
     * Method used to obtain all the TransactionsJpa on the LedgerJpa
     * @return
     */

    public List<TransactionJpa> getTransactionsFromLedgerJpa() {
        return new ArrayList<>(transactions);
    }
}
