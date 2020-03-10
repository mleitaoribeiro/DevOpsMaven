package switch2019.project.model.person;

import org.junit.jupiter.api.Test;
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
}