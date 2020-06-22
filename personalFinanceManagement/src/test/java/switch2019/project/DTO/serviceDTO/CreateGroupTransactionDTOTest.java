package switch2019.project.DTO.serviceDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.shared.Denomination;

import static org.junit.jupiter.api.Assertions.*;

class CreateGroupTransactionDTOTest {

    @Test
    @DisplayName("Test for method equals - exactly the same object")
    void testEqualsSameObject() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50,  "EUR", "19-05-2020", "beers","drinks",
                "Switch Account", "Isep Bar Account", "debit");

        // Act
        boolean result = createGroupTransactionDTO.equals(createGroupTransactionDTO);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test for method equals - two objects with the same parameters")
    void testEqualsTwoObjectSameParameters() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR","19-05-2020","beers", "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        CreateGroupTransactionDTO createGroupTransactionDTOCopy = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers", "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        // Act
        boolean result = createGroupTransactionDTO.equals(createGroupTransactionDTOCopy);

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test for method equals - two objects with different parameters")
    void testEqualsTwoObjectDifferentParameters() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO1 = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers","drinks",
                "Switch Account", "Isep Bar Account", "debit");

        CreateGroupTransactionDTO createGroupTransactionDTO2 = new CreateGroupTransactionDTO("Bashtards",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers","drinks",
                "Switch Account", "Isep Bar Account", "debit");

        // Act
        boolean result = createGroupTransactionDTO1.equals(createGroupTransactionDTO2);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test for method equals - two objects form different classes")
    void testEqualsTwoObjectFromDifferentClasses() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers","drinks",
                "Switch Account", "Isep Bar Account", "debit");

        Denomination denomination = new Denomination("bashtards");

        // Act
        boolean result = createGroupTransactionDTO.equals(denomination);

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test for method hashcode - true")
    void testHashCodeTRUE() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers", "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        CreateGroupTransactionDTO createGroupTransactionDTOCopy = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers", "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        // Act
        boolean result = createGroupTransactionDTO.hashCode() == createGroupTransactionDTOCopy.hashCode();

        // Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test for method hashcode - false")
    void testHashCodeFALSE() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers", "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        CreateGroupTransactionDTO createGroupTransactionDTOCopy = new CreateGroupTransactionDTO("Bashtards",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers", "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        // Act
        boolean result = createGroupTransactionDTO.hashCode() == createGroupTransactionDTOCopy.hashCode();

        // Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test for method getDescription")
    void getGroupDescription() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO1 = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers",  "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        CreateGroupTransactionDTO createGroupTransactionDTO2 = new CreateGroupTransactionDTO("Bashtards",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers",  "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        String groupDescription1Expected = "Switch";
        String groupDescription2Expected = "Bashtards";

        // Act
        String groupDescription1 = createGroupTransactionDTO1.getGroupDescription();
        String groupDescription2 = createGroupTransactionDTO2.getGroupDescription();

        // Assert
        assertEquals(groupDescription1Expected, groupDescription1);
        assertEquals(groupDescription2Expected, groupDescription2);
        assertNotEquals(groupDescription1, groupDescription2);
        assertNotNull(groupDescription1);
        assertNotNull(groupDescription2);
    }

    @Test
    @DisplayName("Test for method getPersonEmail")
    void getPersonEmail() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO1 = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers",
                "drinks", "Switch Account", "Isep Bar Account", "debit");

        String personEmailExpected = "sofia@sapo.pt";

        // Act
        String personEmail = createGroupTransactionDTO1.getPersonEmail();

        // Assert
        assertEquals(personEmailExpected, personEmail);
    }

    @Test
    @DisplayName("Test for method getAmount")
    void getAmount() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers", "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        double amountExpected = 20.50;

        // Act
        double amount = createGroupTransactionDTO.getAmount();

        // Assert
        assertEquals(amountExpected, amount);
    }

    @Test
    @DisplayName("Test for method getCurrency")
    void getCurrency() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers", "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        String currencyExpected = "EUR";

        // Act
        String currency = createGroupTransactionDTO.getCurrency();

        // Assert
        assertEquals(currencyExpected, currency);
    }

    @Test
    @DisplayName("Test for method getDate")
    void getDate() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers", "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        String dateExpected = "19-05-2020";

        // Act
        String date = createGroupTransactionDTO.getDate();

        // Assert
        assertEquals(dateExpected, date);
    }

    @Test
    @DisplayName("Test for method getDescription")
    void getDescription() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers",  "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        String descriptionExpected = "beers";

        // Act
        String description = createGroupTransactionDTO.getDescription();

        // Assert
        assertEquals(descriptionExpected, description);
    }

    @Test
    @DisplayName("Test for method getCategory")
    void getCategory() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers",  "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        String categoryExpected = "drinks";

        // Act
        String category = createGroupTransactionDTO.getCategory();

        // Assert
        assertEquals(categoryExpected, category);
    }

    @Test
    @DisplayName("Test for method getAccountFrom")
    void getAccountFrom() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers", "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        String accountFromExpected = "Switch Account";

        // Act
        String accountFrom = createGroupTransactionDTO.getAccountFrom();

        // Assert
        assertEquals(accountFromExpected, accountFrom);
    }

    @Test
    @DisplayName("Test for method getAccountTo")
    void getAccountTo() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers", "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        String accountToExpected = "Isep Bar Account";

        // Act
        String accountTo = createGroupTransactionDTO.getAccountTo();

        // Assert
        assertEquals(accountToExpected, accountTo);
    }

    @Test
    @DisplayName("Test for method getType - debit")
    void getType() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers", "drinks",
                "Switch Account", "Isep Bar Account", "debit");

        boolean typeExpected = false;

        // Act
        boolean type = createGroupTransactionDTO.getType();

        // Assert
        assertEquals(typeExpected, type);
    }

    @Test
    @DisplayName("Test for method getType - credit")
    void getTypeFalse() {

        // Arrange
        CreateGroupTransactionDTO createGroupTransactionDTO = new CreateGroupTransactionDTO("Switch",
                "sofia@sapo.pt", 20.50, "EUR", "19-05-2020","beers", "drinks",
                "Switch Account", "Isep Bar Account", "credit");

        boolean typeExpected = true;

        // Act
        boolean type = createGroupTransactionDTO.getType();

        // Assert
        assertEquals(typeExpected, type);
    }
}