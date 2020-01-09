package switch2019.project.model;

import java.util.Objects;
import java.text.Normalizer;

public class Category {
    //Private instance variables
    private String category;

    /**
     * Category constructor
     *
     * @param category
     */
    public Category(String category) {
        setCategory(category);
    }

    /**
     * setter for Category Name -> with input validation
     *
     * @param newCategory
     */

    public void setCategory(String newCategory) {
        if (newCategory == null) {
            throw new IllegalArgumentException ("The category description is not valid or it's missing. Please try again.");
        } else {
            newCategory = removerAcentos(newCategory);
            category = newCategory.toUpperCase();
        }

    }

    private String removerAcentos(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");

        return texto;
    }

    /**
     * Get Category
     *
     * @return category Clone
     */

    public String getCategory() {
        return this.category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category1 = (Category) o;
        return category.equals(category1.category);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
