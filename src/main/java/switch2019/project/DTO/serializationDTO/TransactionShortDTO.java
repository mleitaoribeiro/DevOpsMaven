package switch2019.project.DTO.serializationDTO;

import java.util.Currency;
import java.util.Objects;

public class TransactionShortDTO {

    private final Double amount;
    private final Currency currency;
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
    public TransactionShortDTO (Double amount, Currency currency, String accountFrom, String accountTo, String type){
        this.amount = amount;
        this.currency = currency;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionShortDTO)) return false;
        TransactionShortDTO that = (TransactionShortDTO) o;
        return Objects.equals(amount, that.amount) &&
                Objects.equals(currency, that.currency) &&
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
    public Double getAmount() {
        return amount;
    }

    /**
     * Getter for Currency
     * @return currency
     */

    public Currency getCurrency() {
        return currency;
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
