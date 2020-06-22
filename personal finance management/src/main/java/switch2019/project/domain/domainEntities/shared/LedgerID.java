package switch2019.project.domain.domainEntities.shared;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;

import java.util.Objects;

public class LedgerID implements ID {

    private final OwnerID ownerID;

    public LedgerID(OwnerID ownerID) {
        this.ownerID = ownerID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LedgerID)) return false;
        LedgerID ledgerID = (LedgerID) o;
        return Objects.equals(ownerID, ledgerID.ownerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ownerID);
    }

    public OwnerID getOwnerID() {
        return ownerID;
    }
}
