package switch2019.project.repository;

import switch2019.project.model.category.Category;
import switch2019.project.model.frameworks.ID;
import switch2019.project.model.frameworks.OwnerID;

import java.util.*;

public class CategoryRepository implements Repository {

    // Private instance variables
    private Set<Category> categories;

    //Public Constructor
    public CategoryRepository() {
        categories = new HashSet<>();
    }

    @Override
    public String toString() {
        List<String> categoriesToString = new ArrayList();
        for (Category category : categories) {
            categoriesToString.add(category.getNameOfCategory());
        }
        return "CategoryList: " + categoriesToString;
    }

    /**
     * Find category by ID
     *
     * @param categoryID
     * @return account
     */
    public Category findByID(ID categoryID) {
        for (Category category : categories) {
            if (category.getID().equals(categoryID))
                return category;
        }
        throw new IllegalArgumentException("No category found with that ID.");
    }

    /**
     * Add a new category to CategoryList
     * (ainda está aqui porque ainda não alteramos todos os testes)
     *
     * @param nameOfCategory
     */
    public boolean createCategory(String nameOfCategory) {
        Category newCategory = new Category(nameOfCategory);
        return categories.add(newCategory);
    }

    /**
     * 2nd Add a new category to CategoryList
     *
     * @param nameOfCategory
     */
    public boolean createCategory(String nameOfCategory, OwnerID ownerID) {
        Category newCategory = new Category(nameOfCategory, ownerID);
        return categories.add(newCategory);
    }

    /**
     * Remove a category from CategoryList
     *
     * @param categoryToRemove
     */
    public boolean removeCategory(String categoryToRemove, OwnerID ownerID) {
        Category category = new Category(categoryToRemove, ownerID);
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
    public boolean addMultipleCategories(Set<String> categories, OwnerID ownerID) {
        int sizeBefore = this.categories.size();
        for (String category : categories) {
            this.createCategory(category, ownerID);
        }
        return this.categories.size() == sizeBefore + categories.size();
    }

    /**
     * Remove multiple categories from CategoryList
     *
     * @param categories<Category> categories
     */
    public boolean removeMultipleCategories(Set<String> categories, OwnerID ownerID) {
        for (String category : categories)
            this.removeCategory(category, ownerID);
        return !this.categories.containsAll(categories);
    }

    /**
     * Validate if a category is in the CategoryList
     *
     * @param categoryId that we want to see if exists
     */
    public boolean isCategoryValid(ID categoryId) {
        for (Category category : categories) {
            if (category.getID().equals(categoryId))
                return true;
        }
        return false;
    }

    /**
     * Validate if a set of categories is in the CategoryList
     *
     * @param setOfCategories
     */
    public boolean isSetOfCategoriesValid(Set<String> setOfCategories, OwnerID ownerID) {
        Set<Category> list = new HashSet<>();
        for (String category : setOfCategories) {
            list.add(new Category(category, ownerID));
        }
        return this.categories.containsAll(list);
    }

    /**
     * Method to get the numbers of Categories in the Category List
     */
    public int numberOfCategoryInRepository() {
        return this.categories.size();
    }
}
