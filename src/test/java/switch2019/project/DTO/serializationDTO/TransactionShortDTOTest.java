package switch2019.project.DTO.serializationDTO;

import org.junit.jupiter.api.Test;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

class TransactionShortDTOTest {

    /**
     * Tests for Equals
     */

    @Test
    void testEquals() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"),
                "Mine", "Yours", "DEBIT");

        boolean result = transactionShortDTO.equals(transactionShortDTO);

        assertTrue(result);
    }

    @Test
    void testEquals2 () {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0, Currency.getInstance("EUR") ,"Mine",
                "Yours", "DEBIT");
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"),"Mine",
                "Yours", "DEBIT");

        boolean result = transactionShortDTO.equals(transactionShortDTO2);

        assertTrue(result);
    }

    @Test
    void testEqualsDifferentAmount () {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0, Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT");
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "CREDIT");

        boolean result = transactionShortDTO.equals(transactionShortDTO2);

        assertFalse(result);
    }

    @Test
    void testEqualsDifferentAccountFrom () {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0, Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT");
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"),"Market",
                "Yours", "DEBIT");

        boolean result = transactionShortDTO.equals(transactionShortDTO2);

        assertFalse(result);
    }

    @Test
    void testEqualsDifferentAccountTo () {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT");
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Grocery", "DEBIT");

            boolean result = transactionShortDTO.equals(transactionShortDTO2);

            assertFalse(result);
    }
    @Test
    void testEqualsDifferentType () {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"),  "Mine",
                "Yours", "DEBIT");
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO(100.0, Currency.getInstance("EUR"), "Mine",
                "Yours", "CREDIT");

        boolean result = transactionShortDTO.equals(transactionShortDTO2);

        assertFalse(result);
    }

    @Test
    void testEqualsDifferentObject () {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0, Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT");
        AccountDTO accountDTO = new AccountDTO("maria@email.com","Grocery","Month");

        boolean result = transactionShortDTO.equals(accountDTO);

        assertFalse(result);
    }

    @Test
    void testEqualsNull () {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT");
        TransactionShortDTO transactionShortDTO2 = null;

        boolean result = transactionShortDTO.equals(transactionShortDTO2);

        assertFalse(result);
    }

    /**
     * Tests for hashCode
     */

    @Test
    void testHashCode() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT");
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT");

        assertEquals(transactionShortDTO.hashCode(),transactionShortDTO2.hashCode());
    }

    @Test
    void testHashCodeDifferent() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0, Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT");
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO(50.0, Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT");

        assertNotEquals(transactionShortDTO.hashCode(),transactionShortDTO2.hashCode());
    }

    /**
     * Tests for gets
     */
    @Test
    void getAmount() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT");
        double result = 100;

        assertEquals(result,transactionShortDTO.getAmount());
    }

    @Test
    void getAmountFalse() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT");
        String result = "10";

        assertNotEquals(result,transactionShortDTO.getAmount());
    }

    @Test
    void getAccountFrom() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"),"Mine",
                "Yours", "DEBIT");
        String result = "Mine";
        assertEquals(result,transactionShortDTO.getAccountFrom());
    }

    @Test
    void getAccountFromFalse() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"),"Mine",
                "Yours", "DEBIT");
        String result = "grocery";
        assertNotEquals(result,transactionShortDTO.getAccountFrom());
    }

    @Test
    void getAccountTo() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT");
        String result = "Yours";
        assertEquals(result,transactionShortDTO.getAccountTo());
    }

    @Test
    void getAccountToFalse() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"),"Mine",
                "Yours", "DEBIT");
        String result = "life";
        assertNotEquals(result,transactionShortDTO.getAccountTo());
    }

    @Test
    void getType() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT");
        String result = "DEBIT";

        assertEquals(result,transactionShortDTO.getType());
    }

    @Test
    void getTypeFalse() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT");
        String result = "bubbles";
        assertNotEquals(result,transactionShortDTO.getType());
    }
}