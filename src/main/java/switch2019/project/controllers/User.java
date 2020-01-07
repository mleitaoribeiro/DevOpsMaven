package switch2019.project.controllers;

import java.util.HashSet;
import switch2019.project.model.Category;
import switch2019.project.model.Person;
import switch2019.project.model.CategoryList;

public class User {

    private Person person;

    /**
     * Default Person constructor
     *
     * @param person
     */

    public User (Person person){
        this.person = person;
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
     * @param HashSet<Category> categories
     */

    public void addMultipleCategoriesToList(HashSet<Category> categories){

    }


    /**
     * Remove multiple categories from CategoryList
     *
     * @param HashSet<Category> categories
     */
    public void removeMultipleCategoriesToList(HashSet<Category> categories){

    }

}
