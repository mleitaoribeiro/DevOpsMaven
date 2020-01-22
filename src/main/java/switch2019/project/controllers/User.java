package switch2019.project.controllers;

import java.time.LocalDateTime;
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
     * @param category
     */
    public boolean addCategoryToList(String category){
        return person.createCategoryAndAddToCategoryList(category);
    }


    /**
     * Remove a category from CategoryList
     *
     * @param category category
     */
    public boolean removeCategoryFromList(String category){
        return person.removeCategoryFromList(category);
    }


    /**
     * Add multiple categories to CategoryList
     *
     * @param categories<Category> categories
     */

    public boolean addMultipleCategoriesToList(HashSet<String> categories){
        return person.createAndAddMultipleCategoriesToList(categories);
    }

   /* *//**
     * Remove multiple categories from CategoryList
     *
     * @param categories<Category> categories
     *//*
    public boolean removeMultipleCategoriesToList(HashSet<Category> categories){
        return person.removeMultipleCategoriesToList(categories);

    }

    *//**Method to get the numbers of Categories in the Category List
     *
     *//*
    public int numberOfCategoryInTheCategoryList() {
        return person.numberOfCategoryInTheCategoryList();
    }
*/
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

    public boolean createTransaction(MonetaryValue amount, String description, LocalDateTime localDate, Category category, Account accountFrom, Account accountTo, boolean type){
        return person.createTransaction(amount, description, localDate, category, accountFrom, accountTo, type);
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

}
