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

    public String getDenominationString() {
        return denomination.toString();
    }

    public String getOwnerIDString() { return ownerID.toString(); }

    public Denomination getDenomination() {
        return denomination;
    }

    @Override
    public String toString() {
        return denomination.toString() + ", " + ownerID.toString();
    }

    public OwnerID getOwnerID() {
        return ownerID;
    }

    public CategoryID getCopyOfCategory() { return new CategoryID(this.getDenomination(), this.getOwnerID()); }
}
