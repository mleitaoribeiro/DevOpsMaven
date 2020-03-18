package switch2019.project.model.shared;

import switch2019.project.model.frameworks.ID;
import switch2019.project.model.frameworks.OwnerID;

import java.util.Objects;

public class CategoryID implements ID {

    private final Denomination denomination;
    private final OwnerID ownerID;

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
     * Method to return Denomination
     *
     * @return denomination
     */

    public String getDenomination() {
        return denomination.toString();
    }

}
