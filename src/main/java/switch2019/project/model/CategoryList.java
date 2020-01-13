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
     * Method get the category List.
     * @return categoryList Clone
     */

    public HashSet<Category> getCategoriesList(){
        return new HashSet<>(categories);
    }

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

    /**
     * Add a new category to CategoryList
     *
     * @param newCategory
     */

    public boolean addCategoryToCategoryList(Category newCategory) {
        if(newCategory != null) {
            return categories.add(newCategory);
        }
        else return  false;
    }

    /**
     * Remove a category from CategoryList
     *
     * @param categoryToRemove
     */

    public void removeCategoryFromList(Category categoryToRemove){
        if( categoryToRemove != null)
            categories.remove(categoryToRemove);
    }
    /**
     * Add multiple categories to CategoryList
     *
     * @param categories<Category> categories
     */

    public boolean addMultipleCategoriesToList(HashSet<Category> categories){
        for (Category category : categories) {
            this.addCategoryToCategoryList(category);
        }
        return this.categories.containsAll(categories);
    }

    /**
     * Remove multiple categories from CategoryList
     *
     * @param categories<Category> categories
     */

    public void removeMultipleCategoriesToList(HashSet<Category> categories){
        for (Category category : categories) {
            this.removeCategoryFromList(category);
        }

    }

    /**
     * Validate if a category is in the CategoryList
     *
     * @param category<Category> category to test
     */

    public boolean validateIfCategoryIsInTheCategoryList (Category category){

        //WRITE CODE HERE

        return true;
    }
}
