package switch2019.project.domain.domainEntities.shared;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import switch2019.project.domain.domainEntities.category.Category;
import switch2019.project.domain.domainEntities.frameworks.OwnerID;
import switch2019.project.domain.domainEntities.group.Group;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryIDTest {
    @Test
    @DisplayName("Test if two CategoryID are the same - true case")
    void testEqualCategoryIDTrueCase() {
        //Arrange:
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryID categoryID1 = new CategoryID(new Denomination("Gym"), person1.getID());
        CategoryID categoryID2 = new CategoryID(new Denomination("Gym"), person1.getID());

        //Act:
        boolean result = categoryID1.equals(categoryID2);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if two CategoryID are the same - same object")
    void testEqualsCategoryIDSameObject() {
        //Arrange:
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryID categoryID1 = new CategoryID(new Denomination("Gym"), person1.getID());
        CategoryID categoryID2 = categoryID1;

        //Act:
        boolean result = categoryID1.equals(categoryID2);

        //Assert:
        assertTrue(result);
    }

    @Test
    @DisplayName("Test if two CategoryID are the same - false case - same owner, different denomination")
    void testEqualsCategoryIDSameOwnerDifferentDenomination() {
        //Arrange:
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryID categoryID1 = new CategoryID(new Denomination("Food"), person1.getID());
        CategoryID categoryID2 = new CategoryID(new Denomination("House"), person1.getID());

        //Act:
        boolean result = categoryID1.equals(categoryID2);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Test if two CategoryID are the same - false case - different owner, same denomination")
    void testEqualsCategoryIDDifferentOwnerSameDenomination() {
        //Arrange:
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        Group group1 = new Group(new Description("The Ones"),person1.getID());
        CategoryID categoryID1 = new CategoryID(new Denomination("House"), person1.getID());
        CategoryID categoryID2 = new CategoryID(new Denomination("House"), group1.getID());

        //Act:
        boolean result = categoryID1.equals(categoryID2);

        //Assert:
        assertFalse(result);
    }


    @Test
    @DisplayName("Test if two CategoryID are the same - different type of objects")
    void testEqualsCategoryIDDifferentType() {
        //Arrange:
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryID categoryID1 = new CategoryID(new Denomination("Gym"), person1.getID());
        Category category1 = new Category(new Denomination("food"), new PersonID(new Email("personEmail@email.com")));

        //Act:
        boolean result = categoryID1.equals(category1);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Test if two categoryID are the same - one categoryID is null")
    void testEqualsGroupIDNullCase() {
        //Arrange:
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryID categoryID1 = new CategoryID(new Denomination("Gym"), person1.getID());

        //Act:
        boolean result = categoryID1.equals(null);

        //Assert:
        assertFalse(result);
    }

    @Test
    @DisplayName("Test if two categoryID have the same hashcode - True")
    void testHashCodeTrue() {
        //Arrange:
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryID categoryID1 = new CategoryID(new Denomination("Gym"), person1.getID());
        CategoryID categoryID2 = new CategoryID(new Denomination("Gym"), person1.getID());

        //Act & Assert:
        assertEquals(categoryID1.hashCode(),categoryID2.hashCode());
    }

    @Test
    @DisplayName("Test if two categoryID have the same hashcode - False")
    void testHashCodeFalse() {
        //Arrange:
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        Group group1 = new Group (new Description("The ones"),person1.getID());
        CategoryID categoryID1 = new CategoryID(new Denomination("Gym"), person1.getID());
        CategoryID categoryID2 = new CategoryID(new Denomination("Gym"), group1.getID());

        //Act & Assert:
        assertNotEquals(categoryID1.hashCode(),categoryID2.hashCode());
    }


    @Test
    @DisplayName("Test to getDenomination method")
    void getDenomination() {
        //Arrange:
        Person person1 = new Person("Raquel", new DateAndTime(1989, 1, 1),
                new Address("Porto"), new Address("Rua xpto", "Porto", "4430-300"), new Email("1234@isep.pt"));
        CategoryID categoryID1 = new CategoryID(new Denomination("Gym"), person1.getID());

        //Act:
        String result = categoryID1.getDenominationString();

        //Assert:
        assertEquals("GYM",result);
    }

    /**
     * Tests to constructor method
     */

    @Test
    @DisplayName("Test to Constructor - Null Denomination")
    void testCategoryIDNullDenomination() {
        //Arrange:
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group = new Group(new Description("Running Team"),person.getID());
        //Act:
        try {
            CategoryID categoryID1 = new CategoryID(null,group.getID());
        }
        //Assert:
        catch (IllegalArgumentException categoryID) {
            assertEquals("Neither the Denomination nor OwnerID can be null.", categoryID.getMessage());
        }
    }

    @Test
    @DisplayName("Test to Constructor - Null Owner")
    void testCategoryIDNullOwner() {
        //Act:
        try {
            CategoryID categoryID1 = new CategoryID(new Denomination("Car expenses"), null);
        }
        //Assert:
        catch (IllegalArgumentException categoryID) {
            assertEquals("Neither the Denomination nor OwnerID can be null.", categoryID.getMessage());
        }
    }

    @Test
    @DisplayName("Test to Constructor - Null Owner & Null Denomination")
    void testCategoryIDNullOwnerAndDenomination() {
        //Act:
        try {
            CategoryID categoryID1 = new CategoryID(null, null);
        }
        //Assert:
        catch (IllegalArgumentException categoryID) {
            assertEquals("Neither the Denomination nor OwnerID can be null.", categoryID.getMessage());
        }
    }

    @Test
    @DisplayName("Test toString method- true ")
    void testToStringMethod() {
        //Arrange
        CategoryID categoryID = new CategoryID(new Denomination("FOOD"),
                new PersonID(new Email("marta@gmail.com")));
        String expectedResult = "FOOD, marta@gmail.com";
        //Act
        String realResult = categoryID.toString();
        //Assert
        assertEquals(expectedResult, realResult);
    }

    @Test
    @DisplayName("Test toString method - false")
    void testToStringMethodFalse() {
        //Arrange
        CategoryID categoryID = new CategoryID(new Denomination("FOOD"),
                new PersonID(new Email("marta@gmail.com")));
        String unExpectedResult = "marta@gmail.com, FOOD";
        //Act
        String realResult = categoryID.toString();
        //Assert
        assertNotEquals(unExpectedResult, realResult);
    }

    /**
     * Tests for the Denomination getter as an object
     */
    @Test
    @DisplayName("Test if the Denomination content is correct")
    void getDenominationTestHappyCase() {
        //Arrange:
        CategoryID categoryID = new CategoryID(new Denomination("Groceries"), new GroupID(new Description("friends")));
        Denomination expectedDenomination = new Denomination ("GROCERIES");

        //Act:
        Denomination actualDenomination = categoryID.getDenomination();

        //Assert:
        assertEquals(expectedDenomination,actualDenomination);
    }

    @Test
    @DisplayName("Test if the Denomination content is incorrect")
    void getDenominationTestFail() {
        //Arrange:
        CategoryID categoryID = new CategoryID(new Denomination("food"), new GroupID(new Description("friends")));
        Denomination expectedDenomination = new Denomination ("GROCERIES");

        //Act:
        Denomination actualDenomination = categoryID.getDenomination();

        //Assert:
        assertNotEquals(expectedDenomination,actualDenomination);
    }

    /**
     * Tests for the Denomination getter as a String
     */
    @Test
    @DisplayName("Test if the OwnerID string is correct")
    void getOwnerIdTestHappyCase() {
        //Arrange:
        CategoryID categoryID = new CategoryID(new Denomination("Groceries"), new GroupID(new Description("friends")));
        String ownerIdExpected = "FRIENDS";

        //Act:
        String actualOwnerId = categoryID.getOwnerIDString();

        //Assert:
        assertEquals(ownerIdExpected,actualOwnerId);
    }

    @Test
    @DisplayName("Test if the OwnerID string is incorrect")
    void getOwnerIdTestFail() {
        //Arrange:
        CategoryID categoryID = new CategoryID(new Denomination("Groceries"), new GroupID(new Description("friends")));
        String ownerIdExpected = "FAMILY";

        //Act:
        String actualOwnerId = categoryID.getOwnerIDString();

        //Assert:
        assertNotEquals(ownerIdExpected,actualOwnerId);
    }


    @Test
    @DisplayName("Get Owner ID from CategoryID - PersonID")
    void getOwnerID() {

            //Arrange
            Person person = new Person("Marta", new DateAndTime(1996, 4, 27),
                    new Address("Porto"), new Address("Rua X", "Porto", "4450-365"), new Email("1234@isep.pt"));
            CategoryID categoryID = new CategoryID(new Denomination("switch"), person.getID());

            OwnerID realOwnerID = categoryID.getOwnerID();
            PersonID expectedOwnerID = person.getID();

            //Assert
            assertEquals(expectedOwnerID, realOwnerID);
        }

    @Test
    @DisplayName("Get Owner ID from categoryID - GroupID")
    void testIfGetOwnerIDGroup() {
        //Arrange
        Person person = new Person("John", new DateAndTime(2000, 12, 4), new Address("London"),
                new Address("Rua B", "Feira", "4520-233"), new Email("1234@isep.pt"));
        Group group = new Group(new Description("Friends with Benefits"),person.getID());
        CategoryID categoryID = new CategoryID(new Denomination("switch"), group.getID());

        OwnerID realOwnerID = categoryID.getOwnerID();
        GroupID expectedOwnerID = group.getID();

        //Assert
        assertEquals(expectedOwnerID, realOwnerID);
    }
    }
