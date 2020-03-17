package switch2019.project.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.category.Category;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.DateAndTime;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryTest {

    /**
     * Tests for the CategoryList method toString
     */
   /* @Test
    @DisplayName("Test if method toString returns the categories in the CategoryList")
    public void validateToString() {
        //Arrange:
        Person person1 = new Person("Alexandre", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        CategoryRepository firstCategoryList = new CategoryRepository();
        firstCategoryList.createCategory("cinema", person1.getID());
        firstCategoryList.createCategory("jantares", person1.getID());
        String expected = "CategoryList: [CINEMA, JANTARES]";

        //Act:
        String result = firstCategoryList.toString();

        //Assert:
        assertEquals(expected, result);
    }
    */

    /**
     * Tests for the CategoryList HashCode
     */
    @Test
    @DisplayName("Test if two Category Lists with the same categories have the same HashCode")
    public void compareTwoCategoryListsHashCode() {

        //Arrange:
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryRepository firstCategoryList = new CategoryRepository();
        CategoryRepository secondCategoryList = new CategoryRepository();

        //Act:
        firstCategoryList.createCategory("cinema", person1.getID());
        firstCategoryList.createCategory("jantares", person1.getID());
        secondCategoryList.createCategory("cinema", person1.getID());
        secondCategoryList.createCategory("jantares", person1.getID());

        //Assert:
        assertEquals(firstCategoryList.hashCode(), secondCategoryList.hashCode());
    }

    @Test
    @DisplayName("Test if two Category Lists with different categories, have different HashCodes")
    public void compareTwoCategoryListsHashCodeFalse() {

        //Arrange:
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryRepository firstCategoryList = new CategoryRepository();
        CategoryRepository secondCategoryList = new CategoryRepository();

        //Act:
        firstCategoryList.createCategory("cinema", person1.getID());
        secondCategoryList.createCategory("jantares", person1.getID());

        //Assert:
        assertNotEquals(firstCategoryList.hashCode(), secondCategoryList.hashCode());
    }

    /**
     * Test to see if two Category Lists are the same
     **/

    @Test
    @DisplayName("Test if two Category List are the same - Main Scenario")
    public void compareTwoCategoryList() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryRepository oneCategoryList = new CategoryRepository();
        CategoryRepository otherCategoryList = new CategoryRepository();

        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList("Health", "Education", "Gas"));

        //Act

        oneCategoryList.addMultipleCategoriesToList(setOfCategories,person1.getID());
        otherCategoryList.addMultipleCategoriesToList(setOfCategories,person1.getID());

        boolean realResult = oneCategoryList.equals(oneCategoryList);
        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("Test if two Category List equals a Null List")
    public void compareOneCategoryWithNull() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryRepository oneCategoryList = new CategoryRepository();
        CategoryRepository otherCategoryList = null;

        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList("Health", "Education", "Gas"));

        //Act

        oneCategoryList.addMultipleCategoriesToList(setOfCategories,person1.getID());

        boolean realResult = oneCategoryList.equals(otherCategoryList);
        //Assert
        assertFalse(realResult);
    }

    /**
     * Test to see if two Category Lists aren't the same
     **/

    @Test
    @DisplayName("Test if two Category List aren't the same - Main Scenario")
    public void compareTwoCategoryListNotTheSame() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryRepository oneCategoryList = new CategoryRepository();
        CategoryRepository otherCategoryList = new CategoryRepository();
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList("Health", "Education", "Gas"));
        HashSet<String> anotherSetOfCategories = new HashSet<>(Arrays.asList("Education", "Gas"));

        //Act
        oneCategoryList.addMultipleCategoriesToList(setOfCategories, person1.getID());
        otherCategoryList.addMultipleCategoriesToList(anotherSetOfCategories, person1.getID());

        boolean realResult = oneCategoryList.equals(otherCategoryList);

        //Assert

        assertFalse(realResult);
    }

    /**
     * Test if two Categories list are consider the same and Have the same Hashcode
     */

    @Test
    @DisplayName("test if two lists are the same & have the same HasHCode")
    public void testIfTwoAccountListsAreTheSameHashcode() {
        //Arrange

        String oneCategoryName = "xpto";
        String otherCategoryName = "xpto";

        CategoryRepository oneCategoryList = new CategoryRepository();
        CategoryRepository otherCategoryList = new CategoryRepository();

        //Act
        oneCategoryList.createCategory(oneCategoryName);
        otherCategoryList.createCategory(otherCategoryName);


        //Assert
        assertEquals(oneCategoryList.hashCode(), otherCategoryList.hashCode());
    }

    /**
     * Test if two CategoriesList are the same when created from different classes or null
     */
    @Test
    @DisplayName("test if two CategoriesList are the same when created from different classes")
    public void testIfTwoAccountListsAreTheSameDifferentClasses() {
        //Arrange
        CategoryRepository oneCategoryList = new CategoryRepository();
        GroupsRepository otherCategoryList = new GroupsRepository();

        //Assert
        assertNotEquals(oneCategoryList, otherCategoryList);
    }

    @Test
    @DisplayName("test if two CategoriesList are the same when null")
    public void testIfTwoAccountListsAreTheSameNull() {
        //Arrange
        CategoryRepository oneCategoryList = null;
        GroupsRepository otherCategoryList = new GroupsRepository();

        //Assert
        assertNotEquals(oneCategoryList, otherCategoryList);
    }

    /**
     * Test if one category was added to the Category List
     */

    @Test
    @DisplayName("Test if one category was added to the Category List - Main Scenario ")
    void addCategoryToListMainScenario() {
        //Arrange
        //Category to be included in Category List
        String oneCategory = "School expenses";
        CategoryRepository newCategoryList = new CategoryRepository();

        //Act

        boolean realResult = newCategoryList.createCategory(oneCategory);

        //Assert
        assertEquals(true, realResult);
    }

    /**
     * Test if two different categories were added to the Category List
     */

    @Test
    @DisplayName("Test if two categories were added to the Category List - Main Scenario ")
    void addCategoryToCategoryListTwoDifferentCategories() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        //Category to be included in Category List
        String oneCategory = "School expenses";
        String otherCategory = "Health";
        CategoryRepository newCategoryList = new CategoryRepository();

        //Act

        boolean realResult = newCategoryList.createCategory(oneCategory,person1.getID())
                && newCategoryList.createCategory(otherCategory, person1.getID());

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
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        //Category to be included in Category List
        String otherCategory = null;
        CategoryRepository newCategoryList = new CategoryRepository();

        //Act

        try {
            newCategoryList.createCategory(otherCategory, person1.getID());
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The denomination can´t be null or empty!", description.getMessage());
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
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        //Category to be included in Category List
        String originalCategory = "Saude";
        String duplicateCategory = "saúde";

        CategoryRepository newCategoryList = new CategoryRepository();

        //Act
        boolean realResult = newCategoryList.createCategory(originalCategory, person1.getID())
                && newCategoryList.createCategory(duplicateCategory,person1.getID());

        //Assert

        assertFalse(realResult);

    }

    /**
     * Test if a category was removed from the Category List
     */

    @Test
    @DisplayName("Test if a category was removed from the Category List - Main Scenario")
    void removeCategoryFromListMainScenario() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        String oneCategory = "Saude";
        String otherCategory = "Health";
        String otherCategoryObject = "Health";

        CategoryRepository newCategoryList = new CategoryRepository();

        //Act
        newCategoryList.createCategory(oneCategory, person1.getID());
        newCategoryList.createCategory(otherCategory, person1.getID());

        //Remove one Category

        boolean realResult = newCategoryList.removeCategoryFromList(otherCategoryObject, person1.getID());

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("Test if a category was removed from the Category List - ignore word case or word accent")
    void removeCategoryFromList() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        String oneCategory = "Saude";
        String otherCategory = "saúde";
        String otherCategoryObject = "saúde";

        CategoryRepository newCategoryList = new CategoryRepository();

        //Act
        newCategoryList.createCategory(oneCategory,person1.getID());
        newCategoryList.createCategory(otherCategory,person1.getID());
        //Remove one Category
        boolean realResult = newCategoryList.removeCategoryFromList(otherCategoryObject,person1.getID());

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("check if category is not in list and threfore cant be removed")
    void removeCategoryThatIsNotInTheList() {

        //Arrange:
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryRepository testCategoryList = new CategoryRepository();

        //Act:
        boolean isACategoryNotInListRemoved = testCategoryList.removeCategoryFromList("Cinema", person1.getID());

        //Assert:
        assertFalse(isACategoryNotInListRemoved);
    }

    @Test
    @DisplayName("Test if a category was removed from the Category List - null case")
    void removeCategoryFromListNullCase() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        String oneCategory = "Saude";
        String otherCategory = "null";
        String otherCategoryObject = null;

        CategoryRepository newCategoryList = new CategoryRepository();

        //Act
        newCategoryList.createCategory(oneCategory,person1.getID());
        newCategoryList.createCategory(otherCategory,person1.getID());
        //Remove one Category
        try {
            boolean realResult = newCategoryList.removeCategoryFromList(otherCategoryObject, person1.getID());
        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The denomination can´t be null or empty!", description.getMessage());
        }

    }

    @Test
    @DisplayName("Test if a category was removed from the Category List - a category that doesnt exists")
    void removeCategoryFromListDoesntExist() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        String oneCategory = "Saude";
        String otherCategory = "Educação";
        String otherCategoryObject = "Educação";

        CategoryRepository newCategoryList = new CategoryRepository();

        //Act
        newCategoryList.createCategory(oneCategory,person1.getID());

        //Remove the otherCategory (the list doesn't contain this)

        boolean realResult = newCategoryList.removeCategoryFromList(otherCategoryObject,person1.getID());

        //Assert
        assertFalse(realResult);
    }

    /**
     * Test to add a set of categories to a category list
     */

    @Test
    @DisplayName("Add a Set of Categories to Category List - Main Scenario")
    void addMultipleCategoriesToListMainScenario() {
        // Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        // Categories to be included in Category List
        String categoryHealth = "Health";
        String categoryGym = "Gym";
        String categoryUniversity = "University";

        //A collection of categories to be added
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryUniversity));
        CategoryRepository newCategoryList = new CategoryRepository();

        //Act

        boolean validateIfTheSetOfCategoriesWasAdded = newCategoryList.addMultipleCategoriesToList(setOfCategories,person1.getID());

        //Assert
        assertTrue(validateIfTheSetOfCategoriesWasAdded);
    }

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if the same Category is not added simultaneously")
    void addMultipleCategoriesToListWithTwoCategoriesThatAreTheSame() {
        // Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        // Categories to be included in Category List
        String categoryHealth = "Health";
        String categoryHealthDuplicated = "Health";
        String categoryBeauty = "Beauty";

        CategoryRepository newCategoryList = new CategoryRepository();

        //A collection of categories to be added
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryHealthDuplicated, categoryBeauty));

        //Act
        //Add several categories simultaneously to Category List with method under test
        newCategoryList.addMultipleCategoriesToList(setOfCategories, person1.getID());

        int realNumberOfCategoriesOfTheList = newCategoryList.numberOfCategoryInTheCategoryList();

        //Assert
        assertEquals(2, realNumberOfCategoriesOfTheList);

    }

    @Test
    @DisplayName("Add a Set of Categories to user Category List - Check if the same Category is not added simultaneously " +
            "Ignore letter capitalization and special characters. ")
    void addMultipleCategoriesToListWithTwoCategoriesCaseInsensitive() {
        // Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        // Categories to be included in Category List
        String categoryHealth = "Health";
        String categoryHealthDuplicated = "heálth";
        String categoryBeauty = "Beauty";

        CategoryRepository newCategoryList = new CategoryRepository();

        //A collection of categories to be added
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryHealthDuplicated, categoryBeauty));
        //Act
        //Add everal categories simultaneously to Category List with method under test
        newCategoryList.addMultipleCategoriesToList(setOfCategories, person1.getID());

        int realNumberOfCategoriesOfTheList = newCategoryList.numberOfCategoryInTheCategoryList();

        assertEquals(2, realNumberOfCategoriesOfTheList);
    }


    /**
     * Test to remove a set of categories from user category list
     */

    @Test
    @DisplayName("Remove a Set of Categories from user Category List - Main Scenario")
    void removeMultipleCategoriesToListMainScenario() {
        // Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        // Categories to be included in Category List
        String categoryHealth = "Health";
        String categoryGym = "Gym";
        String categoryBeauty = "Beauty";

        CategoryRepository newCategoryList = new CategoryRepository();

        //set of categories to be added
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        newCategoryList.addMultipleCategoriesToList(setOfCategories, person1.getID());

        //set of Categories to be removed from Categories List
        HashSet<String> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryBeauty, categoryGym));

        //Act
        //Remove the set of categories with the method under test
        boolean realResult = newCategoryList.removeMultipleCategoriesToList(setOfCategoriesToRemove, person1.getID());

        assertTrue(realResult);
    }


    @Test
    @DisplayName("Remove a Set of Categories from user Category List - try to remove a set of Categories that doesnt exist " +
            "or null")
    void removeMultipleCategoriesToListExceptionCase() {
        // Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        // Categories to be included in Category List
        String categoryHealth = "Health";
        String categoryGym = "Gym";
        String categoryBeauty = "Beauty";
        String categoryCar = "Car";
        String categoryNull = null;
        String categoryUniversity = "University";

        CategoryRepository newCategoryList = new CategoryRepository();

        // Set of Categories to be added to Categories list
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        newCategoryList.addMultipleCategoriesToList(setOfCategories, person1.getID());

        HashSet<String> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryCar, categoryNull, categoryUniversity));
        //Act
        //set of Categories to be removed from Categories List
        try {

            newCategoryList.removeMultipleCategoriesToList(setOfCategoriesToRemove, person1.getID());
        }
        //Assert
        catch (IllegalArgumentException setterEx) {
            assertEquals("The denomination can´t be null or empty!", setterEx.getMessage());
        }
    }

    @Test
    @DisplayName("Remove a Set of Categories from user Category List - Ignore letter capitalization and special characters")
    void removeMultipleCategoriesToListIgnoreLettersFormatAndSpecialCase() {
        // Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        // Categories to be included in Category List
        String categoryHealth = "Health";
        String categoryGym = "Gym";
        String categoryBeauty = "Beauty";
        String categoryHealthLowerCase = "health";
        String categoryGymSpecialCharacter = "Gým";
        String categoryBeautyUpperCase = "BEAUTY";

        CategoryRepository newCategoryList = new CategoryRepository();

        // set of String to be added to Categories list
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        newCategoryList.addMultipleCategoriesToList(setOfCategories, person1.getID());

        //set of Categories to be removed from Categories List
        HashSet<String> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryHealthLowerCase, categoryGymSpecialCharacter, categoryBeautyUpperCase));
        // Remove the previous categories with the method under test

        //Act
        boolean realResult = newCategoryList.removeMultipleCategoriesToList(setOfCategoriesToRemove, person1.getID());

        //Assert
        assertTrue(realResult);
    }

    /**
     * Test to validate if a category is in the list
     */

    @Test
    @DisplayName("Test if a category is in the category list - true case")
    void testValidateIfCategoryIsInTheCategoryListTrueCase() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        String house = "House";
        String cats = "Cats";
        String transport = "Transport";
        Category catsObject = new Category("Cats",person1.getID());
        CategoryRepository newCategories = new CategoryRepository();

        HashSet<String> myCategories = new HashSet<>(Arrays.asList(house, cats, transport));

        //Act

        newCategories.addMultipleCategoriesToList(myCategories, person1.getID());
        boolean result = newCategories.validateIfCategoryIsInTheCategoryList(catsObject);

        //Assert
        assertEquals(true, result);
    }


    @Test
    @DisplayName("Test if a category is in the category list - false case")
    void testValidateIfCategoryIsInTheCategoryListFalseCase() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        String house = "House";
        String cats = "Cats";
        String transport = "Transport";
        CategoryRepository newCategories = new CategoryRepository();

        HashSet<String> myCategories = new HashSet<>(Arrays.asList(house, cats, transport));

        //Act

        boolean realResult = newCategories.addMultipleCategoriesToList(myCategories, person1.getID());

        //Assert
        assertTrue(realResult);
    }


    @Test
    @DisplayName("Test to validate if set of categories is the the category list-true case for all")
    void validateIfSetOfCategoriesIsInTheCategoryList() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryRepository categoryList1 = new CategoryRepository();
        HashSet<String> myCategories = new HashSet<>(Arrays.asList("category1", "category2", "category3"));

        //Act
        categoryList1.addMultipleCategoriesToList(myCategories, person1.getID());

        boolean result = categoryList1.validateIfSetOfCategoriesIsInTheCategoryList(myCategories,person1.getID());
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to validate if set of categories is the the category list adding one at the time-false case")
    void validateIfSetOfCategoriesIsInTheCategoryList_NotAll() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryRepository categoryList1 = new CategoryRepository();
        HashSet<String> myCategories = new HashSet<>(Arrays.asList("category1", "category2", "category3"));

        //Act
        categoryList1.createCategory("category1", person1.getID());
        categoryList1.createCategory("category2", person1.getID());

        boolean result = categoryList1.validateIfSetOfCategoriesIsInTheCategoryList(myCategories,person1.getID());
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
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryRepository categoryList = new CategoryRepository();

        //Act
        categoryList.createCategory("Transports", person1.getID());
        categoryList.createCategory("House", person1.getID());

        int actual = categoryList.numberOfCategoryInTheCategoryList();
        //Assert
        assertEquals(2, actual);
    }


    @Test
    @DisplayName("Test to validate number of categories in the category list - Empty CategoryList")
    void numberOfCategoryInTheCategoryListSuccessCaseEmptyCategoryList() {

        //Arrange
        CategoryRepository categoryList = new CategoryRepository(); //empty Category List

        //Act
        int actual = categoryList.numberOfCategoryInTheCategoryList();

        //Assert
        assertEquals(0, actual);
    }
}