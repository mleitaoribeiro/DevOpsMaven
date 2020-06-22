package switch2019.project.DTO.serializationDTO;

import org.springframework.hateoas.RepresentationModel;

import java.util.Currency;
import java.util.Objects;

public class TransactionShortDTO extends RepresentationModel<TransactionShortDTO> {

    private final Double amount;
    private final Currency currency;
    private final String accountFrom;
    private final String accountTo;
    private final String type;
    private final Long id;

    /**
     * Constructor for TransactionShortDTO
     * @param amount for amount
     * @param currency for currency
     * @param id for id
     * @param accountFrom for accountFrom
     * @param accountTo for accountTo
     * @param type for type
     */
    public TransactionShortDTO (Double amount, Currency currency, String accountFrom, String accountTo, String type, Long id){
        this.amount = amount;
        this.currency = currency;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.type = type;
        this.id = id;
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

    public Long getId() {
        return id;
    }

    /**
     * Getter for amount
     * @return Double
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Getter for Currency
     * @return Currency
     */

    public Currency getCurrency() {
        return currency;
    }

    /**
     * Getter for accountFrom
     * @return String
     */

    public String getAccountFrom() {
        return accountFrom;
    }

    /**
     * Getter for accountTo
     * @return String
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
