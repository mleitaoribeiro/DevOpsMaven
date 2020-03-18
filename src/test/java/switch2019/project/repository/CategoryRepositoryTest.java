package switch2019.project.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.model.category.Category;
import switch2019.project.model.frameworks.ID;
import switch2019.project.model.person.Address;
import switch2019.project.model.person.Email;
import switch2019.project.model.person.Person;
import switch2019.project.model.shared.CategoryID;
import switch2019.project.model.shared.DateAndTime;
import switch2019.project.model.shared.Denomination;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class CategoryRepositoryTest {

    /**
     * Tests for the CategoryRepository method toString
     */
    /*@Test
    @DisplayName("Test if method toString returns the categories in the Category Repository")
    public void validateToString() {
        //Arrange:
        Person person1 = new Person("Alexandre", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        CategoryRepository firstCategoryRepository = new CategoryRepository();

        firstCategoryRepository.createCategory("cinema", person1.getID());
        firstCategoryRepository.createCategory("jantares", person1.getID());

        String expected = "CategoryRepository: [CINEMA, JANTARES]";

        //Act:
        String result = firstCategoryRepository.toString();

        //Assert:
        assertEquals(expected, result);
    }*/

    /**
     * Test if one category was added to the Category Repository
     */

    @Test
    @DisplayName("Test if one category was added to the Category Repository - Main Scenario ")
    void addCategoryToRepositoryMainScenario() {
        //Arrange
        //Category to be included in Category Repository
        String oneCategory = "School expenses";
        CategoryRepository newCategoryRepository = new CategoryRepository();

        //Act

        boolean realResult = newCategoryRepository.createCategory(oneCategory);

        //Assert
        assertEquals(true, realResult);
    }

    /**
     * Test if two different categories were added to the Category Repository
     */

    @Test
    @DisplayName("Test if two categories were added to the Category Repository - Main Scenario ")
    void addCategoryToCategoryRepositoryTwoDifferentCategories() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        //Category to be included in Category Repository
        String oneCategory = "School expenses";
        String otherCategory = "Health";
        CategoryRepository newCategoryRepository = new CategoryRepository();

        //Act

        boolean realResult = newCategoryRepository.createCategory(oneCategory,person1.getID())
                && newCategoryRepository.createCategory(otherCategory, person1.getID());

        //Assert
        assertTrue(realResult);
    }

    /**
     * Test if a null was added to the Category Repository
     * Validation trough the size of the Repository
     */

    @Test
    @DisplayName("Test if a null was added to the Category Repository - validate trough size of CategoryRepository ")
    void addCategoryToCategoryRepositoryNullCase() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        //Category to be included in Category Repository
        String otherCategory = null;
        CategoryRepository newCategoryRepository = new CategoryRepository();

        //Act

        try {
            newCategoryRepository.createCategory(otherCategory, person1.getID());
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The denomination can´t be null or empty!", description.getMessage());
        }
    }

    /**
     * Test if a category that already exists in the Repository was added
     * Ignore the case and spelling accents
     */

    @Test
    @DisplayName("Test if a duplicate Category was added to the Category Repository - ignore word case or spelling accents")
    void addCategoryToCategoryRepositoryDuplicateCategory() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        //Category to be included in Category Repository
        String originalCategory = "Saude";
        String duplicateCategory = "saúde";

        CategoryRepository newCategoryRepository = new CategoryRepository();

        //Act
        boolean realResult = newCategoryRepository.createCategory(originalCategory, person1.getID())
                && newCategoryRepository.createCategory(duplicateCategory,person1.getID());

        //Assert

        assertFalse(realResult);

    }

    /**
     * Test if a category was removed from the Category Repository
     */

    @Test
    @DisplayName("Test if a category was removed from the Category Repository - Main Scenario")
    void removeCategoryFromRepositoryMainScenario() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        String oneCategory = "Saude";
        String otherCategory = "Health";
        String otherCategoryObject = "Health";

        CategoryRepository newCategoryRepository = new CategoryRepository();

        //Act
        newCategoryRepository.createCategory(oneCategory, person1.getID());
        newCategoryRepository.createCategory(otherCategory, person1.getID());

        //Remove one Category

        boolean realResult = newCategoryRepository.removeCategory(otherCategoryObject, person1.getID());

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("Test if a category was removed from the Category Repository - ignore word case or word accent")
    void removeCategoryFromRepository() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        String oneCategory = "Saude";
        String otherCategory = "saúde";
        String otherCategoryObject = "saúde";

        CategoryRepository newCategoryRepository = new CategoryRepository();

        //Act
        newCategoryRepository.createCategory(oneCategory,person1.getID());
        newCategoryRepository.createCategory(otherCategory,person1.getID());
        //Remove one Category
        boolean realResult = newCategoryRepository.removeCategory(otherCategoryObject,person1.getID());

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("check if category is not in Repository and threfore cant be removed")
    void removeCategoryThatIsNotInTheRepository() {

        //Arrange:
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryRepository testCategoryRepository = new CategoryRepository();

        //Act:
        boolean isACategoryNotInRepositoryRemoved = testCategoryRepository.removeCategory("Cinema", person1.getID());

        //Assert:
        assertFalse(isACategoryNotInRepositoryRemoved);
    }

    @Test
    @DisplayName("Test if a category was removed from the Category Repository - null case")
    void removeCategoryFromRepositoryNullCase() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        String oneCategory = "Saude";
        String otherCategory = "null";
        String otherCategoryObject = null;

        CategoryRepository newCategoryRepository = new CategoryRepository();

        //Act
        newCategoryRepository.createCategory(oneCategory,person1.getID());
        newCategoryRepository.createCategory(otherCategory,person1.getID());
        //Remove one Category
        try {
            boolean realResult = newCategoryRepository.removeCategory(otherCategoryObject, person1.getID());
        }
        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The denomination can´t be null or empty!", description.getMessage());
        }

    }

    @Test
    @DisplayName("Test if a category was removed from the Category Repository - a category that doesnt exists")
    void removeCategoryFromRepositoryDoesntExist() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        String oneCategory = "Saude";
        String otherCategory = "Educação";
        String otherCategoryObject = "Educação";

        CategoryRepository newCategoryRepository = new CategoryRepository();

        //Act
        newCategoryRepository.createCategory(oneCategory,person1.getID());

        //Remove the otherCategory (the Repository doesn't contain this)

        boolean realResult = newCategoryRepository.removeCategory(otherCategoryObject,person1.getID());

        //Assert
        assertFalse(realResult);
    }

    /**
     * Test to add a set of categories to a category Repository
     */

    @Test
    @DisplayName("Add a Set of Categories to Category Repository - Main Scenario")
    void addMultipleCategoriesToRepositoryMainScenario() {
        // Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        // Categories to be included in Category Repository
        String categoryHealth = "Health";
        String categoryGym = "Gym";
        String categoryUniversity = "University";

        //A collection of categories to be added
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryUniversity));
        CategoryRepository newCategoryRepository= new CategoryRepository();

        //Act

        boolean validateIfTheSetOfCategoriesWasAdded = newCategoryRepository.addMultipleCategories(setOfCategories,person1.getID());

        //Assert
        assertTrue(validateIfTheSetOfCategoriesWasAdded);
    }

    @Test
    @DisplayName("Add a Set of Categories to user Category Repository - Check if the same Category is not added simultaneously")
    void addMultipleCategoriesToRepositoryWithTwoCategoriesThatAreTheSame() {
        // Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        // Categories to be included in Category Repository
        String categoryHealth = "Health";
        String categoryHealthDuplicated = "Health";
        String categoryBeauty = "Beauty";

        CategoryRepository newCategoryRepository = new CategoryRepository();

        //A collection of categories to be added
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryHealthDuplicated, categoryBeauty));

        //Act
        //Add several categories simultaneously to Category Repository with method under test
        newCategoryRepository.addMultipleCategories(setOfCategories, person1.getID());

        int realNumberOfCategoriesOfTheRepository = newCategoryRepository.numberOfCategoryInRepository();

        //Assert
        assertEquals(2, realNumberOfCategoriesOfTheRepository);

    }

    @Test
    @DisplayName("Add a Set of Categories to user Category Repository - Check if the same Category is not added simultaneously " +
            "Ignore letter capitalization and special characters. ")
    void addMultipleCategoriesToRepositoryWithTwoCategoriesCaseInsensitive() {
        // Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        // Categories to be included in Category Repository
        String categoryHealth = "Health";
        String categoryHealthDuplicated = "heálth";
        String categoryBeauty = "Beauty";

        CategoryRepository newCategoryRepository = new CategoryRepository();

        //A collection of categories to be added
        HashSet<String> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryHealthDuplicated, categoryBeauty));
        //Act
        //Add everal categories simultaneously to Category Repository with method under test
        newCategoryRepository.addMultipleCategories(setOfCategories, person1.getID());

        int realNumberOfCategoriesOfTheRepository = newCategoryRepository.numberOfCategoryInRepository();

        assertEquals(2, realNumberOfCategoriesOfTheRepository);
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
        newCategoryList.addMultipleCategories(setOfCategories, person1.getID());

        //set of Categories to be removed from Categories List
        HashSet<String> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryBeauty, categoryGym));

        //Act
        //Remove the set of categories with the method under test
        boolean realResult = newCategoryList.removeMultipleCategories(setOfCategoriesToRemove, person1.getID());

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
        newCategoryList.addMultipleCategories(setOfCategories, person1.getID());

        HashSet<String> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryCar, categoryNull, categoryUniversity));
        //Act
        //set of Categories to be removed from Categories List
        try {

            newCategoryList.removeMultipleCategories(setOfCategoriesToRemove, person1.getID());
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
        newCategoryList.addMultipleCategories(setOfCategories, person1.getID());

        //set of Categories to be removed from Categories List
        HashSet<String> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryHealthLowerCase, categoryGymSpecialCharacter, categoryBeautyUpperCase));
        // Remove the previous categories with the method under test

        //Act
        boolean realResult = newCategoryList.removeMultipleCategories(setOfCategoriesToRemove, person1.getID());

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
        ID catsObject = new CategoryID(new Denomination("Cats"),person1.getID());
        CategoryRepository newCategories = new CategoryRepository();

        HashSet<String> myCategories = new HashSet<>(Arrays.asList(house, cats, transport));

        //Act

        newCategories.addMultipleCategories(myCategories, person1.getID());
        boolean result = newCategories.isCategoryValid(catsObject);

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

        boolean realResult = newCategories.addMultipleCategories(myCategories, person1.getID());

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
        categoryList1.addMultipleCategories(myCategories, person1.getID());

        boolean result = categoryList1.isSetOfCategoriesValid(myCategories,person1.getID());
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

        boolean result = categoryList1.isSetOfCategoriesValid(myCategories,person1.getID());
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

        int actual = categoryList.numberOfCategoryInRepository();
        //Assert
        assertEquals(2, actual);
    }


    @Test
    @DisplayName("Test to validate number of categories in the category list - Empty CategoryList")
    void numberOfCategoryInTheCategoryListSuccessCaseEmptyCategoryList() {

        //Arrange
        CategoryRepository categoryList = new CategoryRepository(); //empty Category List

        //Act
        int actual = categoryList.numberOfCategoryInRepository();

        //Assert
        assertEquals(0, actual);
    }
}