package switch2019.project.DTO.serviceDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatePersonalTransactionDTOTest {

    @Test
    @DisplayName("Test for method equals - exactly the same object")
    void testEqualsSameObject() {

        // Arrange
        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO("1191762@isep.ipp.pt",
                20.50,  "EUR", "beers","19-05-2002", "drinks","Switch Account",
                "Isep Bar Account", "debit");

        // Act
        boolean result = createPersonalTransactionDTO.equals(createPersonalTransactionDTO);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test for method equals - two objects with the same parameters")
    void testEqualsTwoObjectSameParameters() {

        // Arrange
        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO("1191762@isep.ipp.pt",
                20.50, "EUR","beers","19-05-2002", "drinks","Switch Account",
                "Isep Bar Account", "debit");

        CreatePersonalTransactionDTO createPersonalTransactionDTOCopy = new CreatePersonalTransactionDTO("1191762@isep.ipp.pt",
                20.50, "EUR", "beers", "19-05-2002","drinks", "Switch Account",
                "Isep Bar Account", "debit");

        // Act
        boolean result = createPersonalTransactionDTO.equals(createPersonalTransactionDTOCopy);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test for method equals - two objects with different parameters")
    void testEqualsTwoObjectDifferentParameters() {

        // Arrange
        CreatePersonalTransactionDTO createPersonalTransactionDTO1 = new CreatePersonalTransactionDTO("1191762@isep.ipp.pt",
                20.50, "EUR", "beers","15-02-2020","drinks", "Switch Account",
                "Isep Bar Account", "debit");

        CreatePersonalTransactionDTO createPersonalTransactionDTO2 = new CreatePersonalTransactionDTO("119180@isep.ipp.pt",
                20.50, "EUR", "beers","15-02-2020","drinks", "Switch Account",
                "Isep Bar Account", "debit");

        // Act
        boolean result = createPersonalTransactionDTO1.equals(createPersonalTransactionDTO2);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test for method hashcode - true")
    void testHashCodeTRUE() {

        // Arrange
        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO("1191762@isep.ipp.pt",
                20.50, "EUR", "beers", "15-02-2020","drinks", "Switch Account",
                "Isep Bar Account", "debit");

        CreatePersonalTransactionDTO createPersonalTransactionDTOCopy = new CreatePersonalTransactionDTO("1191762@isep.ipp.pt",
                20.50, "EUR", "beers","15-02-2020", "drinks", "Switch Account",
                "Isep Bar Account", "debit");

        // Act
        boolean result = createPersonalTransactionDTO.hashCode() == createPersonalTransactionDTOCopy.hashCode();

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test for method hashcode - false")
    void testHashCodeFALSE() {

        // Arrange
        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO("1191762@isep.ipp.pt",
                20.50, "EUR", "beers", "15-02-2020","drinks", "Switch Account",
                "Isep Bar Account", "debit");

        CreatePersonalTransactionDTO createPersonalTransactionDTOCopy = new CreatePersonalTransactionDTO("1191780@isep.ipp.pt",
                20.50, "EUR", "beers", "15-02-2020","drinks", "Switch Account",
                "Isep Bar Account", "debit");

        // Act
        boolean result = createPersonalTransactionDTO.hashCode() == createPersonalTransactionDTOCopy.hashCode();

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test for method getDescription")
    void getGroupDescription() {

        // Arrange
        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO("1191762@isep.ipp.pt",
                20.50, "EUR", "beers",  "15-02-2020","drinks", "Switch Account",
                "Isep Bar Account", "debit");

        String PersonEmailExpected = "1191762@isep.ipp.pt";

        // Act
        String PersonEmail = createPersonalTransactionDTO.getPersonEmail();

        // Assert
        assertEquals(PersonEmailExpected, PersonEmail);
    }

    @Test
    @DisplayName("Test for method getAmount")
    void getAmount() {

        // Arrange
        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO("1191762@isep.ipp.pt",
                20.50, "EUR", "beers","15-02-2020", "drinks", "Switch Account",
                "Isep Bar Account", "debit");

        double amountExpected = 20.50;

        // Act
        double amount = createPersonalTransactionDTO.getAmount();

        // Assert
        assertEquals(amountExpected, amount);
    }

    @Test
    @DisplayName("Test for method getCurrency")
    void getCurrency() {

        // Arrange
        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO("1191762@isep.ipp.pt",
                20.50, "EUR", "beers", "15-05-2020","drinks", "Switch Account",
                "Isep Bar Account", "debit");

        String currencyExpected = "EUR";

        // Act
        String currency = createPersonalTransactionDTO.getCurrency();

        // Assert
        assertEquals(currencyExpected, currency);
    }

    @Test
    @DisplayName("Test for method getDescription")
    void getDescription() {

        // Arrange
        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO("1191762@isep.ipp.pt",
                20.50, "EUR", "beers","15-02-2020",  "drinks", "Switch Account",
                "Isep Bar Account", "debit");

        String descriptionExpected = "beers";

        // Act
        String description = createPersonalTransactionDTO.getDescription();

        // Assert
        assertEquals(descriptionExpected, description);
    }

    @Test
    @DisplayName("Test for method getCategory")
    void getCategory() {

        // Arrange
        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO("1191762@isep.ipp.pt",
                20.50, "EUR", "beers", "15-05-2020", "drinks", "Switch Account",
                "Isep Bar Account", "debit");

        String categoryExpected = "drinks";

        // Act
        String category = createPersonalTransactionDTO.getCategory();

        // Assert
        assertEquals(categoryExpected, category);
    }

    @Test
    @DisplayName("Test for method getAccountFrom")
    void getAccountFrom() {

        // Arrange
        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO("1191762@isep.ipp.pt",
                20.50, "EUR", "beers", "drinks", "15-02-2020","Switch Account",
                "Isep Bar Account", "debit");

        String accountFromExpected = "Switch Account";

        // Act
        String accountFrom = createPersonalTransactionDTO.getAccountFrom();

        // Assert
        assertEquals(accountFromExpected, accountFrom);
    }

    @Test
    @DisplayName("Test for method getAccountTo")
    void getAccountTo() {

        // Arrange
        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO("1191762@isep.ipp.pt",
                20.50, "EUR", "beers", "15-02-2020","drinks", "Switch Account",
                "Isep Bar Account", "debit");

        String accountToExpected = "Isep Bar Account";

        // Act
        String accountTo = createPersonalTransactionDTO.getAccountTo();

        // Assert
        assertEquals(accountToExpected, accountTo);
    }

    @Test
    @DisplayName("Test for method getType")
    void getType() {

        // Arrange
        CreatePersonalTransactionDTO createPersonalTransactionDTO = new CreatePersonalTransactionDTO("1191762@isep.ipp.pt",
                20.50, "EUR", "beers","15-02-2020", "drinks", "Switch Account",
                "Isep Bar Account", "debit");

        String typeExpected = "debit";

        // Act
        String type = createPersonalTransactionDTO.getType();

        // Assert
        assertEquals(typeExpected, type);
    }

}
