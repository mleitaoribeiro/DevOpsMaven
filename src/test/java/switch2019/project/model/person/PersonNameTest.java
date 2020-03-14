package switch2019.project.model.person;

import org.junit.jupiter.api.Test;
import switch2019.project.model.shared.Denomination;

import static org.junit.jupiter.api.Assertions.*;

class PersonNameTest {

    @Test
    void getPersonNameComplete() {
        //Arrange:
        PersonName personName = new PersonName("marTA    gomES   dE  lEMos  pInhEIro");
        String expected = "Marta Gomes de Lemos Pinheiro";

        //Act:
        String result = personName.getPersonName();

        //Assert:
        assertEquals(expected, result);
    }

    @Test
    void getPersonNameNullCase() {

        //Arrange & Act
        try {
            new PersonName(null);
        }

        //Assert
        catch (IllegalArgumentException getTransactionsInDateRange) {
            assertEquals("The name can't be empty or null.", getTransactionsInDateRange.getMessage());
        }
    }

    @Test
    void getPersonNameEmptyCase() {

        //Arrange & Act
        try {
            new PersonName("");
        }

        //Assert
        catch (IllegalArgumentException getTransactionsInDateRange) {
            assertEquals("The name can't be empty or null.", getTransactionsInDateRange.getMessage());
        }
    }

    @Test
    void getPersonNameRemoveExtraSpaces() {
        //Arrange:
        PersonName personName = new PersonName("      Marta     Gomes  de   Lemos     Pinheiro    ");
        String expected = "Marta Gomes de Lemos Pinheiro";

        //Act:
        String result = personName.getPersonName();

        //Assert:
        assertEquals(expected, result);
    }

    @Test
    void getPersonNameCapitalizeEachWord() {
        //Arrange:
        PersonName personName = new PersonName("marTA gOmES lEmoS piNHEiRO");
        String expected = "Marta Gomes Lemos Pinheiro";

        //Act:
        String result = personName.getPersonName();

        //Assert:
        assertEquals(expected, result);
    }

    @Test
    void getPersonNameExceptionalCases() {
        //Arrange:
        PersonName personName = new PersonName("marTA dA DE dO gomES dAS dOs lEMos pInhEIro");
        String expected = "Marta da de do Gomes das dos Lemos Pinheiro";

        //Act:
        String result = personName.getPersonName();

        //Assert:
        assertEquals(expected, result);
    }

    @Test
    void getFirstAndLastName() {
        //Arrange:
        PersonName personName = new PersonName("marTA dA DE dO gomES dAS dOs lEMos pInhEIro");
        String expected = "Marta Pinheiro";

        //Act:
        String result = personName.getFirstAndLastName();

        //Assert:
        assertEquals(expected, result);
    }

    @Test
    void personNameEqualsSameCase() {
        //Arrange:
        PersonName personName = new PersonName("Marta Gomes de Lemos Pinheiro");
        PersonName personName2 = new PersonName("Marta Gomes de Lemos Pinheiro");

        //Act:
        boolean result = personName.equals(personName2);

        //Assert:
        assertTrue(result);
    }

    @Test
    void personNameEqualsDifferentCase() {
        //Arrange:
        PersonName personName = new PersonName("marTA dA DE dO gomES dAS dOs lEMos pInhEIro");
        PersonName personName2 = new PersonName("mArta DA de do gOmEs das DOs LeMoS PinheirO");

        //Act:
        boolean result = personName.equals(personName2);

        //Assert:
        assertTrue(result);
    }

    @Test
    void personNameEqualsSameObject() {
        //Arrange:
        PersonName personName = new PersonName("Marta Gomes de Lemos Pinheiro");

        //Act:
        boolean result = personName.equals(personName);

        //Assert:
        assertTrue(result);
    }

    @Test
    void personNameEqualsDifferentObject() {
        //Arrange:
        PersonName personName = new PersonName("Marta Pinheiro");
        Denomination denomination = new Denomination("Just Testing");
        //Act:
        boolean result = personName.equals(denomination);
        //Assert:
        assertFalse(result);
    }
    @Test
    void personNameEqualsNullObject() {
        //Arrange:
        PersonName personName = new PersonName("Marta Pinheiro");
        PersonName personName2 = null;
        //Act:
        boolean result = personName.equals(personName2);
        //Assert:
        assertFalse(result);
    }

}