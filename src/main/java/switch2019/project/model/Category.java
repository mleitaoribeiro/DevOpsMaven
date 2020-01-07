package switch2019.project.model;

import java.util.Objects;

public class Category {
    //Private instance variables
    private String category;

    /**
     * Category constructor
     *
     * @param category
     */
    public Category(String category) {

    }

    /**
     * setter for Category Name
     *
     * @param newCategory
     */

    public void setCategory(String newCategory) {
    }

    /**
     * Get Category
     *
     * @return category clone
     */

    public Category geCategory() {
        return this;
    }

    /**
     * override of equals for Category Instance and @override hashcode
     *
     * @param o
     * @return boolean
     */

    @Override
    public boolean equals(Object o) {
      return true;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
