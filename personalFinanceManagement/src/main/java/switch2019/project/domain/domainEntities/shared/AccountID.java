package switch2019.project.domain.domainEntities.shared;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;

import java.util.Objects;

public class AccountID implements ID {

    // Private AccountID attributes
    private final Denomination denomination;
    private final OwnerID ownerID;

    public AccountID(Denomination denomination, OwnerID ownerID) {
        if (denomination == null || ownerID == null)
            throw new IllegalArgumentException("Neither the Denomination nor OwnerID can be null.");
        else {
            this.denomination = denomination;
            this.ownerID = ownerID;
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountID accountID = (AccountID) o;
        return Objects.equals(denomination, accountID.denomination) &&
                Objects.equals(ownerID, accountID.ownerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, ownerID);
    }

    @Override
    public String toString() { return  denomination.toString() + ", " + ownerID.toString(); }

    public String getDenominationToString() {
        return denomination.toString();
    }

    public Denomination getDenomination() {
        return denomination;
    }

    public OwnerID getOwnerID() {
        return ownerID;
    }

    public AccountID getCopyOfAccountID() {
        return new AccountID(new Denomination(this.getDenominationToString()), this.getOwnerID());
    }

}
