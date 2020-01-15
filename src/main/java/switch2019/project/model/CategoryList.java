package switch2019.project.model;

import java.util.Arrays;
import java.util.Collections;
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
     * @param nameOfCategory
     */

    public boolean addCategoryToCategoryList(String nameOfCategory) {
        Category newCategory = new Category(nameOfCategory);
        return categories.add(newCategory);
    }

    /**
     * Remove a category from CategoryList
     *
     * @param categoryToRemove
     */

    public boolean removeCategoryFromList(Category categoryToRemove){
        if( categoryToRemove != null)
            return categories.remove(categoryToRemove);
        else
            return false;
    }
    /**
     * Add multiple categories to CategoryList
     *
     * @param categories<Category> categories
     */

    public boolean addMultipleCategoriesToList(HashSet<String> categories){
        for (String category : categories) {
            this.addCategoryToCategoryList(category);
        }
        return this.categories.containsAll(categories);
    }

    /**
     * Remove multiple categories from CategoryList
     *
     * @param categories<Category> categories
     */

    public boolean removeMultipleCategoriesToList(HashSet<Category> categories){
        for (Category category : categories)
            this.removeCategoryFromList(category);
        return !this.categories.containsAll(categories);
    }

    /**
     * Validate if a category is in the CategoryList
     *
     * @param category<Category> category to test
     */

    public boolean validateIfCategoryIsInTheCategoryList (Category category){
        return categories.contains(category);
    }

    /**
     * Validate if a set of categories is in the CategoryList
     *
     * @param setOfCategories
     */

    public boolean validateIfSetOfCategoriesIsInTheCategoryList(HashSet<String> setOfCategories){
        HashSet<String> list = new HashSet<>(setOfCategories);
        return this.categories.containsAll(list);
    }

    /**Method to get the numbers of Categories in the Category List
     *
     */

    public int numberOfCategoryInTheCategoryList() {
        return this.categories.size();
    }
}
