package switch2019.project.model.shared;

import switch2019.project.model.frameworks.ID;
import switch2019.project.model.frameworks.Owner;

import java.util.Objects;

public class CategoryID implements ID {

    private Denomination denomination;
    private ID id;

    /**
     * CategoryID constructor
     */
    public CategoryID(Denomination denomination, Owner owner) {
        this.denomination = denomination;
        id = owner.getID();
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
                Objects.equals(id, that.id);
    }

    /**
     * Override to hashCode
     */
    @Override
    public int hashCode() {
        return Objects.hash(denomination, id);
    }

}
