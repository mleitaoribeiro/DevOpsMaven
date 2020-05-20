package switch2019.project.dataModel.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TransactionIDJpa implements Serializable {

    private int id;
    private String ledger_id;

    protected TransactionIDJpa() {
    }

    public TransactionIDJpa(int id, String ledger_id) {
        this.id = id;
        this.ledger_id = ledger_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionIDJpa that = (TransactionIDJpa) o;
        return id == that.id &&
                Objects.equals(ledger_id, that.ledger_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ledger_id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLedger_id() {
        return ledger_id;
    }

    public void setLedger_id(String ledger_id) {
        this.ledger_id = ledger_id;
    }
}
