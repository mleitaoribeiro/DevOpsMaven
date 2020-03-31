package switch2019.project.DTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoryDTOTest {

    /**
     * Tests for the different getter methods on the CreateCategoryInGroupDTOTest
     */

    @DisplayName("Test categoryDescription Getter")
    @Test
    public void getDescriptionTest(){
        //Arrange:
        CategoryDTO dto = new CategoryDTO("Groceries", "ID");

        //Act:
        String description = dto.getDenomination();

        //Assert:
        assertEquals("Groceries", description);
    }

    @DisplayName("Test categoryID Getter")
    @Test
    public void getPersonEmailTest(){
        //Arrange:
        CategoryDTO dto = new CategoryDTO("Groceries", "ID");

        //Act:
        String categoryID = dto.getOwnerID();

        //Assert:
        assertEquals("ID", categoryID);
    }

    /**
     * Tests for the equals method
     */
    @DisplayName("Test if Two CategoryDTO objects are the same")
    @Test
    public void equalsTestTrue(){
        //Arrange:
        CategoryDTO dto1 = new CategoryDTO("Groceries", "ID");
        CategoryDTO dto2 = new CategoryDTO("Groceries", "ID");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertTrue(areDtosTheSame);
    }

    @DisplayName("Test if Two CategoryDTO objects are NOT the same - different description")
    @Test
    public void equalsTestFalseDifferentDescription(){
        //Arrange:
        CategoryDTO dto1 = new CategoryDTO("Groceries", "ID");
        CategoryDTO dto2 = new CategoryDTO("Movies", "ID");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }

    @DisplayName("Test if Two CategoryDTO objects are NOT the same - different categoryID")
    @Test
    public void equalsTestFalseDifferentCategoryID(){
        //Arrange:
        CategoryDTO dto1 = new CategoryDTO("Groceries", "ID");
        CategoryDTO dto2 = new CategoryDTO("Groceries", "ID2");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }

    @DisplayName("Test if the CategoryDTO is equal after  - different categoryID")
    @Test
    public void equalsTestTrueSameObject(){
        //Arrange:
        CategoryDTO dto1 = new CategoryDTO("Groceries", "ID");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto1);

        //Assert:
        assertTrue(areDtosTheSame);
    }

    @DisplayName("null parameters - check if equals always returns false")
    @Test
    public void equalsTestFalseNullParameters(){
        //Arrange:
        CategoryDTO dto1 = new CategoryDTO("Groceries", "ID");
        CategoryDTO dto2 = null;

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }

    /**
     * Tests for the hashCode method
     */
    @DisplayName("Test if Two CategoryDTO objects are the same")
    @Test
    public void equalsHashCodeTrue(){
        //Arrange:
        CategoryDTO dto1 = new CategoryDTO("Groceries", "ID");
        CategoryDTO dto2 = new CategoryDTO("Groceries", "ID");

        //Act:
        boolean sameHashCode = (dto1.hashCode() == dto2.hashCode());

        //Assert:
        assertTrue(sameHashCode);
    }

    @DisplayName("Test if Two CategoryDTO objects are the same")
    @Test
    public void equalsHashCodeFalse(){
        //Arrange:
        CategoryDTO dto1 = new CategoryDTO("Groceries", "ID");
        CategoryDTO dto2 = new CategoryDTO("Movies", "ID");

        //Act:
        boolean sameHashCode = (dto1.hashCode() == dto2.hashCode());

        //Assert:
        assertFalse(sameHashCode);
    }

    @DisplayName("Test if Two CategoryDTO objects are NOT the same")
    @Test
    public void equalsHashCodeSame(){
        //Arrange:
        CategoryDTO dto1 = new CategoryDTO("Groceries", "ID");
        CategoryDTO dto2 = new CategoryDTO("Groceries", "ID");

        //Assert:
        assertEquals(dto1.hashCode(),dto2.hashCode());
    }
}

