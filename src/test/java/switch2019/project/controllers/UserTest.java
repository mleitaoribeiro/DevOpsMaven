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
    @DisplayName("Check if a category was added to Category List - Main Scenario")
    void addCategoryToListMainScenario() {
        //Arrange
        //Initialize user
        Person person1 = new Person("Alexandre", 1996, 3, 4, new Address("Porto"));
        User user1 = new User(person1);

        //Category to be included in Category List
        Category category1 = new Category("School expenses");

        //Act
        boolean realResult = user1.addCategoryToList(category1);

        //Assert
        assertTrue(realResult);


    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Check if null category is not added")
    void addCategoryToListWithANullCase() {
        //Arrange

        //Initialize user
        Person person1 = new Person("Alexandre", 1996, 3, 4, new Address("Porto"));
        User user1 = new User(person1);

        //Category to be included in Category List
        Category category1 = null;

        //Act
        boolean realResult = user1.addCategoryToList(category1);

        //Assert
        assertFalse(realResult);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Check if the same Category is not added simultaneously")
    void addTwoCategoriesToListWithTwoCategoriesThatAreTheSame() {
        //Arrange

        //Initialize user
        Person person1 = new Person("Alexandre", 1996, 3, 4, new Address("Porto"));
        User user1 = new User(person1);

        //Categories to be included in Category List
        Category category1 = new Category("School expenses");
        Category category2 = new Category("School expenses");

        //Act
        boolean realResult =  user1.addCategoryToList(category1) && !user1.addCategoryToList(category2);

        //Assert
        assertTrue(realResult);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Check if the same Category is not added simultaneously - Ignore letter capitalization and special characters ")
    void addTwoCategoriesToListWithTwoCategoriesCaseInsensitive() {
        //Arrange

        //Initialize user
        Person person1 = new Person("Alexandre", 1996, 3, 4, new Address("Porto"));
        User user1 = new User(person1);

        //Categories to be included in Category List
        Category category1 = new Category("School expenses");
        Category category2 = new Category("SCHOóL expenses");

        //Act
       boolean realResult = user1.addCategoryToList(category1) && !user1.addCategoryToList(category2);

        //Assert
      assertTrue(realResult);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Remove categories from User Category List - Main Scenario")
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
       boolean realResult = user1.removeCategoryFromList(category1);

        //Assert
        assertTrue(realResult);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("To Try to remove a set of Categories that does not exist or null")
    void removeCategoriesToListWithANullCase() {
        //Arrange

        //Initialize user
        Person person1 = new Person("Alexandre", 1996, 3, 4, new Address("Porto"));
        User user1 = new User(person1);

        //Categories to be included in Category List
        Category category1 = new Category("School expenses");
        Category category2 = null;

        user1.addCategoryToList(category1);
        user1.addCategoryToList(category2);

        //Act
        boolean realResult = user1.removeCategoryFromList(category1);

        //Assert
        assertTrue(realResult);

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
       boolean realResult = user1.removeCategoryFromList(category2);

        //Assert
        assertTrue(realResult);
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
        boolean realResult = user1.addMultipleCategoriesToList(setOfCategories);


        //Assert
        assertTrue(realResult);
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
        Category categoryNull = null;
        Category categoryBeauty = new Category("Beauty");

        //Act

        // set of Categories to be added to categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryBets, categoryNull, categoryBeauty));
        //Category - add several categories to the user Category List with method
        user1.addMultipleCategoriesToList(setOfCategories);

        boolean realResult = !user1.addCategoryToList(categoryNull);

        //Assert
        assertTrue(realResult);
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

        boolean realResult = user1.addMultipleCategoriesToList(setOfCategories);
        //Assert
        assertTrue(realResult);

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
        boolean realResult =  user1.addMultipleCategoriesToList(setOfCategories);

        //Assert
        assertTrue(realResult);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Remove a Set of Categories from user Category List - Main Scenario")
    void removeMultipleCategoriesToListMainScenario() {
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
        boolean realResult = user1.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        //Assert
        assertTrue(realResult);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test
    @DisplayName("Remove a Set of Categories from user Category List - try to remove a set of Categories that does not " +
            "or null")
    void removeMultipleCategoriesToListExceptionCase() {
        // Arrange

        //Initialize user
        Person person1 = new Person("Marta", 1995, 4, 12, new Address("Porto"));
        User user1 = new User(person1);

        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryGym = new Category("Gym");
        Category categoryBeauty = new Category("Beauty");

        Category categoryCar = new Category("Car");
        Category categoryNull = null;
        Category categoryUniversity = new Category("University");

        //Act

        // set of Categories to be added to Categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        user1.addMultipleCategoriesToList(setOfCategories);

        //set of Categories to be removed from Categories List
        HashSet<Category> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryCar, categoryNull, categoryUniversity));


        boolean realResult = user1.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        //Assert
        assertTrue(realResult);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Test
    @DisplayName("Remove a Set of Categories from user Category List - Ignore letter capitalization and special characters")
    void removeMultipleCategoriesToListIgnoreLettersFormatAndSpecialCase() {
        // Arrange

        //Initialize user
        Person person1 = new Person("Marta", 1995, 4, 12, new Address("Porto"));
        User user1 = new User(person1);

        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryGym = new Category("Gym");
        Category categoryBeauty = new Category("Beauty");

        Category categoryHealthLowerCase = new Category("health");
        Category categoryGymSpecialCharacter = new Category("Gým");
        Category categoryBeautyUpperCase = new Category("BEAUTY");

        //Act

        // set of Categories to be added to Categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        user1.addMultipleCategoriesToList(setOfCategories);

        //set of Categories to be removed from Categories List
        HashSet<Category> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryHealthLowerCase, categoryGymSpecialCharacter, categoryBeautyUpperCase));


        boolean realResult = user1.removeMultipleCategoriesToList(setOfCategoriesToRemove);
        //Assert
        assertTrue(realResult);
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
        Type type = new Type(false); //debit

        Category category = new Category("General");
        user1.addCategoryToList (category);

        Account from = new Account("Wallet","General expenses");
        Account to = new Account("TransportAccount","Transport expenses");

        user1.createAccount("Wallet","General expenses");
        user1.createAccount("TransportAccount","Transport expenses");

        //Act
        boolean result = user1.createTransaction(amount,description,category,from,to,type);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if a transaction was created - category is not in the list")
    void createTransactionCategoryisNotinTheList() {
        //Arrange
        Person person1 = new Person("Jose", 1996, 04, 02, new Address("Lisboa"));
        User user1 = new User (person1);
        MonetaryValue amount = new MonetaryValue(22, Currency.getInstance("EUR"));
        String description = "payment";
        Type type = new Type(false); //debit

        Category categoryFood = new Category("food");
        Category categoryBaby = new Category("baby");
        Category categoryHome = new Category("home");
        user1.addMultipleCategoriesToList(new HashSet<>(Arrays.asList(categoryFood, categoryBaby)));

        Account from = new Account("Wallet","General expenses");
        Account to = new Account("TransportAccount","Transport expenses");
        user1.createAccount("Wallet", "General expenses");
        user1.createAccount("TransportAccount","Transport expenses");

        //Act
        boolean categoryInTheList = user1.createTransaction(amount,description,categoryFood,from,to,type);
        boolean categoryNotInTheList = user1.createTransaction(amount,description,categoryHome,from,to,type);

        //Assert
        assertTrue(categoryInTheList && !categoryNotInTheList);
    }

    @Test
    @DisplayName("Test if a transaction was created - account is not in the list")
    void createTransactionAccountIsNotInTheList() {
        //Arrange
        Person person1 = new Person("Jose", 1996, 04, 02, new Address("Lisboa"));
        User user1 = new User (person1);
        MonetaryValue amount = new MonetaryValue(22, Currency.getInstance("EUR"));
        String description = "payment";
        Type type = new Type(false); //debit

        Category category = new Category("General");
        user1.addCategoryToList(category);

        Account accountWallet = new Account("Wallet", "General expenses");
        Account accountTransport = new Account("Transport","Transport expenses");
        Account accountBaby = new Account("Baby","Baby expenses");
        user1.createAccount("Wallet", "General expenses");
        user1.createAccount("Transport","Transport expenses");

        //Act
        boolean accountInTheList = user1.createTransaction(amount,description,category,accountWallet,accountTransport,type);
        boolean accountNotInTheList = user1.createTransaction(amount,description,category,accountWallet,accountBaby,type);

        //Assert
        assertTrue(accountInTheList && !accountNotInTheList);
    }

    @Test
    @DisplayName("Test if a transaction was created - monetary value is negative")
    void createTransactionAccountNegativeMonetaryValue() {
        //Arrange
        Person person1 = new Person("Jose", 1996, 04, 02, new Address("Lisboa"));
        User user1 = new User(person1);
        MonetaryValue amountPositive = new MonetaryValue(50, Currency.getInstance("EUR"));
        MonetaryValue amountNegative = new MonetaryValue(-50, Currency.getInstance("EUR"));
        String description = "payment";
        Type type = new Type(false); //debit

        Category category = new Category("General");
        user1.addCategoryToList(category);

        Account accountWallet = new Account("Wallet", "General expenses");
        Account accountTransport = new Account("Transport", "Transport expenses");
        user1.createAccount("Wallet", "General expenses");
        user1.createAccount("Transport", "Transport expenses");

        //Act
        boolean monetaryValuePositive = user1.createTransaction(amountPositive, description, category, accountWallet, accountTransport, type);
        boolean monetaryValueNegative = user1.createTransaction(amountNegative, description, category, accountWallet, accountTransport, type);

        //Assert
        assertTrue(monetaryValuePositive && !monetaryValueNegative);
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

        //Act
        boolean real = oneUser.createAccount(accountDenomination,accountDescription);

        // assert
        assertTrue(real);
    }

}