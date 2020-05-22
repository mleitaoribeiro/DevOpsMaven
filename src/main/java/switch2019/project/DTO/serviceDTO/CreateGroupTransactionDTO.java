package switch2019.project.DTO.serviceDTO;

import java.util.Objects;

public class CreateGroupTransactionDTO {

    private final String groupDescription;
    private final String personEmail;
    private final Double amount;
    private final String currency;
    private final String date;
    private final String description;
    private final String category;
    private final String accountFrom;
    private final String accountTo;
    private final String type;

    public CreateGroupTransactionDTO(String groupDescription, String personEmail, Double amount, String currency, String date,
                                     String description, String category, String accountFrom, String accountTo, String type) {

        this.groupDescription = groupDescription;
        this.personEmail = personEmail;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
        this.description = description;
        this.category = category;
        this.accountFrom = accountFrom;
        this. accountTo = accountTo;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateGroupTransactionDTO)) return false;
        CreateGroupTransactionDTO that = (CreateGroupTransactionDTO) o;
        return Objects.equals(groupDescription, that.groupDescription) &&
                Objects.equals(personEmail, that.personEmail) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(date, that.date) &&
                Objects.equals(description, that.description) &&
                Objects.equals(category, that.category) &&
                Objects.equals(accountFrom, that.accountFrom) &&
                Objects.equals(accountTo, that.accountTo) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupDescription, personEmail, amount, currency, date, description, category, accountFrom, accountTo, type);
    }

    /**
     * get Group Description
     *
     * @return groupDescription
     */
    public String getGroupDescription() {
        return groupDescription;
    }

    /**
     * Getter for personEmail
     *
     * @return personEmail
     */
    public String getPersonEmail() {
        return personEmail;
    }

    /**
     * get Transaction Amount
     *
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * get Transaction Currency
     *
     * @return currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * get Transaction Date
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * get Transaction Description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * get Transaction category
     *
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * get Transaction Account From
     *
     * @return accountFrom
     */
    public String getAccountFrom() {
        return accountFrom;
    }

    /**
     * get Transaction Account To
     *
     * @return accountTo
     */
    public String getAccountTo() {
        return accountTo;
    }

    /**
     * get Transaction Type
     *
     * @return type
     */
    public String getType() {
        return type;
    }
}
