package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.controllers.User;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class CategoryListTest {

    /**
    *Test to see if two Category Lists are the same
     * Main Scenario
     **/
/*
    @Test
    @DisplayName("Test if two Category List are the same - Main Scenario")
    public void compareTwoCategoryList() {
        //Arrange
        Category categoryHealth = new Category("Health");
        Category categoryEducation = new Category("Education");
        Category categoryGas = new Category("Gas");

        CategoryList oneCategoryList = new CategoryList();
        CategoryList otherCategoryList = new CategoryList();

        //Act
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryEducation, categoryGas));

        oneCategoryList.addMultipleCategoriesToList(setOfCategories);
        otherCategoryList.addMultipleCategoriesToList(setOfCategories);

        boolean realResult = oneCategoryList.equals(oneCategoryList);
        //Assert
        assertTrue(realResult);
    }

    /**
     * Test to see if two Caterogy Lists aren't the same
     * Main Scenario
     **/
/*
    @Test
    @DisplayName("Test if two Category List aren't the same - Main Scenario")
    public void compareTwoCategoryListNotTheSame() {
        //Arrange
        Category categoryHealth = new Category("Health");
        Category categoryEducation = new Category("Education");
        Category categoryGas = new Category("Gas");

        CategoryList oneCategoryList = new CategoryList();
        CategoryList otherCategoryList = new CategoryList();

        //Act
        HashSet<Category> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryEducation, categoryGas));
        HashSet<Category> anotherSetOfCategories = new HashSet<>(Arrays.asList(categoryEducation, categoryGas));

        oneCategoryList.addMultipleCategoriesToList(setOfCategories);
        otherCategoryList.addMultipleCategoriesToList(anotherSetOfCategories);

        boolean realResult = oneCategoryList.equals(otherCategoryList);
        //Assert

        assertFalse(realResult);
    }

    /**
     * Test if one category was added to the Ctegory List
     * Main Scenario
     */
/*
    @Test
    @DisplayName("Test if one category was added to the Category List - Main Scenario ")
    void addCategoryToListMainScenario() {
        //Arrange
        //Category to be included in Category List
        Category oneCategory = new Category("School expenses");
        CategoryList newCategoryList = new CategoryList();
        //Act

        boolean realResult = newCategoryList.addCategoryToCategoryList(oneCategory);

        //Assert
        assertEquals(true, realResult);
    }

    /**
     * Test if two different categories were added to the Category List
     * Main Scenario
     */
/*
    @Test
    @DisplayName("Test if two categories were added to the Category List - Main Scenario ")
    void addCategoryToCategoryListTwoDifferentCategories() {
        //Arrange
        //Category to be included in Category List
        Category oneCategory = new Category("School expenses");
        Category otherCategory = new Category("Health");
        CategoryList newCategoryList = new CategoryList();

        //Act

        boolean realResult = newCategoryList.addCategoryToCategoryList(oneCategory)
                && newCategoryList.addCategoryToCategoryList(otherCategory);

        //Assert
        assertEquals(true, realResult);
    }

    /**
     * Test if a null was added to the Category List
     * Validation trough the size of the List
     */
/*
    @Test
    @DisplayName("Test if a null was added to the Category List - validate trough size of CategoryList ")
    void addCategoryToCategoryListNullCase() {
        //Arrange
        //Category to be included in Category List
        Category otherCategory = null;
        CategoryList newCategoryList = new CategoryList();

        //Act
        boolean realResult = newCategoryList.addCategoryToCategoryList(otherCategory);

        //Assert
        assertFalse(realResult);
    }

    /**
     * Test if a category that already exists in the list was added
     * Ignore the case and spelling accents
     */
/*
    @Test
    @DisplayName("Test if a duplicate Category was added to the Category List - ignore word case or spelling accents")
    void addCategoryToCategoryListDuplicateCategory() {
        //Arrange
        //Category to be included in Category List
        Category originalCategory = new Category("Saude");
        Category duplicateCategory = new Category("saúde");

        CategoryList newCategoryList = new CategoryList();

        //Act
        boolean realResult = newCategoryList.addCategoryToCategoryList(originalCategory)
                && newCategoryList.addCategoryToCategoryList(duplicateCategory);

        //Assert

        assertFalse(realResult);

    }

    /**
     * Test if a category was removed from the Category List
     * Main Scenario
     */
/*
    @Test
    @DisplayName("Test if a category was removed from the Category List - Main Scenario")
    void removeCategoryFromListMainScenario() {
        //Arrange
        Category oneCategory = new Category("Saude");
        Category otherCategory = new Category("Health");

        CategoryList newCategoryList = new CategoryList();

        //Act
        newCategoryList.addCategoryToCategoryList(oneCategory);
        newCategoryList.addCategoryToCategoryList(otherCategory);
        //Remove one Category


        boolean realResult = newCategoryList.removeCategoryFromList(otherCategory);

        //Assert
        assertTrue(realResult);
    }

    /** Test if a category was removed from the Category List
     * Ignore case and spelling accents
     */
/*
    @Test
    @DisplayName("Test if a category was removed from the Category List - ignore word case or word accent")
    void removeCategoryFromList() {
        //Arrange
        Category oneCategory = new Category("Saude");
        Category otherCategory = new Category("saúde");

        CategoryList newCategoryList = new CategoryList();

        //Act
        newCategoryList.addCategoryToCategoryList(oneCategory);
        newCategoryList.addCategoryToCategoryList(otherCategory);
        //Remove one Category
        boolean realResult = newCategoryList.removeCategoryFromList(otherCategory);


        //Assert
        assertTrue(realResult);
    }

    /**
     * Test if a category was removed from category list
     * Null Case
     */
/*
    @Test
    @DisplayName("Test if a category was removed from the Category List - null case")
    void removeCategoryFromListNullCase() {
        //Arrange
        Category oneCategory = new Category("Saude");
        Category otherCategory = null;

        CategoryList newCategoryList = new CategoryList();

        //Act
        newCategoryList.addCategoryToCategoryList(oneCategory);
        newCategoryList.addCategoryToCategoryList(otherCategory);
        //Remove one Category

        boolean realResult = newCategoryList.removeCategoryFromList(otherCategory);

        //Assert
        assertFalse(realResult);
    }

    /**
     * Test if a category was removed from the category list
     * category doesn't exist
     */
/*
    @Test
    @DisplayName("Test if a category was removed from the Category List - a category that doesnt exists")
    void removeCategoryFromListDoesntExist() {
        //Arrange
        Category oneCategory = new Category("Saude");
        Category otherCategory = new Category("Educação");

        CategoryList newCategoryList = new CategoryList();

        //Act
        newCategoryList.addCategoryToCategoryList(oneCategory);

        //Remove the otherCategory (the list doesn't contain this)

        boolean realResult = newCategoryList.removeCategoryFromList(otherCategory);

        //Assert
        assertFalse(realResult);
    }

    /**
     * Test to add a set of categories to a category list
     * Main Scenario
     */
/*
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


        boolean validateIfTheSetOfCategoriesWasAdded = newCategoryList.addMultipleCategoriesToList(setOfCategories);

        //Assert
        assertTrue(validateIfTheSetOfCategoriesWasAdded);
    }

    /**
     * Test to add a set of categories to user category list
     * see if a null was added
     */
/*
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

        int realNumberOfCategoriesAdded = newCategoryList.numberOfCategoryInTheCategoryList();
        //Assert
        assertEquals(2, realNumberOfCategoriesAdded);
    }


    /**
     * Test to add a set of categories to user category list
     * See if the same category was not added simultaneously
     */
/*
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

        int realNumberOfCategoriesOfTheList = newCategoryList.numberOfCategoryInTheCategoryList();

        //Assert
        assertEquals(2, realNumberOfCategoriesOfTheList);

    }

    /**
     * Test to add a set of categories to user category list
     * See if the same category was not added simultaneously
     * Ignore letter capitalization and special characters
     */
/*
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

        int realNumberOfCategoriesOfTheList = newCategoryList.numberOfCategoryInTheCategoryList();

        assertEquals(2, realNumberOfCategoriesOfTheList);
    }


    /**
     * Test to remove a set of categories from user category list
     * Main Scenario
     */
/*
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
        boolean realResult = newCategoryList.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        assertTrue(realResult);
    }

    /**
     * Test to remove a set categories from user category list
     * See if can remove a category that doesn't exist
     * Test for null
     */
/*
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
        boolean realResult = newCategoryList.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        assertTrue(realResult);
    }

    /**
     * Test to remove a set of categories from a user category list
     * Ignore letter capitalization and special characters
     */
/*
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
        boolean realResult = newCategoryList.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        //Assert
        assertTrue(realResult);
    }

    /**
     * Test to validate if a category is in the list
     * True case
     */
/*
    @Test
    @DisplayName("Test if a category is in the category list - true case")
    void testValidateIfCategoryIsInTheCategoryListTrueCase() {
        //Arrange
        Category house = new Category("House");
        Category cats = new Category("Cats");
        Category transport = new Category("Transport");
        CategoryList newCategories = new CategoryList();

        //Act
        HashSet<Category> myCategories = new HashSet<>(Arrays.asList(house, cats, transport));
        newCategories.addMultipleCategoriesToList(myCategories);
        boolean result = newCategories.validateIfCategoryIsInTheCategoryList(cats);

        //Assert
        assertEquals(true, result);
    }

    /**
     * Test to validate if a category is in the list
     * False case
     */
/*
    @Test
    @DisplayName("Test if a category is in the category list - false case")
    void testValidateIfCategoryIsInTheCategoryListFalseCase() {
        //Arrange
        Category house = new Category("House");
        Category cats = new Category("Cats");
        Category transport = new Category("Transport");
        CategoryList newCategories = new CategoryList();

        //Act
        HashSet<Category> myCategories = new HashSet<>(Arrays.asList(house, cats, transport));

        boolean realResult = newCategories.addMultipleCategoriesToList(myCategories);

        //Assert
        assertTrue(realResult);
    }

    /**
     * Test to validate if set of categories in the the list
     * True case for all
     */
/*
    @Test
    @DisplayName("Test to validate if set of categories is the the category list-true case for all")
    void validateIfSetOfCategoriesIsInTheCategoryList() {
        //Arrange
        Category category1 = new Category("category1");
        Category category2 = new Category("category2");
        Category category3 = new Category("category3");
        CategoryList categoryList1 = new CategoryList();
        HashSet<Category> myCategories = new HashSet<>(Arrays.asList(category1, category2, category3));

        //Act
        categoryList1.addMultipleCategoriesToList(myCategories);

        boolean result = categoryList1.validateIfSetOfCategoriesIsInTheCategoryList(myCategories);
        //Assert
        assertTrue(result);
    }

    /**
     * Test to validate if set of categories in the the list
     * False case
     */
/*
    @Test
    @DisplayName("Test to validate if set of categories is the the category list adding one at the time-false case")
    void validateIfSetOfCategoriesIsInTheCategoryList_NotAll() {
        //Arrange
        Category category1 = new Category("category1");
        Category category2 = new Category("category2");
        Category category3 = new Category("category3");
        CategoryList categoryList1 = new CategoryList();
        HashSet<Category> myCategories = new HashSet<>(Arrays.asList(category1, category2, category3));

        //Act
        categoryList1.addCategoryToCategoryList(category1);
        categoryList1.addCategoryToCategoryList(category2);

        boolean result = categoryList1.validateIfSetOfCategoriesIsInTheCategoryList(myCategories);
        //Assert
        assertFalse(result);
    }

    /**
     * Test to validate number of categories in the category list
     */
/*
    @Test
    @DisplayName("Test to validate number of categories in the category list - Success Case")
    void numberOfCategoryInTheCategoryListSuccessCase() {

        //Arrange
        Category category1 = new Category("Transports");
        Category category2 = new Category("House");
        CategoryList categoryList = new CategoryList();

        //Act
        categoryList.addCategoryToCategoryList(category1);
        categoryList.addCategoryToCategoryList(category2);

        int actual = categoryList.numberOfCategoryInTheCategoryList();
        //Assert
        assertEquals(2,actual);
    }


    @Test
    @DisplayName("Test to validate number of categories in the category list - Empty CategoryList")
    void numberOfCategoryInTheCategoryListSuccessCaseEmptyCategoryList() {

        //Arrange
        CategoryList categoryList = new CategoryList(); //empty Category List

        //Act
        int actual = categoryList.numberOfCategoryInTheCategoryList();

        //Assert
        assertEquals(0,actual);
    }
*/
}