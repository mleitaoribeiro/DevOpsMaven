package switch2019.project.model.category;

import switch2019.project.model.shared.Denomination;

import java.text.Normalizer;
import java.util.Objects;

public class Category {
    //Private instance variables
    private Denomination nameOfCategory;

    /**
     * Category constructor
     *
     * @param category
     */
    public Category(Denomination category) {
        nameOfCategory = category;
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
