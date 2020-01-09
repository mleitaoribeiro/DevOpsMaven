package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.controllers.User;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class CategoryListTest {

    @Test
    void categoryListContains() {
    }

    @Test
    void addCategoryToCategoryList() {
    }

    @Test
    void removeCategoryFromList() {
    }

    // Test adding or removing multiple categories
    @Test
    @DisplayName("Add a Set of Categories to Category List - Main Scenario")
    void addMultipleCategoriesToListMainScenario() {
        // Arrange
        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryGym = new Category("Gym");
        Category categoryUniversity = new Category("University");

        CategoryList newCategoryList = new CategoryList();

        //Act
        //A collection of categories to be added
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryUniversity));

        //Add several categories simultaneously to Category List with method under test
        newCategoryList.addMultipleCategoriesToList(setOfCategories);

        boolean validateIfTheSetOfCategoriesWasAdded = newCategoryList.getCategoriesList().containsAll(setOfCategories);

        //Assert
        assertTrue(validateIfTheSetOfCategoriesWasAdded);
    }

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if null category is not added")
    void addMultipleCategoriesToListWithANullCase() {
        // Arrange
        // Categories to be included in Category List
        Category categoryBets = new Category("Bets and Games");
        Category categoryNull = null;
        Category categoryBeauty = new Category("Beauty");

        CategoryList newCategoryList = new CategoryList();

        //Act
        //A collection of categories to be added
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryBets, categoryNull, categoryBeauty));

        //Add several categories simultaneously to Category List with method under test
        newCategoryList.addMultipleCategoriesToList(setOfCategories);

        int realNumberOfCategoriesAdded = newCategoryList.getCategoriesList().size();
        //Assert
        assertEquals(2, realNumberOfCategoriesAdded);
    }


    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if the same Category is not added simultaneously")
    void addMultipleCategoriesToListWithTwoCategoriesThatAreTheSame() {
        // Arrange
        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryHealthDuplicated = new Category("Health");
        Category categoryBeauty = new Category("Beauty");

        CategoryList newCategoryList = new CategoryList();

        //Act
        //A collection of categories to be added
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryHealthDuplicated, categoryBeauty));
        //Add several categories simultaneously to Category List with method under test
        newCategoryList.addMultipleCategoriesToList(setOfCategories);

        int realNumberOfCategoriesOfTheList = newCategoryList.getCategoriesList().size();

        //Assert
        assertEquals(2, realNumberOfCategoriesOfTheList);

    }

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if the same Category is not added simultaneously " +
            "Ignore letter capitalization and special characters. ")
    void addMultipleCategoriesToListWithTwoCategoriesCaseInsensitive() {
        // Arrange
        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryHealthDuplicated = new Category("heálth");
        Category categoryBeauty = new Category("Beauty");

        CategoryList newCategoryList = new CategoryList();

        //Act
        //A collection of categories to be added
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryHealthDuplicated, categoryBeauty));
        //Add everal categories simultaneously to Category List with method under test
        newCategoryList.addMultipleCategoriesToList(setOfCategories);

        int realNumberOfCategoriesOfTheList = newCategoryList.getCategoriesList().size();

        assertEquals(2, realNumberOfCategoriesOfTheList);
    }


    @Test
    @DisplayName("Remove a Set of Categories from user Category List - Main Scenario")
    void removeMultipleCategoriesToList_MainScenario() {
        // Arrange
        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryGym = new Category("Gym");
        Category categoryBeauty = new Category("Beauty");

        CategoryList newCategoryList = new CategoryList();

        //Act
        //set of categories to be added
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        newCategoryList.addMultipleCategoriesToList(setOfCategories);

        //set of Categories to be removed from Categories List
        HashSet<Category> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryBeauty, categoryGym));
        //Remove the set of categories with the method under test
        newCategoryList.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        int realNumberOfCategoriesOfFinalList = newCategoryList.getCategoriesList().size();

        assertEquals(1, realNumberOfCategoriesOfFinalList);
    }

    @Test
    @DisplayName("Remove a Set of Categories from user Category List - try to remove a set of Categories that doesnt exist " +
            "or null")
    void removeMultipleCategoriesToList_exceptionCase() {
        // Arrange
        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryGym = new Category("Gym");
        Category categoryBeauty = new Category("Beauty");
        Category categoryCar = new Category("Car");
        Category categoryNull = null;
        Category categoryUniversity = new Category("University");

        CategoryList newCategoryList = new CategoryList();

        //Act
        // Set of Categories to be added to Categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        newCategoryList.addMultipleCategoriesToList(setOfCategories);

        //set of Categories to be removed from Categories List
        HashSet<Category> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryCar, categoryNull, categoryUniversity));
        // Remove several categories simultaneously with method under test
        newCategoryList.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        int realNumberOfCategoriesOfFinalList = newCategoryList.getCategoriesList().size();

        assertEquals(3, realNumberOfCategoriesOfFinalList);
    }

    @Test
    @DisplayName("Remove a Set of Categories from user Category List - Ignore letter capitalization and special characters")
    void removeMultipleCategoriesToList_ignoreLettersFormatAndSpecialCase() {
        // Arrange
        // Categories to be included in Category List
        Category categoryHealth = new Category("Health");
        Category categoryGym = new Category("Gym");
        Category categoryBeauty = new Category("Beauty");
        Category categoryHealthLowerCase = new Category("health");
        Category categoryGymSpecialCharacter = new Category("Gým");
        Category categoryBeautyUpperCase = new Category("BEAUTY");

        CategoryList newCategoryList = new CategoryList();

        //Act
        // set of Categories to be added to Categories list
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        newCategoryList.addMultipleCategoriesToList(setOfCategories);

        //set of Categories to be removed from Categories List
        HashSet<Category> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryHealthLowerCase, categoryGymSpecialCharacter, categoryBeautyUpperCase));
        // Remove the previous categories with the method under test
        newCategoryList.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        int realNumberOfCategoriesOfFinalList = newCategoryList.getCategoriesList().size();

        //Assert
        assertEquals(0, realNumberOfCategoriesOfFinalList);
    }
}