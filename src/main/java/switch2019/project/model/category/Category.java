package switch2019.project.model.category;

import switch2019.project.model.frameworks.Entity;
import switch2019.project.model.frameworks.ID;
import switch2019.project.model.frameworks.Owner;
import switch2019.project.model.frameworks.OwnerID;
import switch2019.project.model.shared.CategoryID;
import switch2019.project.model.shared.Denomination;

import java.util.Objects;

public class Category implements Entity {
    //Private instance variables
    private CategoryID categoryID;
    private Denomination nameOfCategory;

    /**
     * Category constructor (to delete later)
     * @param category
     */
    public Category(String category) {
        if (category != null) {
            nameOfCategory = new Denomination(category);
        } else throw new IllegalArgumentException("The category description is not valid or it's missing. Please try again.");
    }

    /**
     * 2nd Category constructor
     * @param category
     * @param ownerID
     */
    public Category(Denomination category, OwnerID ownerID) {
        categoryID = new CategoryID(category, ownerID);
    }

    /**
     * Get account by ID
     */
    public CategoryID getID() {
        return categoryID;
    }


    /**
     * Get name of the category
     * @return nameOfCategory
     */
    public String getNameOfCategory() {
        return nameOfCategory.toString();
    }

    /**
     * Develop @override of equals for Category and @override of hashcode
     *
     * @param o
     * @return true if equal
     */
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

    /**
     * Override to toString
     * @return category string
     */
    @Override
    public String toString() {
        return categoryID.toString();
    }
}
