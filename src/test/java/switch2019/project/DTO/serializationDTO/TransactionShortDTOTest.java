package switch2019.project.DTO.serializationDTO;

import org.junit.jupiter.api.Test;
import switch2019.project.domain.domainEntities.person.Person;
import switch2019.project.domain.repositories.PersonRepository;

import static org.junit.jupiter.api.Assertions.*;

class TransactionShortDTOTest {

    /**
     * Tests for Equals
     */

    @Test
    void testEquals() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");

        boolean result = transactionShortDTO.equals(transactionShortDTO);

        assertTrue(result);
    }

    @Test
    void testEquals2 () {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");

        boolean result = transactionShortDTO.equals(transactionShortDTO2);

        assertTrue(result);
    }

    @Test
    void testEqualsDifferentAmount () {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO("10", "Mine",
                "Yours", "credit");

        boolean result = transactionShortDTO.equals(transactionShortDTO2);

        assertFalse(result);
    }

    @Test
    void testEqualsDifferentAccountFrom () {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO("100", "Market",
                "Yours", "credit");

        boolean result = transactionShortDTO.equals(transactionShortDTO2);

        assertFalse(result);
    }

    @Test
    void testEqualsDifferentAccountTo () {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO("100", "Mine",
                "Grocery", "credit");

            boolean result = transactionShortDTO.equals(transactionShortDTO2);

            assertFalse(result);
    }
    @Test
    void testEqualsDifferentType () {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO("100", "Mine",
                "Yours", "debit");

        boolean result = transactionShortDTO.equals(transactionShortDTO2);

        assertFalse(result);
    }

    @Test
    void testEqualsDifferentObject () {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        AccountDTO accountDTO = new AccountDTO("maria@email.com","Grocery","Month");

        boolean result = transactionShortDTO.equals(accountDTO);

        assertFalse(result);
    }

    @Test
    void testEqualsNull () {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        TransactionShortDTO transactionShortDTO2 = null;

        boolean result = transactionShortDTO.equals(transactionShortDTO2);

        assertFalse(result);
    }

    /**
     * Tests for hashCode
     */

    @Test
    void testHashCode() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");

        assertEquals(transactionShortDTO.hashCode(),transactionShortDTO2.hashCode());
    }

    @Test
    void testHashCodeDifferent() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        TransactionShortDTO transactionShortDTO2 = new TransactionShortDTO("50", "Mine",
                "Yours", "credit");

        assertNotEquals(transactionShortDTO.hashCode(),transactionShortDTO2.hashCode());
    }

    /**
     * Tests for gets
     */
    @Test
    void getAmount() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        String result = "100";

        assertEquals(result,transactionShortDTO.getAmount());
    }

    @Test
    void getAmountFalse() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        String result = "10";

        assertNotEquals(result,transactionShortDTO.getAmount());
    }

    @Test
    void getAccountFrom() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        String result = "Mine";
        assertEquals(result,transactionShortDTO.getAccountFrom());
    }

    @Test
    void getAccountFromFalse() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        String result = "grocery";
        assertNotEquals(result,transactionShortDTO.getAccountFrom());
    }

    @Test
    void getAccountTo() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        String result = "Yours";
        assertEquals(result,transactionShortDTO.getAccountTo());
    }

    @Test
    void getAccountToFalse() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        String result = "life";
        assertNotEquals(result,transactionShortDTO.getAccountTo());
    }

    @Test
    void getType() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        String result = "credit";
        assertEquals(result,transactionShortDTO.getType());
    }

    @Test
    void getTypeFalse() {
        TransactionShortDTO transactionShortDTO = new TransactionShortDTO("100", "Mine",
                "Yours", "credit");
        String result = "bubbles";
        assertNotEquals(result,transactionShortDTO.getType());
    }
}