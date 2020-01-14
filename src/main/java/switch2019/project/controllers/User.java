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
    public boolean addCategoryToList(Category category){
        return person.addCategoryToCategoryList(category);
    }


    /**
     * Remove a category from CategoryList
     *
     * @param category category
     */
    public boolean removeCategoryFromList(Category category){
        return person.removeCategoryFromList(category);
    }


    /**
     * Add multiple categories to CategoryList
     *
     * @param categories<Category> categories
     */

    public boolean addMultipleCategoriesToList(HashSet<Category> categories){
        return person.addMultipleCategoriesToList(categories);
    }

    /**
     * Remove multiple categories from CategoryList
     *
     * @param categories<Category> categories
     */
    public boolean removeMultipleCategoriesToList(HashSet<Category> categories){
        return person.removeMultipleCategoriesToList(categories);

    }

    /**Method to get the numbers of Categories in the Category List
     *
     */
    public int numberOfCategoryInTheCategoryList() {
        return person.numberOfCategoryInTheCategoryList();
    }

    /**
     * Develop method to create a new transaction | USER STORY - 08:
     * 
     * As a user I want to create a transaction
     * giving it a monetary value, a date (current, by default),
     * a description, a category,
     * a credit account and a debit account.
     *
     * @param amount
     * @param description
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     */

    public boolean createTransaction(MonetaryValue amount, String description, Category category, Account accountFrom, Account accountTo, Type type){
        return person.createTransaction(amount, description, category, accountFrom, accountTo, type);
    }

    /**
     * Develop method to create a new Account | USER STORY - 06:
     *
     * As a user, I want to create an account for myself by
     * giving it a name and description so that
     * it can later be used in my movements.
     *
     * @param accountDenomination
     * @param accountDescription
     */

    public boolean createAccount (String accountDenomination, String accountDescription) {
        return person.createAccount(accountDenomination, accountDescription);
    }

    /**
     * Develop method to create a new Account to the group (USER STORY 7)
     * @param accountDenomination
     * @param accountDescription
     * @param group1
     */
    public void createGroupAccount (String accountDenomination, String accountDescription, Group group1) {
        group1.createGroupAccount(accountDenomination, accountDescription);
    }
}
