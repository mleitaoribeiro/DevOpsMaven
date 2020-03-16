package switch2019.project.repository;

import switch2019.project.model.category.Category;
import java.util.*;

public class CategoryRepository implements Repository{
    // Private instance variables
    private Set<Category> categories;

    /**
     * Constructor for Category List
     */

    public CategoryRepository() {
        categories = new HashSet<>();
    }

    /**
     * Develop @override of toString method for CategoryList
     *
     * @return String with the categories in the CategoryList
     */
    @Override
    public String toString() {
        List<String> categoriesToString = new ArrayList();
        for (Category category : categories) {
            categoriesToString.add(category.getNameOfCategory());
        }
        return "CategoryList: " + categoriesToString;
    }

    /**
     * Develop @override of equals for Category List and @override of hashcode
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        CategoryRepository categoriesList = (CategoryRepository) o;
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

    public boolean removeCategoryFromList(String categoryToRemove) {
        Category category = new Category(categoryToRemove);
        if (this.categories.contains(category)) {
            return categories.remove(category);
        }
        return false;
    }

    /**
     * Add multiple categories to CategoryList
     *
     * @param categories<Category> categories
     */

    public boolean addMultipleCategoriesToList(Set<String> categories) {
        int sizeBefore = this.categories.size();
        for (String category : categories) {
            this.addCategoryToCategoryList(category);
        }
        return this.categories.size() == sizeBefore + categories.size();
    }

    /**
     * Remove multiple categories from CategoryList
     *
     * @param categories<Category> categories
     */

    public boolean removeMultipleCategoriesToList(Set<String> categories) {
        for (String category : categories)
            this.removeCategoryFromList(category);
        return !this.categories.containsAll(categories);
    }

    /**
     * Validate if a category is in the CategoryList
     *
     * @param category<Category> category to test
     */

    public boolean validateIfCategoryIsInTheCategoryList(Category category) {
        return categories.contains(category);
    }

    /**
     * Validate if a set of categories is in the CategoryList
     *
     * @param setOfCategories
     */

    public boolean validateIfSetOfCategoriesIsInTheCategoryList(Set<String> setOfCategories) {
        Set<Category> list = new HashSet<>();
        for (String category : setOfCategories) {
            list.add(new Category(category));
        }
        return this.categories.containsAll(list);
    }

    /**
     * Method to get the numbers of Categories in the Category List
     */

    public int numberOfCategoryInTheCategoryList() {
        return this.categories.size();
    }
}
