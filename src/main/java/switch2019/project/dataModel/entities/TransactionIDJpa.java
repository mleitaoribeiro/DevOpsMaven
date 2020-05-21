package switch2019.project.dataModel.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TransactionIDJpa implements Serializable {

    private long id;
    private String ledgerId;

    protected TransactionIDJpa() {
    }

    public TransactionIDJpa(long id, String ledgerId) {
        this.id = id;
        this.ledgerId = ledgerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionIDJpa that = (TransactionIDJpa) o;
        return id == that.id &&
                Objects.equals(ledgerId, that.ledgerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ledgerId);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLedger_id() {
        return ledgerId;
    }

    public void setLedger_id(String ledger_id) {
        this.ledgerId = ledger_id;
    }
}
