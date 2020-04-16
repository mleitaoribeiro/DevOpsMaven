package switch2019.project.DTO.SerializationDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.DTO.ServiceDTO.CreatePersonAccountDTO;
import switch2019.project.domain.domainEntities.shared.Description;

import static org.junit.jupiter.api.Assertions.*;

class CreatePersonAccountDTOTest {

    /**
     * Tests for the different getter methods on the CreatePersonAccountDTO Class
     */

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
    @DisplayName("Test if Two CreatePersonAccountDTO objects are the same")
    public void equalsTestTrue(){
        //Arrange:
        CreatePersonAccountDTO dto1  = new CreatePersonAccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");
        CreatePersonAccountDTO dto2 = new  CreatePersonAccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertTrue(areDtosTheSame);
    }

    @Test
    @DisplayName("Test if Two CreatePersonAccountDTO objects are the same - Same object")
    public void equalsTestSameObject(){
        //Arrange:
        CreatePersonAccountDTO dto1  = new CreatePersonAccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");

        //Act & Assert:
        assertTrue(dto1.equals(dto1));
    }

    @DisplayName("Test if Two CreatePersonAccountDTO objects are the same - Same object")
    public void equalsTestFalse(){
        //Arrange:
        CreatePersonAccountDTO dto1  = new CreatePersonAccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");
        CreatePersonAccountDTO dto2 = new  CreatePersonAccountDTO("santos@xpto.pt", "kelle account","expenses");


        //Act & Assert:
        assertFalse(dto1.equals(dto1));
    }


    @Test
    @DisplayName("Test if Two CreatePersonAccountDTO objects are NOT the same - different description")
    public void equalsTestFalseDifferentDescription(){
        //Arrange:
        CreatePersonAccountDTO dto1 = new  CreatePersonAccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");
        CreatePersonAccountDTO dto2 = new  CreatePersonAccountDTO("raquel.santos@xpto.pt", "kelle account","house");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }

    @Test
    @DisplayName("Test if Two CreatePersonAccountDTO objects are NOT the same - different ownerID")
    public void equalsTestFalseDifferentOwnerID(){
        //Arrange:
        CreatePersonAccountDTO dto1 = new  CreatePersonAccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");
        CreatePersonAccountDTO dto2 = new  CreatePersonAccountDTO("raquel.rodrigues@xpto.pt", "kelle account","expenses");

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }


    @Test
    @DisplayName("null parameters - check if equals always returns false")
    public void equalsTestFalseNullParameters(){
        //Arrange:
        CreatePersonAccountDTO dto1 = new  CreatePersonAccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");
        CreatePersonAccountDTO dto2 = null;

        //Act:
        boolean areDtosTheSame = dto1.equals(dto2);

        //Assert:
        assertFalse(areDtosTheSame);
    }



    @Test
    @DisplayName("Test if Two CreatePersonAccountDTO objects are the same - different type of object")
    public void equalsTestFalseDifferentTypeOfObject(){
        //Arrange:
        CreatePersonAccountDTO dto1 = new  CreatePersonAccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");
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
    @DisplayName("Test if Two CreatePersonAccountDTO objects are the same")
    public void equalsHashCodeFalse(){
        //Arrange:
        CreatePersonAccountDTO dto1 = new  CreatePersonAccountDTO("raquel.santos@xpto.pt", "kelle account","expenses for house");
        CreatePersonAccountDTO dto2 = new  CreatePersonAccountDTO("raquel.santos@xpto.pt", "kelle account","expenses for house");

        //Act:
        boolean sameHashCode = (dto1.hashCode() == dto2.hashCode());

        //Assert:
        assertTrue(sameHashCode);
    }

    @DisplayName("Test if Two CreatePersonAccountDTO objects are NOT the same")
    @Test
    public void equalsHashCodeSame(){
        //Arrange:
        CreatePersonAccountDTO dto1 = new  CreatePersonAccountDTO("raquel.santos@xpto.pt", "kelle account","expenses");
        CreatePersonAccountDTO dto2 = new  CreatePersonAccountDTO("raquel.santos@xpto.pt", "raquel account","expenses");

        //Act:
        boolean sameHashCode = (dto1.hashCode() == dto2.hashCode());

        //Assert:
        assertFalse(sameHashCode);
    }
}