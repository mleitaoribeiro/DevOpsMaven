package switch2019.project.DTO.serializationDTO;

import java.util.Objects;

public class TransactionShortDTO {

    private final String amount;
    private final String accountFrom;
    private final String accountTo;
    private final String type;

    /**
     * Constructor for TransactionShortDTO
     * @param amount
     * @param accountFrom
     * @param accountTo
     * @param type
     */
    public TransactionShortDTO (String amount, String accountFrom, String accountTo, String type){
        this.amount = amount;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionShortDTO that = (TransactionShortDTO) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(accountFrom, that.accountFrom) &&
                Objects.equals(accountTo, that.accountTo) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, accountFrom, accountTo, type);
    }

    /**
     * Getter for amount
     * @return amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * Getter for accountFrom
     * @return accountFrom
     */

    public String getAccountFrom() {
        return accountFrom;
    }

    /**
     * Getter for accountTo
     * @return accountTo
     */

    public String getAccountTo() {
        return accountTo;
    }

    /**
     * Getter to type
     * @return type
     */

    public String getType() {
        return type;
    }
}