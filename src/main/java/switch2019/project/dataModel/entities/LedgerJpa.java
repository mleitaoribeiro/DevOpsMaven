package switch2019.project.dataModel.entities;

import switch2019.project.domain.domainEntities.ledger.Ledger;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

@Entity(name = "ledger")
public class LedgerJpa {

    @EmbeddedId
    @Column(name = "ledger_id")
    //Primary Key:
    private LedgerIdJpa ledgerIdJpa;

    private String creationDate;

    protected LedgerJpa() {}

    public LedgerJpa (LedgerIdJpa ledgerIdJpa, String creationDate) {
        this.ledgerIdJpa = ledgerIdJpa;
        this.creationDate = creationDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) return false;
        LedgerJpa that = (LedgerJpa) obj;
        return Objects.equals(ledgerIdJpa, that.ledgerIdJpa) &&
                Objects.equals(creationDate, that.creationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ledgerIdJpa, creationDate);
    }
    
}
