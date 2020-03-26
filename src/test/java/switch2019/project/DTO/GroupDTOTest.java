package switch2019.project.DTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.person.Address;
import switch2019.project.domain.domainEntities.person.Email;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.domainEntities.shared.DateAndTime;

import static org.junit.jupiter.api.Assertions.*;

class GroupDTOTest {

    /**
     * Test to Get Method:
     */

    @Test
    @DisplayName("Test to Get Method - Family Group Description")
    void getFamilyGroupDescription() {
        //Arrange
        GroupDTO dto = new GroupDTO("Querido's Family");
        String expected = "QUERIDO'S FAMILY";

        //Act
        String actual = dto.getGroupDescription();

        //Assert
        assertEquals(expected, actual);
    }

    /**
     * Tests to Equals Method:
     */

    @Test
    @DisplayName("Family Group Description - Same Description")
    void equalsFamilyGroupDescriptionSameDescription() {
        //Arrange
        GroupDTO dto1 = new GroupDTO("Querido's Family");
        GroupDTO dto2 = new GroupDTO("Querido's Family");

        //Act
        boolean result = dto1.equals(dto2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Family Group Description - Different Description")
    void equalsFamilyGroupDescriptionDifferentDescription() {
        //Arrange
        GroupDTO dto1 = new GroupDTO("Querido's Family");
        GroupDTO dto2 = new GroupDTO("Santo's Family");

        //Act
        boolean result = dto1.equals(dto2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Family Group Description - Same Object")
    void equalsFamilyGroupDescriptionSameObject() {
        //Arrange
        GroupDTO dto1 = new GroupDTO("Querido's Family");
        GroupDTO dto2 = new GroupDTO("Querido's Family");

        //Act
        boolean result = dto1.equals(dto2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Family Group Description - Null Object")
    void equalsFamilyGroupDescriptionNullObject() {
        //Arrange
        GroupDTO dto1 = new GroupDTO("Querido's Family");
        GroupDTO dto2 = null;

        //Act
        boolean result = dto1.equals(dto2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Family Group Description - Different type of Object")
    void equalsFamilyGroupDescriptionDifferentTypeOfObject() {
        //Arrange
        GroupDTO dto1 = new GroupDTO("Querido's Family");
        Person person1 = new Person("Raquel", new DateAndTime(1989, 10, 13), new Address("Gaia"),
                new Address("Av. Republica", "Porto", "4430-852"), new Email("xpto@isep.pt"));

        //Act
        boolean result = dto1.equals(person1);

        //Assert
        assertFalse(result);
    }

    /**
     * Tests to Hashcode:
     */

    @Test
    @DisplayName("Family Group Description - Same Hashcode - True")
    void hashcodeFamilyGroupDescriptionSameHashCodeTrue() {
        //Arrange
        GroupDTO dto1 = new GroupDTO("Querido's Family");
        GroupDTO dto2 = new GroupDTO("Querido's Family");

        //Act & Assert:
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    @DisplayName("Family Group Description - Same Hashcode - False")
    void hashcodeFamilyGroupDescriptionSameHashCodeFalse() {
        //Arrange
        GroupDTO dto1 = new GroupDTO("Querido's Family");
        GroupDTO dto2 = new GroupDTO("Santo's Family");

        //Act & Assert:
        assertNotEquals(dto1.hashCode(), dto2.hashCode());
    }


}


