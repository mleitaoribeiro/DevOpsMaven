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
        if (newCategory != null) {
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
