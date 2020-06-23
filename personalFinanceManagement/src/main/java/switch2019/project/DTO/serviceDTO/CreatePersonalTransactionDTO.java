package switch2019.project.DTO.serviceDTO;

import java.util.Objects;

public class CreatePersonalTransactionDTO {

    private final String personEmail;
    private final Double amount;
    private final String currency;
    private final String description;
    private final String date;
    private final String category;
    private final String accountFrom;
    private final String accountTo;
    private final String type;

    public CreatePersonalTransactionDTO(String personEmail, Double amount, String currency, String description,
                                        String date, String category, String accountFrom, String accountTo, String type) {
        this.personEmail = personEmail;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
        this.date = date;
        this.category = category;
        this.accountFrom = accountFrom;
        this.accountTo = accountTo;
        this.type = type;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePersonalTransactionDTO that = (CreatePersonalTransactionDTO) o;
        return Objects.equals(personEmail, that.personEmail) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(description, that.description) &&
                Objects.equals(date, that.date) &&
                Objects.equals(category, that.category) &&
                Objects.equals(accountFrom, that.accountFrom) &&
                Objects.equals(accountTo, that.accountTo) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEmail, amount, currency, description, date, category, accountFrom, accountTo, type);
    }

    public String getPersonEmail() {
        return personEmail;

    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
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

    public boolean getType() {
        return this.type.equalsIgnoreCase("credit");
    }
}
