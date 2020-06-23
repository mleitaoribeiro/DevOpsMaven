package switch2019.project.DTO.serializationDTO;

import java.util.Currency;
import java.util.Objects;

public class TransactionDTO {

    private final Double amount;
    private final Currency currency;
    private final String description;
    private final String date;
    private final String category;
    private final String accountFrom;
    private final String accountTo;
    private final String type;

    public  TransactionDTO (Double amount, Currency currency, String description, String date, String category,
                            String accountFrom, String accountTo, String type) {
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.date = date;
        this.category = category;
        this.accountFrom = accountFrom;
        this. accountTo = accountTo;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransactionDTO)) return false;
        TransactionDTO that = (TransactionDTO) o;
        return amount.equals(that.amount) &&
                currency.equals(that.currency) &&
                description.equalsIgnoreCase(that.description) &&
                date.equalsIgnoreCase(that.date) &&
                category.equalsIgnoreCase(that.category) &&
                accountFrom.equalsIgnoreCase(that.accountFrom) &&
                accountTo.equalsIgnoreCase(that.accountTo) &&
                type.equalsIgnoreCase(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, description, date, category, accountFrom, accountTo, type);
    }

    public Currency getCurrency() {
        return currency;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getAccountFrom() {
        return accountFrom;
    }

    public String getAccountTo() {
        return accountTo;
    }

    public String getType() {
        return type;
    }
}
