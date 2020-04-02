package switch2019.project.DTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.shared.Description;

import static org.junit.jupiter.api.Assertions.*;

class CreatePersonAccountInfoDTOTest {

    @Test
    @DisplayName("Test personEmail Getter")
    void getPersonEmailTest() {

        //Assert
        CreatePersonAccountDTO dto = new CreatePersonAccountDTO("joao.cardoso_12@isep.ipp.pt", "Revolut", "Online Shopping");

        //Act:
        String description = dto.getPersonEmail();

        //Assert:
        assertEquals("joao.cardoso_12@isep.ipp.pt", description);

    }


    @Test
    @DisplayName("Test accountDenomination getter")
    void getAccountDenominationTest() {

        //Assert
        CreatePersonAccountDTO dto = new CreatePersonAccountDTO("joao.cardoso_12@isep.ipp.pt", "Revolut", "Online Shopping");

        //Act:
        String description = dto.getAccountDenomination();

        //Assert:
        assertEquals("Revolut", description);

    }

    @Test
    @DisplayName("Test accountDescription getter")
    void getAccountDescriptionTest() {

        //Assert
        CreatePersonAccountDTO dto = new CreatePersonAccountDTO("joao.cardoso_12@isep.ipp.pt", "Revolut", "Online Shopping");

        //Act:
        String description = dto.getAccountDescription();

        //Assert:
        assertEquals("Online Shopping", description);

    }

    /**
     * Tests for the equals method
     */

    @Test
    @DisplayName("Test if Two AccountDTO objects are the same")
    public void equalsTestTrue(){
        //Arrange:
        AccountDTO dto1 = new AccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");
        AccountDTO dto2 = new AccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertTrue(areDtosTheSame);
    }

    @Test
    @DisplayName("Test if Two AccountDTO objects are NOT the same - different description")
    public void equalsTestFalseDifferentDescription(){
        //Arrange:
        AccountDTO dto1 = new AccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");
        AccountDTO dto2 = new AccountDTO("raquel.santos@xpto.pt", "kelle account","house");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }

    @Test
    @DisplayName("Test if Two AccountDTO objects are NOT the same - different ownerID")
    public void equalsTestFalseDifferentOwnerID(){
        //Arrange:
        AccountDTO dto1 = new AccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");
        AccountDTO dto2 = new AccountDTO("raquel.rodrigues@xpto.pt", "kelle account","expenses");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }


    @Test
    @DisplayName("null parameters - check if equals always returns false")
    public void equalsTestFalseNullParameters(){
        //Arrange:
        AccountDTO dto1 = new AccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");
        AccountDTO dto2 = null;

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }



    @Test
    @DisplayName("Test if Two AccountDTO objects are the same - different type of object")
    public void equalsTestFalseDifferentTypeOfObject(){
        //Arrange:
        AccountDTO dto1 = new AccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");
        Description dto2 = new Description("TestEquals");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }


    /**
     * Tests for the hashCode method
     */

    @Test
    @DisplayName("Test if Two AccountDTO objects are the same")
    public void equalsHashCodeFalse(){
        //Arrange:
        AccountDTO dto1 = new AccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");
        AccountDTO dto2 = new AccountDTO("raquel.santos@xpto.pt", "global account","expenses for house");

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