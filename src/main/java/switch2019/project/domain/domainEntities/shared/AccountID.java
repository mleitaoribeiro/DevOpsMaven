package switch2019.project.domain.domainEntities.shared;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;

import javax.persistence.Embeddable;
import javax.persistence.IdClass;
import javax.persistence.SecondaryTable;
import java.io.Serializable;
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

    /**
     * Method to return Denomination
     *
     * @return denomination
     */
    public String getDenomination() {
        return denomination.toString();
    }

    /**
     * Method to return ownerID
     *
     * @return ownerID
     */
    public OwnerID getOwnerID() {
        return ownerID;
    }



}
