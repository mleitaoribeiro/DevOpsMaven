package switch2019.project.DTO.deserializationDTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateTransactionInfoDTOTest {

    @Test
    @DisplayName("Test - getAmount & setAmount")
    void getAmount() {
        //Assert
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAmount(5.00);

        Double expectedAmount = 5.00;

        //Act:
        Double realAmount = dto.getAmount();

        //Assert:
        assertEquals(expectedAmount, realAmount);

    }

    @Test
    @DisplayName("Test - getCurrency & setCurrency")
    void getCurrency() {
        //Assert
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setCurrency("EUR");

        String expectedCurrency = "EUR";

        //Act:
        String realCurrency = dto.getCurrency();

        //Assert:
        assertEquals(expectedCurrency, realCurrency);

    }


    @Test
    @DisplayName("Test - getDescription & setDescription")
    void getDescription() {
        //Assert
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setDescription("General Expenses");

        String expectedDescription = "General Expenses";

        //Act:
        String realDescription = dto.getDescription();

        //Assert:
        assertEquals(expectedDescription, realDescription);

    }


    @Test
    @DisplayName("Test - getDate & setDate")
    void getDate() {
        //Assert
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setDate("18-05-2020");

        String expectedDate = "18-05-2020";

        //Act:
        String realDate = dto.getDate();

        //Assert:
        assertEquals(expectedDate, realDate);

    }

    @Test
    @DisplayName("Test - getType & setType")
    void getType() {
        //Assert
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setType("debit");

        String expectedType = "debit";

        //Act:
        String realType = dto.getType();

        //Assert:
        assertEquals(expectedType, realType);

    }


    @Test
    @DisplayName("Test - getCategory & setCategory")
    void getCategory() {
        //Assert
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setCategory("Gym");

        String expectedCategory = "Gym";

        //Act:
        String realDate = dto.getCategory();

        //Assert:
        assertEquals(expectedCategory, realDate);

    }


    @Test
    @DisplayName("Test - getAccountFrom & setAccountFrom")
    void getAccountFrom() {
        //Assert
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAccountFrom("Raquel Account");

        String expectedAccountFrom = "Raquel Account";

        //Act:
        String realAccountFrom = dto.getAccountFrom();

        //Assert:
        assertEquals(expectedAccountFrom, realAccountFrom);

    }


    /**
     * Tests for the methods: getAccountTo & setAccountTo:
     */

    @Test
    @DisplayName("Test - Get AccountFrom")
    void getAccountTo() {
        //Assert
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAccountTo("Sofia Account");

        String expectedAccountTo = "Sofia Account";

        //Act:
        String realAccountTo = dto.getAccountTo();

        //Assert:
        assertEquals(expectedAccountTo, realAccountTo);

    }

    /**
     * Tests for Equals Method:
     */

    @Test
    @DisplayName("Test to equals - Same Object")
    void equalsSameObject() {
        //Arrange
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAmount(5.00);
        dto.setCurrency("EUR");
        dto.setCategory("Shopping");
        dto.setDescription("Shopping Expenses");
        dto.setAccountTo("Querido Account");
        dto.setAccountFrom("Raquel Account");
        dto.setDate("18-05-2020");
        dto.setType("debit");

        //Act
        boolean result = dto.equals(dto);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to equals - Null Object")
    void equalsNullObject() {
        //Arrange
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAmount(5.00);
        dto.setCurrency("EUR");
        dto.setCategory("Shopping");
        dto.setDescription("Shopping Expenses");
        dto.setAccountTo("Querido Account");
        dto.setAccountFrom("Raquel Account");
        dto.setDate("18-05-2020");
        dto.setType("debit");

        CreateTransactionInfoDTO dto2 = null;

        //Act
        boolean result = dto.equals(dto2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to equals - Object from different class")
    void equalsDifferentObjectClass() {
        //Arrange
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAmount(5.00);
        dto.setCurrency("EUR");
        dto.setCategory("Shopping");
        dto.setDescription("Shopping Expenses");
        dto.setAccountTo("Querido Account");
        dto.setAccountFrom("Raquel Account");
        dto.setDate("18-05-2020");
        dto.setType("debit");

        CreateGroupInfoDTO dtoFromDifClass = new CreateGroupInfoDTO();

        //Act
        boolean result = dto.equals(dtoFromDifClass);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to equals - Same attributes")
    void equalsSameAttributes() {
        //Arrange
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAmount(5.00);
        dto.setCurrency("EUR");
        dto.setCategory("Shopping");
        dto.setDescription("Shopping Expenses");
        dto.setAccountTo("Querido Account");
        dto.setAccountFrom("Raquel Account");
        dto.setDate("18-05-2020");
        dto.setType("debit");

        CreateTransactionInfoDTO dto2 = new CreateTransactionInfoDTO();
        dto2.setAmount(5.00);
        dto2.setCurrency("EUR");
        dto2.setCategory("Shopping");
        dto2.setDescription("Shopping Expenses");
        dto2.setAccountTo("Querido Account");
        dto2.setAccountFrom("Raquel Account");
        dto2.setDate("18-05-2020");
        dto2.setType("debit");

        //Act
        boolean result = dto.equals(dto2);

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to equals - Different Description")
    void equalsDifferentDescription() {
        //Arrange
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAmount(5.00);
        dto.setCurrency("EUR");
        dto.setCategory("Shopping");
        dto.setDescription("Shopping Expenses");
        dto.setAccountTo("Querido Account");
        dto.setAccountFrom("Raquel Account");
        dto.setDate("18-05-2020");
        dto.setType("debit");

        CreateTransactionInfoDTO dto2 = new CreateTransactionInfoDTO();
        dto2.setAmount(5.00);
        dto2.setCurrency("EUR");
        dto2.setCategory("Shopping");
        dto2.setDescription("General Expenses");
        dto2.setAccountTo("Querido Account");
        dto2.setAccountFrom("Raquel Account");
        dto2.setDate("18-05-2020");
        dto2.setType("debit");

        //Act
        boolean result = dto.equals(dto2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to equals - Different Amount")
    void equalsDifferentAmount() {
        //Arrange
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAmount(5.00);
        dto.setCurrency("EUR");
        dto.setCategory("Shopping");
        dto.setDescription("General Expenses");
        dto.setAccountTo("Querido Account");
        dto.setAccountFrom("Raquel Account");
        dto.setDate("18-05-2020");
        dto.setType("debit");

        CreateTransactionInfoDTO dto2 = new CreateTransactionInfoDTO();
        dto2.setAmount(6.50);
        dto2.setCurrency("EUR");
        dto2.setCategory("Shopping");
        dto2.setDescription("General Expenses");
        dto2.setAccountTo("Querido Account");
        dto2.setAccountFrom("Raquel Account");
        dto2.setDate("18-05-2020");
        dto2.setType("debit");

        //Act
        boolean result = dto.equals(dto2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to equals - Different Currency")
    void equalsDifferentCurrency() {
        //Arrange
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAmount(5.00);
        dto.setCurrency("EUR");
        dto.setCategory("Shopping");
        dto.setDescription("General Expenses");
        dto.setAccountTo("Querido Account");
        dto.setAccountFrom("Raquel Account");
        dto.setDate("18-05-2020");
        dto.setType("debit");

        CreateTransactionInfoDTO dto2 = new CreateTransactionInfoDTO();
        dto2.setAmount(5.00);
        dto2.setCurrency("LIBRA");
        dto2.setCategory("Shopping");
        dto2.setDescription("General Expenses");
        dto2.setAccountTo("Querido Account");
        dto2.setAccountFrom("Raquel Account");
        dto2.setDate("18-05-2020");
        dto2.setType("debit");

        //Act
        boolean result = dto.equals(dto2);

        //Assert
        assertFalse(result);
    }


    @Test
    @DisplayName("Test to equals - Different Category")
    void equalsDifferentCategory() {
        //Arrange
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAmount(5.00);
        dto.setCurrency("EUR");
        dto.setCategory("General");
        dto.setDescription("General Expenses");
        dto.setAccountTo("Querido Account");
        dto.setAccountFrom("Raquel Account");
        dto.setDate("18-05-2020");
        dto.setType("debit");

        CreateTransactionInfoDTO dto2 = new CreateTransactionInfoDTO();
        dto2.setAmount(5.00);
        dto2.setCurrency("EUR");
        dto2.setCategory("Shopping");
        dto2.setDescription("General Expenses");
        dto2.setAccountTo("Querido Account");
        dto2.setAccountFrom("Raquel Account");
        dto2.setDate("18-05-2020");
        dto2.setType("debit");

        //Act
        boolean result = dto.equals(dto2);

        //Assert
        assertFalse(result);
    }


    @Test
    @DisplayName("Test to equals - Different AccountTo")
    void equalsDifferentAccountTo() {
        //Arrange
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAmount(5.00);
        dto.setCurrency("EUR");
        dto.setCategory("Shopping");
        dto.setDescription("General Expenses");
        dto.setAccountTo("Querido Account");
        dto.setAccountFrom("Raquel Account");
        dto.setDate("18-05-2020");
        dto.setType("debit");

        CreateTransactionInfoDTO dto2 = new CreateTransactionInfoDTO();
        dto2.setAmount(5.00);
        dto2.setCurrency("EUR");
        dto2.setCategory("Shopping");
        dto2.setDescription("General Expenses");
        dto2.setAccountTo("Rodrigues Account");
        dto2.setAccountFrom("Raquel Account");
        dto2.setDate("18-05-2020");
        dto2.setType("debit");

        //Act
        boolean result = dto.equals(dto2);

        //Assert
        assertFalse(result);
    }


    @Test
    @DisplayName("Test to equals - Different AccountFrom")
    void equalsDifferentAccountFrom() {
        //Arrange
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAmount(5.00);
        dto.setCurrency("EUR");
        dto.setCategory("Shopping");
        dto.setDescription("General Expenses");
        dto.setAccountTo("Rodrigues Account");
        dto.setAccountFrom("Sofia Account");
        dto.setDate("18-05-2020");
        dto.setType("debit");

        CreateTransactionInfoDTO dto2 = new CreateTransactionInfoDTO();
        dto2.setAmount(5.00);
        dto2.setCurrency("EUR");
        dto2.setCategory("Shopping");
        dto2.setDescription("General Expenses");
        dto2.setAccountTo("Rodrigues Account");
        dto2.setAccountFrom("Raquel Account");
        dto2.setDate("18-05-2020");
        dto2.setType("debit");

        //Act
        boolean result = dto.equals(dto2);

        //Assert
        assertFalse(result);
    }


    @Test
    @DisplayName("Test to equals - Different Date")
    void equalsDifferentDate() {
        //Arrange
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAmount(5.00);
        dto.setCurrency("EUR");
        dto.setCategory("Shopping");
        dto.setDescription("General Expenses");
        dto.setAccountTo("Querido Account");
        dto.setAccountFrom("Raquel Account");
        dto.setDate("15-05-2020");
        dto.setType("debit");

        CreateTransactionInfoDTO dto2 = new CreateTransactionInfoDTO();
        dto2.setAmount(5.00);
        dto2.setCurrency("EUR");
        dto2.setCategory("Shopping");
        dto2.setDescription("General Expenses");
        dto2.setAccountTo("Querido Account");
        dto2.setAccountFrom("Raquel Account");
        dto2.setDate("18-05-2020");
        dto2.setType("debit");

        //Act
        boolean result = dto.equals(dto2);

        //Assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Test to equals - Different Type")
    void equalsDifferentType() {
        //Arrange
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAmount(5.00);
        dto.setCurrency("EUR");
        dto.setCategory("Shopping");
        dto.setDescription("General Expenses");
        dto.setAccountTo("Querido Account");
        dto.setAccountFrom("Raquel Account");
        dto.setDate("15-05-2020");
        dto.setType("debit");

        CreateTransactionInfoDTO dto2 = new CreateTransactionInfoDTO();
        dto2.setAmount(5.00);
        dto2.setCurrency("EUR");
        dto2.setCategory("Shopping");
        dto2.setDescription("General Expenses");
        dto2.setAccountTo("Querido Account");
        dto2.setAccountFrom("Raquel Account");
        dto2.setDate("15-05-2020");
        dto2.setType("credit");

        //Act
        boolean result = dto.equals(dto2);

        //Assert
        assertFalse(result);
    }



    /**
     * Tests for HashCode Method:
     */

    @Test
    @DisplayName("Test to HashCode - Same Hashcode")
    void sameHashcode() {
        //Arrange
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAmount(5.00);
        dto.setCurrency("EUR");
        dto.setCategory("Shopping");
        dto.setDescription("Gym Expenses");
        dto.setAccountTo("Querido Account");
        dto.setAccountFrom("Raquel Account");
        dto.setDate("15-05-2020");
        dto.setType("debit");

        CreateTransactionInfoDTO dto2 = new CreateTransactionInfoDTO();
        dto2.setAmount(5.00);
        dto2.setCurrency("EUR");
        dto2.setCategory("Shopping");
        dto2.setDescription("Gym Expenses");
        dto2.setAccountTo("Querido Account");
        dto2.setAccountFrom("Raquel Account");
        dto2.setDate("15-05-2020");
        dto2.setType("debit");

        //Act
        boolean result = dto.hashCode() == dto2.hashCode();

        //Assert
        assertTrue(result);
    }

    @Test
    @DisplayName("Test to HashCode - Different Hashcode")
    void differentHashcode() {
        //Arrange
        CreateTransactionInfoDTO dto = new CreateTransactionInfoDTO();
        dto.setAmount(5.00);
        dto.setCategory("Shopping");
        dto.setDescription("Gym Expenses");
        dto.setAccountTo("Querido Account");
        dto.setAccountFrom("Raquel Account");
        dto.setDate("15-05-2020");
        dto.setType("debit");

        CreateTransactionInfoDTO dto2 = new CreateTransactionInfoDTO();
        dto2.setAmount(13.00);
        dto2.setCategory("Shopping");
        dto2.setDescription("General Expenses");
        dto2.setAccountTo("Querido Account");
        dto2.setAccountFrom("Raquel Account");
        dto2.setDate("15-05-2020");
        dto2.setType("credit");

        //Act
        boolean result = dto.hashCode() == dto2.hashCode();

        //Assert
        assertFalse(result);
    }

}