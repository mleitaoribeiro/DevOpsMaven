package switch2019.project.controllers;

import java.util.HashSet;
import switch2019.project.model.Category;
import switch2019.project.model.CategoryList;

public class User {

    private String username;

    /**
     * Default Person constructor
     *
     * @param username
     */

    public User (String username){
        this.username = username.toUpperCase();
    }


    /**
     * Add a new category to CategoryList
     *
     * @param category
     */
    public void addCategoryToList(Category category){

    }


    /**
     * Remove a category from CategoryList
     *
     * @param category
     */
    public void removeCategoryFromList(Category category){

    }


    /**
     * Add multiple categories to CategoryList
     *
     * @param categoryList
     */

    public void addMultipleCategoriesToList(CategoryList categoryList){

    }


    /**
     * Remove multiple categories from CategoryList
     *
     * @param categoryList
     */
    public void removeMultipleCategoriesToList(CategoryList categoryList){

    }

}
