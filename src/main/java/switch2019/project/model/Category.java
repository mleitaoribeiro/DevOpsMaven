package switch2019.project.model;

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
     * setter for Category Name -> with input validation
     *
     * @param newCategory
     */

    public void setNameOfCategory(String newCategory) {
        if (newCategory == null) {
            throw new IllegalArgumentException ("The category description is not valid or it's missing. Please try again.");
        } else {
            newCategory = removeAccents(newCategory);
            nameOfCategory = newCategory.toUpperCase();
        }
    }

    private String removeAccents(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        return texto;
    }

    /**
     * Get Category
     *
     * @return category Clone
     */

    public String getNameOfCategory() {
        return this.nameOfCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category1 = (Category) o;
        return nameOfCategory.equals(category1.nameOfCategory);
    }

    @Override
    public int hashCode() { return Objects.hash(nameOfCategory); }
}
