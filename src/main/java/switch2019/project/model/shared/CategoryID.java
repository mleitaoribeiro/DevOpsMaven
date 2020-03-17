package switch2019.project.model.shared;

import switch2019.project.model.frameworks.ID;
import switch2019.project.model.frameworks.OwnerID;

import java.util.Objects;

public class CategoryID implements ID {

    private Denomination denomination;
    private OwnerID ownerID;

    /**
     * CategoryID constructor
     */
    public CategoryID(Denomination denomination, OwnerID ownerID) {
        if (denomination != null & ownerID != null){
            this.denomination = denomination;
            this.ownerID = ownerID;
        }
        else throw new IllegalArgumentException("The denomination and ownerID can't be null.");
    }

    /**
     * Method to return Denomination
     *
     * @return denomination
     */
    public String getDenomination() {
        return denomination.toString();
    }

    /**
     * Override to Equals method
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryID that = (CategoryID) o;
        return Objects.equals(denomination, that.denomination) &&
                Objects.equals(ownerID, that.ownerID);
    }

    /**
     * Override to hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(denomination, ownerID);
    }

}
