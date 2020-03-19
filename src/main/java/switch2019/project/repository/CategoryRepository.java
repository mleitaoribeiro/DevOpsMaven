package switch2019.project.repository;

import switch2019.project.model.category.Category;
import switch2019.project.model.frameworks.ID;
import switch2019.project.model.frameworks.OwnerID;
import switch2019.project.model.shared.Denomination;

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
        return "Category Repository: " + categories.toString();
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
    public boolean createCategory(Denomination nameOfCategory, OwnerID ownerID) {
        if (nameOfCategory == null) {
            throw new IllegalArgumentException ("Category could not be added to group because its Description is null");
        }
        if (this.categories.contains(new Category(nameOfCategory,ownerID))) {
            throw new IllegalArgumentException ("This category already exists and it could not be created");
        }
        else {
            Category newCategory = new Category(nameOfCategory, ownerID);
            return categories.add(newCategory);
        }
    }

    /**
     * Remove a category from CategoryList
     *
     * @param categoryToRemove
     */
    public boolean removeCategory(Denomination categoryToRemove, OwnerID ownerID) {
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
    public boolean addMultipleCategories(Set<Denomination> categories, OwnerID ownerID) {
        int sizeBefore = this.categories.size();
        for (Denomination category : categories) {
            this.createCategory(category, ownerID);
        }
        return this.categories.size() == sizeBefore + categories.size();
    }

    /**
     * Remove multiple categories from CategoryList
     *
     * @param categories<Category> categories
     */
    public boolean removeMultipleCategories(Set<Denomination> categories, OwnerID ownerID) {
        for (Denomination category : categories)
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
    public boolean isSetOfCategoriesValid(Set<Denomination> setOfCategories, OwnerID ownerID) {
        Set<Category> list = new HashSet<>();
        for (Denomination category : setOfCategories) {
            list.add(new Category(category, ownerID));
        }
        return this.categories.containsAll(list);
    }

    /**
     * Method to get the numbers of Categories in the Category List
     * @return category
     */
    public int numberOfCategoriesInRepository() {
        return this.categories.size();
    }
}
