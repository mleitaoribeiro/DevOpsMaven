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
        setNameOfCategory(category);
    }

    /**
     * 2nd Category constructor
     * @param category
     * @param ownerID
     */
    public Category(String category, OwnerID ownerID) {
        categoryID = new CategoryID(new Denomination(category), ownerID);
    }

    /**
     * Get account by ID
     */
    public ID getID() {
        return categoryID;
    }

    public void setNameOfCategory(String nameOfCategory) {
        if (nameOfCategory != null) {
            this.nameOfCategory = new Denomination(nameOfCategory);
        } else throw new IllegalArgumentException("The category description is not valid or it's missing. Please try again.");
    }

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
}
