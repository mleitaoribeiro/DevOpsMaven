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
     * Method to check the number of Categories inside the list.
     */

    public int howManyCategories() {
       return this.categories.size();
    }

    /**
     * Method to check if a Category already exists in List;
     */

    public boolean categoryListContains(Category aCategory) {
        return this.categories.contains(aCategory);
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

    public void addCategoryToCategoryList(Category newCategory) {
        if(newCategory != null)
            categories.add(newCategory);
    }

    /**
     * Remove a category from CategoryList
     *
     * @param categoryToRemove
     */

    public void removeCategoryFromList(Category categoryToRemove){
        if( categoryToRemove != null && categoryListContains(categoryToRemove))
            categories.remove(categoryToRemove);
    }
    /**
     * Add multiple categories to CategoryList
     *
     * @param categories<Category> categories
     */

    public void addMultipleCategoriesToList(HashSet<Category> categories){
        for (Category category : categories) {
                this.addCategoryToCategoryList(category);
            }

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
}
