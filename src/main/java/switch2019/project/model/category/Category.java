package switch2019.project.model.category;

import java.text.Normalizer;
import java.util.Objects;

public class Category {
    //Private instance variables
    private String nameOfCategory;

    /**
     * Category constructor
     *
     * @param category
     */
    public Category(String category) {
        setNameOfCategory(category);
    }

    /**
     * toString method for Category
     */
    @Override
    public String toString() {
        return nameOfCategory;
    }

    /**
     * setter for Category Name -> with input validation
     *
     * @param newCategory
     */

    public void setNameOfCategory(String newCategory) {
        if (newCategory == null) {
            throw new IllegalArgumentException("The category description is not valid or it's missing. Please try again.");
        } else {
            newCategory = removeAccents(newCategory);
            nameOfCategory = newCategory.toUpperCase();
        }
    }


    /**
     * Develop method to remove accents from categories
     *
     * @param text
     * @return text without accents
     */
    private String removeAccents(String text) {
        text = Normalizer.normalize(text, Normalizer.Form.NFD);
        text = text.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        return text;
    }

    /**
     * Get Category name
     *
     * @return category Clone
     */

    public String getNameOfCategory() {
        return this.nameOfCategory;
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
