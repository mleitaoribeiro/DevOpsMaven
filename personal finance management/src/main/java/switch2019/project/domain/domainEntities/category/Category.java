package switch2019.project.domain.domainEntities.category;

import switch2019.project.domain.domainEntities.frameworks.Entity;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.shared.CategoryID;
import switch2019.project.domain.domainEntities.shared.Denomination;

import java.util.Objects;

public class Category implements Entity {

    //Private instance variables
    private final CategoryID categoryID;

    /**
     * 2nd Category constructor
     *
     * @param category
     * @param ownerID
     */
    public Category(Denomination category, OwnerID ownerID) {
        categoryID = new CategoryID(category, ownerID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category1 = (Category) o;
        return categoryID.equals(category1.categoryID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryID);
    }

    @Override
    public String toString() {
        return categoryID.toString();
    }

    /**
     * Get category by ID
     */
    public CategoryID getID() {
        return categoryID;
    }

    /**
     * Get category by Owner ID
     */

    public OwnerID getOwnerID() {
        return this.categoryID.getOwnerID();
    }


    /**
     * Get name of the category
     *
     * @return nameOfCategory
     */
    public String getNameOfCategory() {
        return categoryID.getDenominationString();
    }
}


