
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
        long id = 1;

        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"),
                "Mine", "Yours", "DEBIT", id);

        boolean result = transactionShortDTO.equals(transactionShortDTO);

        assertTrue(result);
    }

    @Test
    void testEquals2 () {
        long id = 1;
        long id2 = 1;
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0, Currency.getInstance("EUR") ,"Mine",
                "Yours", "DEBIT", id);
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"),"Mine",
                "Yours", "DEBIT", id2);

        boolean result = transactionShortDTO.equals(transactionShortDTO2);

        assertTrue(result);
    }

    @Test
    void testEqualsDifferentAmount () {
        long id = 1;
        long id2 = 2;
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0, Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT", id);
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "CREDIT", id2);

        boolean result = transactionShortDTO.equals(transactionShortDTO2);

        assertFalse(result);
    }

    @Test
    void testEqualsDifferentAccountFrom () {
        long id = 1;
        long id2 = 2;
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0, Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT", id);
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"),"Market",
                "Yours", "DEBIT", id2);

        boolean result = transactionShortDTO.equals(transactionShortDTO2);

        assertFalse(result);
    }

    @Test
    void testEqualsDifferentAccountTo () {
        long id = 1;
        long id2 = 2;
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT", id);
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Grocery", "DEBIT", id2);

            boolean result = transactionShortDTO.equals(transactionShortDTO2);

            assertFalse(result);
    }
    @Test
    void testEqualsDifferentType () {
        long id = 1;
        long id2 = 2;
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"),  "Mine",
                "Yours", "DEBIT", id);
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO(100.0, Currency.getInstance("EUR"), "Mine",
                "Yours", "CREDIT", id2);

        boolean result = transactionShortDTO.equals(transactionShortDTO2);

        assertFalse(result);
    }

    @Test
    void testEqualsDifferentObject () {
        long id = 1;
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0, Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT", id);
        AccountDTO accountDTO = new AccountDTO("maria@email.com","Grocery","Month");

        boolean result = transactionShortDTO.equals(accountDTO);

        assertFalse(result);
    }

    @Test
    void testEqualsNull () {
        long id = 1;
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT", id);
        TransactionShortDTO transactionShortDTO2 = null;

        boolean result = transactionShortDTO.equals(transactionShortDTO2);

        assertFalse(result);
    }

     /**
     * Tests for hashCode
     */


    @Test
    void testHashCode() {
        long id = 1;
        long id2 =1;
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT", id);
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT", id2);

        assertEquals(transactionShortDTO.hashCode(),transactionShortDTO2.hashCode());
    }

    @Test
    void testHashCodeDifferent() {
        long id = 1;
        long id2 =1;
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0, Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT", id);
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO(50.0, Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT", id2);

        assertNotEquals(transactionShortDTO.hashCode(),transactionShortDTO2.hashCode());
    }

    /**
     * Tests for gets
     */

    @Test
    void getAmount() {
        long id = 1;
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT", id);
        double result = 100;

        assertEquals(result,transactionShortDTO.getAmount());
    }

    @Test
    void getAmountFalse() {
        long id = 1;
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT", id);
        String result = "10";

        assertNotEquals(result,transactionShortDTO.getAmount());
    }

    @Test
    void getAccountFrom() {
        long id = 1;

        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"),"Mine",
                "Yours", "DEBIT", id);
        String result = "Mine";
        assertEquals(result,transactionShortDTO.getAccountFrom());
    }

    @Test
    void getAccountFromFalse() {
        long id = 1;
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"),"Mine",
                "Yours", "DEBIT", id);
        String result = "grocery";
        assertNotEquals(result,transactionShortDTO.getAccountFrom());
    }

    @Test
    void getAccountTo() {
        long id = 1;

        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT", id);
        String result = "Yours";
        assertEquals(result,transactionShortDTO.getAccountTo());
    }

    @Test
    void getAccountToFalse() {
        long id = 1;

        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"),"Mine",
                "Yours", "DEBIT", id);
        String result = "life";
        assertNotEquals(result,transactionShortDTO.getAccountTo());
    }

    @Test
    void getType() {
        long id = 1;

        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT", id);
        String result = "DEBIT";

        assertEquals(result,transactionShortDTO.getType());
    }

    @Test
    void getTypeFalse() {
        long id = 1;

        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT", id);
        String result = "bubbles";
        assertNotEquals(result,transactionShortDTO.getType());
    }
    @Test
    void getId() {
        long id = 1;

        TransactionShortDTO transactionShortDTO = new TransactionShortDTO(100.0,  Currency.getInstance("EUR"), "Mine",
                "Yours", "DEBIT", id);

        assertEquals(id,transactionShortDTO.getId());
    }
}
