package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.*;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void addCategoryToListMainScenario() {
        //Arrange

        //Initialize user
        Person person1 = new Person("Alexandre", 1996, 3, 4, new Address("Porto"));
        User user1 = new User(person1);

        //Category to be included in Category List
        Category category1 = new Category("School expenses");

        //Act
        user1.addCategoryToList(category1);

        //Assert
        //assertEquals(user1.getCategoriesList(), new Category("School Expenses"));


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if null category is not added")
    void addCategoryToListWithANullCase() {
        //Arrange

        //Initialize user
        Person person1 = new Person("Alexandre", 1996, 3, 4, new Address("Porto"));
        User user1 = new User(person1);

        //Category to be included in Category List
        Category category1 = null;

        //Act
        user1.addCategoryToList(category1);

        //Assert
        //assertTrue(user1.getCategoriesList.equals());

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if the same Category is not added simultaneously")
    void addTwoCategoriesToListWithTwoCategoriesThatAreTheSame() {
        //Arrange

        //Initialize user
        Person person1 = new Person("Alexandre", 1996, 3, 4, new Address("Porto"));
        User user1 = new User(person1);

        //Categories to be included in Category List
        Category category1 = new Category("School expenses");
        Category category2 = new Category("School expenses");

        //Act
        user1.addCategoryToList(category1);
        user1.addCategoryToList(category2);

        //Assert
        //assertTrue(user1.getCategoriesList.equals(category1));

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if the same Category is not added simultaneously " +
            "Ignore letter capitalization and special characters ")
    void addTwoCategoriesToListWithTwoCategoriesCaseInsensitive() {
        //Arrange

        //Initialize user
        Person person1 = new Person("Alexandre", 1996, 3, 4, new Address("Porto"));
        User user1 = new User(person1);

        //Categories to be included in Category List
        Category category1 = new Category("School expenses");
        Category category2 = new Category("SCHOOL expenses");

        //Act
        user1.addCategoryToList(category1);
        user1.addCategoryToList(category2);

        //Assert
        //assertTrue(user1.getCategoriesList.equals(category1));


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    void removeCategoryFromListMainScenario() {
        //Arrange

        //Initialize user
        Person person1 = new Person("Alexandre", 1996, 3, 4, new Address("Porto"));
        User user1 = new User(person1);

        //Categories to be included in Category List
        Category category1 = new Category("School expenses");
        Category category2 = new Category("Health expenses");
        user1.addCategoryToList(category1);
        user1.addCategoryToList(category2);

        //Act
        user1.removeCategoryFromList(category1);

        //Assert
        //assertTrue(user1.getCategorias.equals(new Category("School expenses")));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Remove a Set of Categories from user Category List - try to remove a set of Categories that does not " +
            "or null")
    void removeCategoriesToListWithANullCase() {
        //Arrange

        //Initialize user
        Person person1 = new Person("Alexandre", 1996, 3, 4, new Address("Porto"));
        User user1 = new User(person1);

        //Categories to be included in Category List
        Category category1 = new Category("School expenses");
        user1.addCategoryToList(category1);

        //Act
        category1 = null;
        user1.removeCategoryFromList(category1);

        //Assert
        //assertTrue(user1.getCategoriesList.equals(new Category("School expenses")));


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Remove a Category from user's Category List - Ignore letter capitalization and special characters")
    void removeCategoryFromListIgnoreLettersFormatAndSpecialCase() {
        //Arrange

        //Initialize user
        Person person1 = new Person("Alexandre", 1996, 3, 4, new Address("Porto"));
        User user1 = new User(person1);

        // Categories to be included in Category List
        Category category1 = new Category("School expenses");
        Category category2 = new Category("ScHOÓL eXpenSÉs");
        user1.addCategoryToList(category1);

        //Act
        user1.removeCategoryFromList(category2);

        //Assert
        //assertTrue(user1.categories.size(), 0);


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Main Scenario")
    void addMultipleCategoriesToListMainScenario() {
        // Arrange

        //Initialize user
        Person person1 = new Person("Alexandre", 1996, 3, 4, new Address("Porto"));
        User user1 = new User(person1);

        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryGym = new Category("Gym");
        Category categoryUniversity = new Category("University");

        //Act

        // set of Categories to be added to categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryUniversity));
        //Category - add several categories to the user Category List with method
        user1.addMultipleCategoriesToList(setOfCategories);


        //Assert
        // assertTrue(user123.getCategories().containsAll(setOfCategories));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if null category is not added")
    void addMultipleCategoriesToListWithANullCase() {
        // Arrange

        //Initialize user
        Person person1 = new Person("Marta", 1995, 4, 12, new Address("Porto"));
        User user1 = new User(person1);

        // Categories to be included in Category List
        Category categoryBets = new Category("Bets and Games");
        Category categoryNull = new Category(null);
        Category categoryBeauty = new Category("Beauty");

        //Act

        // set of Categories to be added to categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryBets, categoryNull, categoryBeauty));
        //Category - add several categories to the user Category List with method
        user1.addMultipleCategoriesToList(setOfCategories);

        //Assert
        // assertTrue(user123.getCategories().containsAll(setOfCategories));
        //assertFalse(user123.getCategories().contains(categoryNull));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if the same Category is not added simultaneously")
    void addMultipleCategoriesToListWithTwoCategoriesThatAreTheSame() {
        // Arrange

        //Initialize user
        Person person1 = new Person("Marta", 1995, 4, 12, new Address("Porto"));
        User user1 = new User(person1);

        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryHealthDuplicated = new Category("Health");
        Category categoryBeauty = new Category("Beauty");

        //Act

        // set of Categories to be added to categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryHealthDuplicated, categoryBeauty));
        //The user adds several categories to his Category List with method
        user1.addMultipleCategoriesToList(setOfCategories);

        // int expectedNumberOfCategoriesOfList = user1.howManyCategories();

        // assertEquals(2,expectedNumberOfCategoriesOfList);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if the same Category is not added simultaneously " +
            "Ignore letter capitalization and special characters ")
    void addMultipleCategoriesToListWithTwoCategoriesCaseInsensitive() {
        // Arrange

        //Initialize user
        Person person1 = new Person("Marta", 1995, 4, 12, new Address("Porto"));
        User user1 = new User(person1);

        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryHealthDuplicated = new Category("heálth");
        Category categoryBeauty = new Category("Beauty");

        //Act

        // set of Categories to be added to categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryHealthDuplicated, categoryBeauty));
        //The user adds several categories to his Category List with method
        user1.addMultipleCategoriesToList(setOfCategories);

        // int expectedNumberOfCategoriesOfList = user1.howManyCategories();

        // assertEquals(2,expectedNumberOfCategoriesOfList);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Remove a Set of Categories from user Category List - Main Scenario")
    void removeMultipleCategoriesToList_MainScenario() {
        // Arrange

        //Initialize user
        Person person1 = new Person("Marta", 1995, 4, 12, new Address("Porto"));
        User user1 = new User(person1);

        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryGym = new Category("Gym");
        Category categoryBeauty = new Category("Beauty");

        //Act

        // set of Categories to be added to Categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        user1.addMultipleCategoriesToList(setOfCategories);

        //set of Categories to be removed from Categories List
        HashSet<Category> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryBeauty, categoryGym));
        user1.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        // int expectedNumberOfCategoriesOfFinalList = user1.howManyCategories();

        // assertEquals(1,expectedNumberOfCategoriesOfFinalList);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Remove a Set of Categories from user Category List - try to remove a set of Categories that does not " +
            "or null")
    void removeMultipleCategoriesToList_exceptionCase() {
        // Arrange

        //Initialize user
        Person person1 = new Person("Marta", 1995, 4, 12, new Address("Porto"));
        User user1 = new User(person1);

        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryGym = new Category("Gym");
        Category categoryBeauty = new Category("Beauty");

        Category categoryCar = new Category("Beauty");
        Category categoryNull = new Category(null);
        Category categoryUniversity = new Category("University");

        //Act

        // set of Categories to be added to Categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        user1.addMultipleCategoriesToList(setOfCategories);

        //set of Categories to be removed from Categories List
        HashSet<Category> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryCar, categoryNull, categoryUniversity));
        user1.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        // int expectedNumberOfCategoriesOfFinalList = user1.howManyCategories();

        // assertEquals(3,expectedNumberOfCategoriesOfFinalList);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    @DisplayName("Remove a Set of Categories from user Category List - Ignore letter capitalization and special characters")
    void removeMultipleCategoriesToList_ignoreLettersFormatAndSpecialCase() {
        // Arrange

        //Initialize user
        Person person1 = new Person("Marta", 1995, 4, 12, new Address("Porto"));
        User user1 = new User(person1);

        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryGym = new Category("Gym");
        Category categoryBeauty = new Category("Beauty");

        Category categoryHealthLowerCase = new Category("Beauty");
        Category categoryGymSpecialCharacter = new Category("Gým");
        Category categoryBeautyUpperCase = new Category("BEAUTY");

        //Act

        // set of Categories to be added to Categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        user1.addMultipleCategoriesToList(setOfCategories);

        //set of Categories to be removed from Categories List
        HashSet<Category> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryHealthLowerCase, categoryGymSpecialCharacter, categoryBeautyUpperCase));
        user1.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        // int expectedNumberOfCategoriesOfFinalList = user1.howManyCategories();

        // assertEquals(0,expectedNumberOfCategoriesOfFinalList);
    }

    /**
     * Test if a transaction was created
     */

    @Test
    @DisplayName("Test if a transaction was created - success case")
    void createTransactionSuccessCase() {
        //Arrange
        Person person1 = new Person("Jose", 1996, 04, 02, new Address("Lisboa"));
        User user1 = new User (person1);
        MonetaryValue amount = new MonetaryValue(20, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("Transports");
        user1.addCategoryToList (category);

        Account from = new Account("Wallet","General expenses");
        Account to = new Account("TransportAccount","Transport expenses");

        user1.getPerson().createAccount("Wallet","General expenses");
        user1.getPerson().createAccount("TransportAccount","Transport expenses");

        Type type = new Type(false); //debit

        //Act
        user1.createTransaction(amount,description,category,from,to,type);

        //Assert
        assertEquals(1, user1.getPerson().getLedger().size());
    }

    @Test
    @DisplayName("Test if a transaction was created - category is not in the list")
    void createTransactionCategoryisNotinTheList() {
        //Arrange
        Person person1 = new Person("Jose", 1996, 04, 02, new Address("Lisboa"));
        User user1 = new User (person1);
        MonetaryValue amount = new MonetaryValue(22, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = null;
        Account from = new Account("Wallet","General expenses");
        Account to = new Account("TransportAccount","Transport expenses");
        Type type = new Type(false); //debit

        //Act
        user1.createTransaction(amount,description,category,from,to,type);

        //Assert
        assertEquals(0, user1.getPerson().getLedger().size());
    }

    @Test
    @DisplayName("Test if a transaction was created - account is not in the list")
    void createTransactionAccountIsNotInTheList() {
        //Arrange
        Person person1 = new Person("Jose", 1996, 04, 02, new Address("Lisboa"));
        User user1 = new User (person1);
        MonetaryValue amount = new MonetaryValue(22, Currency.getInstance("EUR"));
        String description = "payment";
        Category category = new Category("Transports");
        Account from = null;
        Account to = new Account("TransportAccount","Transport expenses");
        Type type = new Type(false); //debit

        //Act
        user1.createTransaction(amount,description,category,from,to,type);

        //Assert
        assertEquals(0, user1.getPerson().getLedger().size());
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Test if a Account was created
     */

    @Test
    @DisplayName("Test if a Account was created - sucess case")
    void createAccountSucessCase() {
        // Arrange

        //Initialize user
        Person onePerson = new Person("João", 1993, 9, 1, new Address("Porto"));
        User oneUser = new User(onePerson);

        String accountDenomination = "Wallet";
        String accountDescription = "General expenses";

        int expectAccountsListSize = 1;

        //Act
        oneUser.createAccount(accountDenomination,accountDescription);


        int realAccountsListSize = onePerson.getAccountsList().size();

        // assert
        assertEquals(expectAccountsListSize, realAccountsListSize);
    }

}