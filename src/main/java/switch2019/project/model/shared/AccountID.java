package switch2019.project.model.shared;

import switch2019.project.model.frameworks.ID;
import switch2019.project.model.frameworks.OwnerID;

import java.util.Objects;

public class AccountID implements ID {

    // Private AccountID attributes
    private Denomination denomination;
    private OwnerID ownerID;

    /**
     * AccountID constructor
     */
    public AccountID(Denomination denomination, OwnerID ownerID) {
        this.denomination = denomination;
        this.ownerID = ownerID;
    }

    /**
     * Method to return Denomination
     * @return denomination
     */
    public String getDenomination() {
        return denomination.toString();
    }

    /**
     * Override to equals method
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountID accountID = (AccountID) o;
        return Objects.equals(denomination, accountID.denomination) &&
                Objects.equals(ownerID, accountID.ownerID);
    }

    /**
     * Override to hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(denomination, ownerID);
    }
}
