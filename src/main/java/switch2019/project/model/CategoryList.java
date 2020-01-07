package switch2019.project.model;

import java.util.HashSet;
import java.util.Objects;

public class CategoryList {
    // Private instance variables
    private HashSet<Category> categories;

    /**
     * Constructor for Category List
     */

    public CategoryList() {
        categories = new HashSet<Category>();
    }

    /**
     *Develop @override of equals for Category List and @override of hashcode
     */

    /**
     * method to get Categories inside a CategoryList
     * @return categoriesList Clone
     */

    public CategoryList getCategories() {return this;}

    /**
     * Method to check the number of Categories inside the list.
     */
    public int howManyCategories() {return 0;}

    /**
     * Method to check if a Category already exists in List;
     */

    public boolean categoryListContains(Category aCategory) {return true;}
    /**
     *Develop @override of equals for Category List and @override of hashcode
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryList categoriesList = (CategoryList) o;
        return Objects.equals(categories, categoriesList.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categories);
    }
}
