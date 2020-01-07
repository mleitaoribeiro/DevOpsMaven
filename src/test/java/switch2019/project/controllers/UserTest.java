package switch2019.project.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.Address;
import switch2019.project.model.Category;
import switch2019.project.model.CategoryList;
import switch2019.project.model.Person;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void addCategoryToList() {
        //Arrange
        Person person1 = new Person("Alexandre", 4, 3, 1996, new Address("Porto"));
        User user1 = new User(person1);

        Category category1 = new Category("School expenses");

        //Act
        user1.addCategoryToList(category1);

        //Assert

    }

    @Test
    void removeCategoryFromList() {
        //Arrange
        Person person1 = new Person("Alexandre", 4, 3, 1996, new Address("Porto"));
        User user1 = new User(person1);
        Category category1 = new Category("School expenses");
        Category category2 = new Category("Health expenses");

        //Act
        user1.addCategoryToList(category1);
        user1.addCategoryToList(category2);
        user1.removeCategoryFromList(category1);

        //Assert
    }

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Main Scenario")
    void addMultipleCategoriesToList_mainScenario() {
        // Arrange

        //Initialize user
        Person person1 = new Person("Alexandre", 4, 3, 1996, new Address("Porto"));
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

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if null category is not added")
    void addMultipleCategoriesToList_WithANullCase() {
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

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if the same Category is not added simultaneously")
    void addMultipleCategoriesToList_WithTwoCategoriesThatAreTheSame() {
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
    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if the same Category is not added simultaneously " +
            "Ignore letter capitalization and special characters ")
    void addMultipleCategoriesToList_WithTwoCategoriesCaseInsensitive() {
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

        Category categoryCar= new Category("Beauty");
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

        Category categoryHealthLowerCase= new Category("Beauty");
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


}