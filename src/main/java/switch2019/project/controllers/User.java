package switch2019.project.controllers;

import java.util.HashSet;

import switch2019.project.model.*;

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
     * @param category category
     */
    public void addCategoryToList(Category category){
        person.addCategoryToCategoryList(category);
    }


    /**
     * Remove a category from CategoryList
     *
     * @param category category
     */
    public void removeCategoryFromList(Category category){
        person.removeCategoryFromList(category);
    }


    /**
     * Add multiple categories to CategoryList
     *
     * @param categories<Category> categories
     */

    public void addMultipleCategoriesToList(HashSet<Category> categories){
        person.addMultipleCategoriesToList(categories);
    }

    /**
     * Remove multiple categories from CategoryList
     *
     * @param categories<Category> categories
     */
    public void removeMultipleCategoriesToList(HashSet<Category> categories){
        person.removeMultipleCategoriesToList(categories);

    }

    /**
     * Develop method to create a new transaction (USER STORY)
     *
     * @param amount
     * @param description
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     */

    public void createTransaction(MonetaryValue amount, String description, Category category, Account accountFrom, Account accountTo, Type type){
        person.createTransaction(amount, description, category, accountFrom, accountTo, type);
    }

    /**
     * Develop method to create a new Account (USER STORY)
     *
     * @param accountDenomination
     * @param accountDescription
     */

    public void createAccount (String accountDenomination, String accountDescription) {
        person.createAccount(accountDenomination, accountDescription);

    }

    /**
     * Develop method to create a new Account to the group (USER STORY 7)
     * @param accountDenomination
     * @param accountDescription
     * @param group1
     */
    public void createGroupAccount (String accountDenomination, String accountDescription, Group group1) {
        person.createGroupAccount(accountDenomination, accountDescription, group1);
    }

    /**
     * Develop method to create a new Account (USER STORY)
     *
     * @return Person
     */

    public Person getPerson() {
        return person.getPerson();
    }
}
