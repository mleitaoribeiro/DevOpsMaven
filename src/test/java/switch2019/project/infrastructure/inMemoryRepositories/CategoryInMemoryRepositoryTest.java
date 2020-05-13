package switch2019.project.infrastructure.inMemoryRepositories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.infrastructure.inMemoryRepositories.CategoryInMemoryRepository;
import switch2019.project.utils.customExceptions.ArgumentNotFoundException;
import switch2019.project.utils.customExceptions.ResourceAlreadyExistsException;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.ID;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.*;
import switch2019.project.domain.repositories.CategoryRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CategoryInMemoryRepositoryTest {

    private CategoryRepository categoryRepository;

    @BeforeEach
    void universeSetUp() {
        categoryRepository = new CategoryInMemoryRepository();
        categoryRepository.createCategory(new Denomination("CGD"), new PersonID(new Email("amadeu1@gmail.com")));
        categoryRepository.createCategory(new Denomination("BPI"), new PersonID(new Email("amadeu2@gmail.com")));
        categoryRepository.createCategory(new Denomination("BIC"), new PersonID(new Email("amadeu2@gmail.com")));
    }

    /**
     * Tests for the CategoryRepository method toString
     */
    @Test
    @DisplayName("Test if method toString returns the categories in the Category Repository")
    public void validateToString() {
        //Arrange:
        Person person1 = new Person("Alexandre", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        CategoryRepository firstCategoryRepository = new CategoryInMemoryRepository();

        firstCategoryRepository.createCategory(new Denomination("cinema"), person1.getID());
        firstCategoryRepository.createCategory(new Denomination("jantar"), person1.getID());

        String expected = "Category Repository: [CINEMA, 1234@isep.pt, JANTAR, 1234@isep.pt]";

        //Act:
        String result = firstCategoryRepository.toString();

        //Assert:
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Test if method toString returns the categories in the Category Repository - False")
    public void validateToStringFalse() {
        //Arrange:
        Person person1 = new Person("Alexandre", new DateAndTime(1995, 12, 13), new Address("Porto"),
                new Address("Rua X", "Porto", "4520-266"), new Email("1234@isep.pt"));
        CategoryRepository firstCategoryRepository = new CategoryInMemoryRepository();

        firstCategoryRepository.createCategory(new Denomination("cinema"), person1.getID());
        firstCategoryRepository.createCategory(new Denomination("jantar"), person1.getID());

        String expected = "Category Repository: [1234@isep.pt, CINEMA, 1234@isep.pt, JANTAR]";

        //Act:
        String result = firstCategoryRepository.toString();

        //Assert:
        assertNotEquals(expected, result);
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
        Denomination oneDenomination = new Denomination("School expenses");
        Denomination otherDenomination = new Denomination("Health");
        CategoryRepository newCategoryRepository = new CategoryInMemoryRepository();

        Category oneCategory = new Category(new Denomination("School Expenses"), person1.getID());
        Category otherCategory = new Category(new Denomination("Health"), person1.getID());

        //Act
        Category createdOneCategory = newCategoryRepository.createCategory(oneDenomination, person1.getID());
        Category createdOtherCategory = newCategoryRepository.createCategory(otherDenomination, person1.getID());

        //Assert
        Assertions.assertAll(
                () -> assertEquals(oneCategory, createdOneCategory),
                () -> assertEquals(otherCategory, createdOtherCategory)
        );
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
        CategoryRepository newCategoryRepository = new CategoryInMemoryRepository();

        //Act
        try {
            newCategoryRepository.createCategory(new Denomination(null), person1.getID());
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The denomination can't be null or empty.", description.getMessage());
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
        Denomination originalCategory = new Denomination("Saude");
        Denomination duplicateCategory = new Denomination("saúde");

        CategoryRepository newCategoryRepository = new CategoryInMemoryRepository();
        newCategoryRepository.createCategory(originalCategory,person1.getID());

        //Act
        try{
            newCategoryRepository.createCategory(duplicateCategory,person1.getID());
        }

        //Assert
        catch (ResourceAlreadyExistsException categoryAlreadyExists) {
            assertEquals("This category already exists.", categoryAlreadyExists.getMessage());
        }

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

        CategoryRepository newCategoryRepository = new CategoryInMemoryRepository();

        //Act
        newCategoryRepository.createCategory(new Denomination(oneCategory), person1.getID());
        newCategoryRepository.createCategory(new Denomination(otherCategory), person1.getID());

        //Remove one Category

        boolean realResult = newCategoryRepository.removeCategory(new Denomination(otherCategoryObject), person1.getID());

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
        String otherCategory = "saúde2";
        String otherCategoryObject = "saúde";

        CategoryRepository newCategoryRepository = new CategoryInMemoryRepository();

        //Act
        newCategoryRepository.createCategory(new Denomination(oneCategory), person1.getID());
        newCategoryRepository.createCategory(new Denomination(otherCategory), person1.getID());
        //Remove one Category
        boolean realResult = newCategoryRepository.removeCategory(new Denomination(otherCategoryObject), person1.getID());

        //Assert
        assertTrue(realResult);
    }

    @Test
    @DisplayName("check if category is not in Repository and threfore cant be removed")
    void removeCategoryThatIsNotInTheRepository() {

        //Arrange:
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryRepository testCategoryRepository = new CategoryInMemoryRepository();

        //Act:
        boolean isACategoryNotInRepositoryRemoved = testCategoryRepository.removeCategory(new Denomination("Cinema"), person1.getID());

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

        CategoryRepository newCategoryRepository = new CategoryInMemoryRepository();

        newCategoryRepository.createCategory(new Denomination(oneCategory), person1.getID());
        newCategoryRepository.createCategory(new Denomination(otherCategory), person1.getID());

        //Act
        try {
            boolean realResult = newCategoryRepository.removeCategory(new Denomination(otherCategoryObject), person1.getID());
        }

        //Assert
        catch (IllegalArgumentException description) {
            assertEquals("The denomination can't be null or empty.", description.getMessage());
        }

    }

    @Test
    @DisplayName("Test if a category was removed from the Category Repository - a category that doesnt exists")
    void removeCategoryFromRepositoryDoesntExist() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        String oneCategory = "Saude";
        String otherCategoryObject = "Educação";

        CategoryRepository newCategoryRepository = new CategoryInMemoryRepository();
        newCategoryRepository.createCategory(new Denomination(oneCategory), person1.getID());

        //Act
        boolean realResult = newCategoryRepository.removeCategory(new Denomination(otherCategoryObject), person1.getID());

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
        Denomination categoryHealth = new Denomination("Health");
        Denomination categoryGym = new Denomination("Gym");
        Denomination categoryUniversity = new Denomination("University");

        //A collection of categories to be added
        HashSet<Denomination> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryUniversity));
        CategoryInMemoryRepository newCategoryInMemoryRepository = new CategoryInMemoryRepository();

        //Act
        boolean validateIfTheSetOfCategoriesWasAdded = newCategoryInMemoryRepository.addMultipleCategories(setOfCategories, person1.getID());

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
        Denomination categoryHealth = new Denomination("Health");
        Denomination categoryHealthDuplicated = new Denomination("Health");
        Denomination categoryBeauty = new Denomination("Beauty");

        CategoryInMemoryRepository newCategoryInMemoryRepository = new CategoryInMemoryRepository();

        //A collection of categories to be added
        HashSet<Denomination> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryHealthDuplicated, categoryBeauty));

        //Act
        //Add several categories simultaneously to Category Repository with method under test
        newCategoryInMemoryRepository.addMultipleCategories(setOfCategories, person1.getID());

        long realNumberOfCategoriesOfTheList = newCategoryInMemoryRepository.repositorySize();

        //Assert
        assertEquals(2, realNumberOfCategoriesOfTheList);

    }

    @Test
    @DisplayName("Add a Set of Categories to user Category Repository - Check if the same Category is not added simultaneously " +
            "Ignore letter capitalization and special characters. ")
    void addMultipleCategoriesToRepositoryWithTwoCategoriesCaseInsensitive() {
        // Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        // Categories to be included in Category Repository
        Denomination categoryHealth = new Denomination("Health");
        Denomination categoryHealthDuplicated = new Denomination("heálth");
        Denomination categoryBeauty = new Denomination("Beauty");

        CategoryInMemoryRepository newCategoryInMemoryRepository = new CategoryInMemoryRepository();

        //A collection of categories to be added
        HashSet<Denomination> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryHealthDuplicated, categoryBeauty));
        //Act
        //Add everal categories simultaneously to Category Repository with method under test
        newCategoryInMemoryRepository.addMultipleCategories(setOfCategories, person1.getID());

        long realNumberOfCategoriesOfTheList = newCategoryInMemoryRepository.repositorySize();

        assertEquals(2, realNumberOfCategoriesOfTheList);
    }


    /**
     * Test to remove a set of categories from user category Repository
     */

    @Test
    @DisplayName("Remove a Set of Categories from user Category Repository - Main Scenario")
    void removeMultipleCategoriesTRepositoryMainScenario() {
        // Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        // Categories to be included in Category List
        Denomination categoryHealth = new Denomination("Health");
        Denomination categoryGym = new Denomination("Gym");
        Denomination categoryBeauty = new Denomination("Beauty");

        CategoryInMemoryRepository newCategoryInMemoryRepository = new CategoryInMemoryRepository();

        //set of categories to be added
        HashSet<Denomination> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        newCategoryInMemoryRepository.addMultipleCategories(setOfCategories, person1.getID());

        //set of Categories to be removed from Categories List
        HashSet<Denomination> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryBeauty, categoryGym));

        //Act
        //Remove the set of categories with the method under test
        boolean realResult = newCategoryInMemoryRepository.removeMultipleCategories(setOfCategoriesToRemove, person1.getID());

        assertTrue(realResult);
    }

    @Test
    @DisplayName("Remove a Set of Categories from user Category Repository - Ignore letter capitalization and special characters")
    void removeMultipleCategoriesToRepositoryIgnoreLettersFormatAndSpecialCase() {
        // Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        // Categories to be included in Category List
        Denomination categoryHealth = new Denomination("Health");
        Denomination categoryGym = new Denomination("Gym");
        Denomination categoryBeauty = new Denomination("Beauty");
        Denomination categoryHealthLowerCase = new Denomination("health");
        Denomination categoryGymSpecialCharacter = new Denomination("Gým");
        Denomination categoryBeautyUpperCase = new Denomination("BEAUTY");

        CategoryInMemoryRepository newCategoryInMemoryRepository = new CategoryInMemoryRepository();

        // set of String to be added to Categories list
        HashSet<Denomination> setOfCategories = new HashSet<>(Arrays.asList(categoryHealth, categoryGym, categoryBeauty));
        newCategoryInMemoryRepository.addMultipleCategories(setOfCategories, person1.getID());

        //set of Categories to be removed from Categories List
        HashSet<Denomination> setOfCategoriesToRemove = new HashSet<>(Arrays.asList(categoryHealthLowerCase, categoryGymSpecialCharacter, categoryBeautyUpperCase));
        // Remove the previous categories with the method under test

        //Act
        boolean realResult = newCategoryInMemoryRepository.removeMultipleCategories(setOfCategoriesToRemove, person1.getID());

        //Assert
        assertTrue(realResult);
    }

    /**
     * Test to validate if a category is in the Repository
     */

    @Test
    @DisplayName("Test if a category is in the category Repository - true case")
    void testValidateIfCategoryIsInTheCategoryRepositoryTrueCase() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));

        Denomination house = new Denomination("House");
        Denomination cats = new Denomination("Cats");
        Denomination transport = new Denomination("Transport");

        ID catsObject = new CategoryID(new Denomination("Cats"), person1.getID());
        CategoryInMemoryRepository newCategories = new CategoryInMemoryRepository();

        HashSet<Denomination> myCategories = new HashSet<>(Arrays.asList(house, cats, transport));

        //Act
        newCategories.addMultipleCategories(myCategories, person1.getID());
        boolean result = newCategories.isIDOnRepository(catsObject);

        //Assert
        assertTrue(result);
    }


    @Test
    @DisplayName("Test if a category is in the category Repository - false case")
    void testValidateIfCategoryIsInTheCategoryRepositoryFalseCase() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        Denomination house = new Denomination("House");
        Denomination cats = new Denomination("Cats");
        Denomination transport = new Denomination("Transport");
        CategoryInMemoryRepository newCategories = new CategoryInMemoryRepository();

        HashSet<Denomination> myCategories = new HashSet<>(Arrays.asList(house, cats, transport));

        //Act
        boolean realResult = newCategories.addMultipleCategories(myCategories, person1.getID());

        //Assert
        assertTrue(realResult);
    }


    @Test
    @DisplayName("Test to validate if set of categories is the the category Repository-true case for all")
    void validateIfSetOfCategoriesIsInTheCategoryRepository() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryInMemoryRepository categoryInMemoryRepository1 = new CategoryInMemoryRepository();
        Denomination house = new Denomination("House");
        Denomination cats = new Denomination("Cats");
        Denomination transport = new Denomination("Transport");
        HashSet<Denomination> myCategories = new HashSet<>(Arrays.asList(house, cats, transport));

        //Act
        categoryInMemoryRepository1.addMultipleCategories(myCategories, person1.getID());

        boolean result = categoryInMemoryRepository1.isSetOfCategoriesValid(myCategories, person1.getID());
        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to validate if set of categories is the the category Repository adding one at the time-false case")
    void validateIfSetOfCategoriesIsInTheCategoryRepositoryNotAll() {
        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryInMemoryRepository categoryInMemoryRepository1 = new CategoryInMemoryRepository();

        Denomination house = new Denomination("House");
        Denomination cats = new Denomination("Cats");
        Denomination transport = new Denomination("Transport");

        HashSet<Denomination> myCategories = new HashSet<>(Arrays.asList(house, cats, transport));

        //Act
        categoryInMemoryRepository1.createCategory(new Denomination("House"), person1.getID());
        categoryInMemoryRepository1.createCategory(new Denomination("Cats"), person1.getID());

        boolean result = categoryInMemoryRepository1.isSetOfCategoriesValid(myCategories, person1.getID());
        //Assert
        assertFalse(result);
    }

    /**
     * Test to validate number of categories in the category Repository
     */

    @Test
    @DisplayName("Test to validate number of categories in the category Repository - Success Case")
    void numberOfCategoryInTheCategoryRepositorySuccessCase() {

        //Arrange
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryRepository categoryRepository = new CategoryInMemoryRepository();

        //Act
        categoryRepository.createCategory(new Denomination("Transports"), person1.getID());
        categoryRepository.createCategory(new Denomination("House"), person1.getID());

        long actual = categoryRepository.repositorySize();
        //Assert
        assertEquals(2, actual);
    }


    @Test
    @DisplayName("Test to validate number of categories in the category Repository - Empty CategoryRepository")
    void numberOfCategoryInTheCategoryRepositorySuccessCaseEmptyCategoryRepository() {

        //Arrange
        CategoryRepository categoryRepository = new CategoryInMemoryRepository(); //empty Category Repository

        //Act
        long actual = categoryRepository.repositorySize();

        //Assert
        assertEquals(0, actual);
    }

    @Test
    @DisplayName("Verify If category ID exists - Main Scenario")
    void verifyIfCategoryIDExists() {
        //Arrange
        CategoryRepository categoryRepository = new CategoryInMemoryRepository();
        Category newCategory = new Category((new Denomination("Hello")), new PersonID(new Email("hello@gmail.com")));
        //Act
        categoryRepository.createCategory(new Denomination("Hello"), new PersonID(new Email("hello@gmail.com")));

        boolean categoryIDExists = categoryRepository.isIDOnRepository(newCategory.getID());

        //Assert
        assertTrue(categoryIDExists);
    }

    @Test
    @DisplayName("Verify If Category ID exists -False Case")
    void verifyIfCategoryIDExistsOnRepositoryFalse() {
        //Arrange
        CategoryRepository categoryRepository = new CategoryInMemoryRepository();
        Category newCategory = new Category((new Denomination("Hello")), new PersonID(new Email("hello@gmail.com")));

        //Act
        categoryRepository.createCategory(new Denomination("BYE"), new PersonID(new Email("hello@gmail.com")));

        boolean categoryIDExists = categoryRepository.isIDOnRepository(newCategory.getID());

        //Assert
        assertFalse(categoryIDExists);
    }
    /**
     * Tests to method returnCategoriesByOwnerID
     */

    @Test
    @DisplayName("Test if Categories are returned by OwnerID - success case")
    void returnCategoriesByOwnerIDOneCategorie() {
        //Arrange
        Category cgdCategory = new Category(new Denomination("CGD")
                , new PersonID(new Email("amadeu1@gmail.com")));

        //Expected List of Categories
        Set<Category> expected = new HashSet<>(Arrays.asList(cgdCategory));

        //Act
        Set<Category> real = categoryRepository.returnCategoriesByOwnerID(new PersonID(new Email("amadeu1@gmail.com")));

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Test if Categories are returned by OwnerID - success case")
    void returnCategoriesByOwnerIDSeveralCategories() {
        //Arrange
        Category bpiCategory = new Category(new Denomination("BPI"),
                 new PersonID(new Email("amadeu2@gmail.com")));
        Category bicCategory = new Category(new Denomination("BIC"),
                 new PersonID(new Email("amadeu2@gmail.com")));

        //Expected List of Categories
        Set<Category> expected = new HashSet<>(Arrays.asList(bpiCategory, bicCategory));

        //Act
        Set<Category> real = categoryRepository.returnCategoriesByOwnerID(new PersonID(new Email("amadeu2@gmail.com")));

        //Assert
        assertEquals(expected, real);
    }

    @Test
    @DisplayName("Test if Categories are returned by OwnerID - owner id not exists")
    void returnCategoriesByOwnerIDDontExistException() {
        //Arrange
        PersonID fakeID = (new PersonID(new Email("amadeu5@gmail.com")));
        try {
            Set<Category> real = categoryRepository.returnCategoriesByOwnerID(fakeID);
        } catch (ArgumentNotFoundException ex) {
            assertEquals("No category found with that ID.", ex.getMessage());
        }
    }

    @Test
    @DisplayName("Test if Categories are returned by OwnerID - null ID")
    void returnCategoriesByOwnerIDNullException() {
        //Arrange
        try {
            Set<Category> real = categoryRepository.returnCategoriesByOwnerID(null);
        } catch (IllegalArgumentException ex) {
            assertEquals("Owner ID can't be null.", ex.getMessage());
        }
    }
}