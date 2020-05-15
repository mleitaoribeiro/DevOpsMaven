package switch2019.project.domain.domainEntities.shared;

import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;

import java.util.Objects;

public class CategoryID implements ID {

    private Denomination denomination;
    private OwnerID ownerID;

    protected CategoryID() {};

    public CategoryID(Denomination denomination, OwnerID ownerID) {
        if (denomination == null || ownerID == null) {
            throw new IllegalArgumentException("Neither the Denomination nor OwnerID can be null.");
        } else {
            this.denomination = denomination;
            this.ownerID = ownerID;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryID that = (CategoryID) o;
        return Objects.equals(denomination, that.denomination) &&
                Objects.equals(ownerID, that.ownerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(denomination, ownerID);
    }

    /**
     * Method to return the Denomination string
     *
     * @return denomination
     */

    public String getDenominationString() {
        return denomination.toString();
    }

    /**
     * Method to return OwnerID string
     * @return
     */

    public String getOwnerIDString() { return ownerID.toString(); }


    /**
     * Method to return the Denomination
     *
     * @return denomination
     */
    public Denomination getDenomination() {
        return denomination;
    }

    /**
     * Override
     * @return denomination toString
     */
    @Override
    public String toString() {
        return denomination.toString() + ", " + ownerID.toString();
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
