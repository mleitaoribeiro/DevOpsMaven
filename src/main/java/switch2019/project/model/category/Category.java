package switch2019.project.model.category;

import switch2019.project.model.shared.Denomination;

import java.util.Objects;

public class Category {
    //Private instance variables
    private Denomination nameOfCategory;

    /**
     * Category constructor
     *
     * @param category
     */
    public Category(String category) {
        setNameOfCategory(category);
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
        return nameOfCategory.equals(category1.nameOfCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfCategory);
    }
}
