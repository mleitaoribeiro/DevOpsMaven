package switch2019.project.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.controllers.User;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class CategoryListTest {

    /**
     *
     * Tests for the CategoryList HashCode
     */
    @Test
    @DisplayName("Test if two Category Lists with the same categories have the same HashCode")
    public void compareTwoCategoryListsHashCode() {

        //Arrange:
        CategoryList firstCategoryList = new CategoryList();
        CategoryList secondCategoryList = new CategoryList();

        //Act:
        firstCategoryList.addCategoryToCategoryList("cinema");
        firstCategoryList.addCategoryToCategoryList("jantares");
        secondCategoryList.addCategoryToCategoryList("cinema");
        secondCategoryList.addCategoryToCategoryList("jantares");

        //Assert:
        assertEquals(firstCategoryList.hashCode(), secondCategoryList.hashCode());
    }

    @Test
    @DisplayName("Test if two Category Lists with different categories, have different HashCodes")
    public void compareTwoCategoryListsHashCodeFalse(){

        //Arrange:
        CategoryList firstCategoryList = new CategoryList();
        CategoryList secondCategoryList = new CategoryList();

        //Act:
        firstCategoryList.addCategoryToCategoryList("cinema");
        secondCategoryList.addCategoryToCategoryList("jantares");

        //Assert:
        assertNotEquals(firstCategoryList.hashCode(), secondCategoryList.hashCode());
    }

    /**
     * Test to see if two Category Lists are the same
     * Main Scenario
     **/

    @Test
    @DisplayName("Test if two Category List are the same - Main Scenario")
    public void compareTwoCategoryList() {
        //Arrange

        CategoryList oneCategoryList = new CategoryList();
        CategoryList otherCategoryList = new CategoryList();

        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList("Health", "Education", "Gas"));

        //Act

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

    @Test
    @DisplayName("Test if two Category List aren't the same - Main Scenario")
    public void compareTwoCategoryListNotTheSame() {
        //Arrange

        CategoryList oneCategoryList = new CategoryList();
        CategoryList otherCategoryList = new CategoryList();
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList("Health", "Education", "Gas"));
        HashSet<String> anotherSetOfCategories = new HashSet<>(Arrays.asList("Education", "Gas"));

        //Act
        oneCategoryList.addMultipleCategoriesToList(setOfCategories);
        otherCategoryList.addMultipleCategoriesToList(anotherSetOfCategories);

        boolean realResult = oneCategoryList.equals(otherCategoryList);

        //Assert

        assertFalse(realResult);
    }

    /**
     * Test if two Categories list are consider the same and Have the same Hashcode
     *
     */

    @Test
    @DisplayName("test if two lists are the same & have the same HasHCode")
    public void testIfTwoAccountListsAreTheSameHashcode() {
        //Arrange

        String oneCategoryName = "xpto";
        String otherCategoryName = "xpto";

        CategoryList oneCategoryList = new CategoryList();
        CategoryList otherCategoryList = new CategoryList();

        //Act
       oneCategoryList.addCategoryToCategoryList(oneCategoryName);
       otherCategoryList.addCategoryToCategoryList(otherCategoryName);


        //Assert
        assertEquals(oneCategoryList.hashCode(), otherCategoryList.hashCode());
    }

    /**
     * Test if two CategoriesList are the same when created from different classes or null
     *
     */
    @Test
    @DisplayName("test if two CategoriesList are the same when created from different classes")
    public void testIfTwoAccountListsAreTheSameDifferentClasses() {
        //Arrange
        CategoryList oneCategoryList = new CategoryList();
        GroupsList otherCategoryList = new GroupsList();

        //Assert
        assertNotEquals(oneCategoryList, otherCategoryList);
    }

    @Test
    @DisplayName("test if two CategoriesList are the same when null")
    public void testIfTwoAccountListsAreTheSameNull() {
        //Arrange
        CategoryList oneCategoryList = null;
        GroupsList otherCategoryList = new GroupsList();

        //Assert
        assertNotEquals(oneCategoryList, otherCategoryList);
    }

    /**
     * Test if one category was added to the Category List
     * Main Scenario
     */

    @Test
    @DisplayName("Test if one category was added to the Category List - Main Scenario ")
    void addCategoryToListMainScenario() {
        //Arrange
        //Category to be included in Category List
        String oneCategory ="School expenses";
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

    @Test
    @DisplayName("Test if two categories were added to the Category List - Main Scenario ")
    void addCategoryToCategoryListTwoDifferentCategories() {
        //Arrange
        //Category to be included in Category List
        String oneCategory = "School expenses";
        String otherCategory ="Health";
        CategoryList newCategoryList = new CategoryList();

        //Act

        boolean realResult = newCategoryList.addCategoryToCategoryList(oneCategory)
                && newCategoryList.addCategoryToCategoryList(otherCategory);

        //Assert
        assertTrue(realResult);
    }

    /**
     * Test if a null was added to the Category List
     * Validation trough the size of the List
     */

    @Test
    @DisplayName("Test if a null was added to the Category List - validate trough size of CategoryList ")
    void addCategoryToCategoryListNullCase() {
        //Arrange
        //Category to be included in Category List
        String otherCategory = null;
        CategoryList newCategoryList = new CategoryList();

        //Act

        try {
            newCategoryList.addCategoryToCategoryList(otherCategory);
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The category description is not valid or it's missing. Please try again.", description.getMessage());
        }
    }

    /**
     * Test if a category that already exists in the list was added
     * Ignore the case and spelling accents
     */

    @Test
    @DisplayName("Test if a duplicate Category was added to the Category List - ignore word case or spelling accents")
    void addCategoryToCategoryListDuplicateCategory() {
        //Arrange
        //Category to be included in Category List
        String originalCategory = "Saude";
        String duplicateCategory = "saúde";

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

    @Test
    @DisplayName("Test if a category was removed from the Category List - Main Scenario")
    void removeCategoryFromListMainScenario() {
        //Arrange
        String oneCategory = "Saude";
        String otherCategory = "Health";
        String otherCategoryObject = "Health";

        CategoryList newCategoryList = new CategoryList();

        //Act
        newCategoryList.addCategoryToCategoryList(oneCategory);
        newCategoryList.addCategoryToCategoryList(otherCategory);

        //Remove one Category

        boolean realResult = newCategoryList.removeCategoryFromList(otherCategoryObject);

        //Assert
        assertTrue(realResult);
    }

    /** Test if a category was removed from the Category List
     * Ignore case and spelling accents
     */

    @Test
    @DisplayName("Test if a category was removed from the Category List - ignore word case or word accent")
    void removeCategoryFromList() {
        //Arrange
        String oneCategory = "Saude";
        String otherCategory="saúde";
        String otherCategoryObject = "saúde";

        CategoryList newCategoryList = new CategoryList();

        //Act
        newCategoryList.addCategoryToCategoryList(oneCategory);
        newCategoryList.addCategoryToCategoryList(otherCategory);
        //Remove one Category
        boolean realResult = newCategoryList.removeCategoryFromList(otherCategoryObject);

        //Assert
        assertTrue(realResult);
    }
    @Test
    @DisplayName("check if category is not in list and threfore cant be removed")
    void removeCategoryThatIsNotInTheList() {

        //Arrange:
        CategoryList testCategoryList = new CategoryList();

        //Act:
        boolean isACategoryNotInListRemoved = testCategoryList.removeCategoryFromList("Cinema");

        //Assert:
        assertFalse(isACategoryNotInListRemoved);
    }

    /**
     * Test if a category was removed from category list
     * Null Case
     */

    @Test
    @DisplayName("Test if a category was removed from the Category List - null case")
    void removeCategoryFromListNullCase() {
        //Arrange
        String oneCategory ="Saude";
        String otherCategory= "null";
        String otherCategoryObject = null;

        CategoryList newCategoryList = new CategoryList();

        //Act
        newCategoryList.addCategoryToCategoryList(oneCategory);
        newCategoryList.addCategoryToCategoryList(otherCategory);
        //Remove one Category
        try {
            boolean realResult = newCategoryList.removeCategoryFromList(otherCategoryObject);
        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The category description is not valid or it's missing. Please try again.", description.getMessage());
        }

    }

    /**
     * Test if a category was removed from the category list
     * category doesn't exist
     */

    @Test
    @DisplayName("Test if a category was removed from the Category List - a category that doesnt exists")
    void removeCategoryFromListDoesntExist() {
        //Arrange
        String oneCategory ="Saude";
        String otherCategory= "Educação";
        String otherCategoryObject = "Educação";

        CategoryList newCategoryList = new CategoryList();

        //Act
        newCategoryList.addCategoryToCategoryList(oneCategory);

        //Remove the otherCategory (the list doesn't contain this)

        boolean realResult = newCategoryList.removeCategoryFromList(otherCategoryObject);

        //Assert
        assertFalse(realResult);
    }

    /**
     * Test to add a set of categories to a category list
     * Main Scenario
     */

    @Test
    @DisplayName("Add a Set of Categories to Category List - Main Scenario")
    void addMultipleCategoriesToListMainScenario() {
        // Arrange
        // Categories to be included in Category List
        String categoryHealth ="Health";
        String categoryGym = "Gym";
        String categoryUniversity = "University";

        //A collection of categories to be added
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryUniversity));
        CategoryList newCategoryList = new CategoryList();

        //Act

        boolean validateIfTheSetOfCategoriesWasAdded = newCategoryList.addMultipleCategoriesToList(setOfCategories);

        //Assert
        assertTrue(validateIfTheSetOfCategoriesWasAdded);
    }

    /**
     * Test to add a set of categories to user category list
     * see if a null was added
     */

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if null category is not added")
    void addMultipleCategoriesToListWithANullCase() {
        // Arrange
        // Categories to be included in Category List
        String categoryBets ="Bets and Games";
        String categoryNull = null;
        String categoryBeauty = "Beauty";

        CategoryList newCategoryList = new CategoryList();
        //A collection of categories to be added
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryBets, categoryNull, categoryBeauty));

        //Act
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

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if the same Category is not added simultaneously")
    void addMultipleCategoriesToListWithTwoCategoriesThatAreTheSame() {
        // Arrange
        // Categories to be included in Category List
        String categoryHealth = "Health";
        String categoryHealthDuplicated ="Health";
        String categoryBeauty = "Beauty";

        CategoryList newCategoryList = new CategoryList();

        //A collection of categories to be added
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryHealthDuplicated, categoryBeauty));

        //Act
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

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if the same Category is not added simultaneously " +
            "Ignore letter capitalization and special characters. ")
    void addMultipleCategoriesToListWithTwoCategoriesCaseInsensitive() {
        // Arrange
        // Categories to be included in Category List
        String categoryHealth ="Health";
        String categoryHealthDuplicated = "heálth";
        String categoryBeauty = "Beauty";

        CategoryList newCategoryList = new CategoryList();

        //A collection of categories to be added
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryHealthDuplicated, categoryBeauty));
        //Act
        //Add everal categories simultaneously to Category List with method under test
        newCategoryList.addMultipleCategoriesToList(setOfCategories);

        int realNumberOfCategoriesOfTheList = newCategoryList.numberOfCategoryInTheCategoryList();

        assertEquals(2, realNumberOfCategoriesOfTheList);
    }


    /**
     * Test to remove a set of categories from user category list
     * Main Scenario
     */

    @Test
    @DisplayName("Remove a Set of Categories from user Category List - Main Scenario")
    void removeMultipleCategoriesToList_MainScenario() {
        // Arrange
        // Categories to be included in Category List
        String categoryHealth = "Health";
        String categoryGym ="Gym";
        String categoryBeauty = "Beauty";

        CategoryList newCategoryList = new CategoryList();

        //set of categories to be added
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        newCategoryList.addMultipleCategoriesToList(setOfCategories);

        //set of Categories to be removed from Categories List
        HashSet<String> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryBeauty, categoryGym));

        //Act
        //Remove the set of categories with the method under test
        boolean realResult = newCategoryList.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        assertTrue(realResult);
    }

    /**
     * Test to remove a set categories from user category list
     * See if can remove a category that doesn't exist
     * Test for null
     */

    @Test
    @DisplayName("Remove a Set of Categories from user Category List - try to remove a set of Categories that doesnt exist " +
            "or null")
    void removeMultipleCategoriesToList_exceptionCase() {
        // Arrange
        // Categories to be included in Category List
        String categoryHealth = "Health";
        String categoryGym = "Gym";
        String categoryBeauty = "Beauty";
        String categoryCar = "Car";
        String categoryNull = null;
        String categoryUniversity ="University";

        CategoryList newCategoryList = new CategoryList();

        // Set of Categories to be added to Categories list
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        newCategoryList.addMultipleCategoriesToList(setOfCategories);

        HashSet<String> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryCar, categoryNull, categoryUniversity));
        //Act
        //set of Categories to be removed from Categories List
        try {

            newCategoryList.removeMultipleCategoriesToList(setOfCategoriesToRemove);
        }
        //Assert
        catch (IllegalArgumentException setterEx) {
            assertEquals("The category description is not valid or it's missing. Please try again.", setterEx.getMessage());
        }
        


    }

    /**
     * Test to remove a set of categories from a user category list
     * Ignore letter capitalization and special characters
     */

    @Test
    @DisplayName("Remove a Set of Categories from user Category List - Ignore letter capitalization and special characters")
    void removeMultipleCategoriesToList_ignoreLettersFormatAndSpecialCase() {
        // Arrange
        // Categories to be included in Category List
        String categoryHealth = "Health";
        String categoryGym = "Gym";
        String categoryBeauty = "Beauty";
        String categoryHealthLowerCase ="health";
        String categoryGymSpecialCharacter ="Gým";
        String categoryBeautyUpperCase ="BEAUTY";

        CategoryList newCategoryList = new CategoryList();

        // set of String to be added to Categories list
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        newCategoryList.addMultipleCategoriesToList(setOfCategories);

        //set of Categories to be removed from Categories List
        HashSet<String> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryHealthLowerCase,categoryGymSpecialCharacter, categoryBeautyUpperCase));
        // Remove the previous categories with the method under test

        //Act
        boolean realResult = newCategoryList.removeMultipleCategoriesToList(setOfCategoriesToRemove);

        //Assert
        assertTrue(realResult);
    }

    /**
     * Test to validate if a category is in the list
     * True case
     */

    @Test
    @DisplayName("Test if a category is in the category list - true case")
    void testValidateIfCategoryIsInTheCategoryListTrueCase() {
        //Arrange
        String house ="House";
        String cats = "Cats";
        String transport = "Transport";
        Category catsObject=new Category ("Cats");
        CategoryList newCategories = new CategoryList();

        HashSet<String> myCategories = new HashSet<>(Arrays.asList(house, cats, transport));

        //Act

        newCategories.addMultipleCategoriesToList(myCategories);
        boolean result = newCategories.validateIfCategoryIsInTheCategoryList(catsObject);

        //Assert
        assertEquals(true, result);
    }

    /**
     * Test to validate if a category is in the list
     * False case
     */

    @Test
    @DisplayName("Test if a category is in the category list - false case")
    void testValidateIfCategoryIsInTheCategoryListFalseCase() {
        //Arrange
        String house = "House";
        String cats ="Cats";
        String  transport ="Transport";
        CategoryList newCategories = new CategoryList();

        HashSet<String> myCategories = new HashSet<>(Arrays.asList(house, cats, transport));

    //Act

        boolean realResult = newCategories.addMultipleCategoriesToList(myCategories);

        //Assert
        assertTrue(realResult);
    }

    /**
     * Test to validate if set of categories in the the list
     * True case for all
     */

    @Test
    @DisplayName("Test to validate if set of categories is the the category list-true case for all")
    void validateIfSetOfCategoriesIsInTheCategoryList() {
        //Arrange
        CategoryList categoryList1 = new CategoryList();
        HashSet<String> myCategories = new HashSet<>(Arrays.asList("category1", "category2", "category3"));

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
    @Test
    @DisplayName("Test to validate if set of categories is the the category list adding one at the time-false case")
    void validateIfSetOfCategoriesIsInTheCategoryList_NotAll() {
        //Arrange
        CategoryList categoryList1 = new CategoryList();
        HashSet<String> myCategories = new HashSet<>(Arrays.asList("category1", "category2", "category3"));

        //Act
        categoryList1.addCategoryToCategoryList("category1");
        categoryList1.addCategoryToCategoryList("category2");

        boolean result = categoryList1.validateIfSetOfCategoriesIsInTheCategoryList(myCategories);
        //Assert
        assertFalse(result);
    }

    /**
     * Test to validate number of categories in the category list
     */

    @Test
    @DisplayName("Test to validate number of categories in the category list - Success Case")
    void numberOfCategoryInTheCategoryListSuccessCase() {

        //Arrange
        CategoryList categoryList = new CategoryList();

        //Act
        categoryList.addCategoryToCategoryList("Transports");
        categoryList.addCategoryToCategoryList("House");

        int actual = categoryList.numberOfCategoryInTheCategoryList();
        //Assert
        assertEquals(2, actual);
    }


    @Test
    @DisplayName("Test to validate number of categories in the category list - Empty CategoryList")
    void numberOfCategoryInTheCategoryListSuccessCaseEmptyCategoryList() {

        //Arrange
        CategoryList categoryList = new CategoryList(); //empty Category List

        //Act
        int actual = categoryList.numberOfCategoryInTheCategoryList();

        //Assert
        assertEquals(0, actual);
    }
}