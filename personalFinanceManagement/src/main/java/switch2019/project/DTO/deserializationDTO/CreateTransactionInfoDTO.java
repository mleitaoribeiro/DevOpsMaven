package switch2019.project.DTO.deserializationDTO;

import java.util.Objects;

public class CreateTransactionInfoDTO {

    private Double amount;
    private String currency;
    private String description;
    private String date;
    private String category;
    private String accountFrom;
    private String accountTo;
    private String type;
    private String personEmail;

    public CreateTransactionInfoDTO() {
        super();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateTransactionInfoDTO that = (CreateTransactionInfoDTO) o;
        return Objects.equals(amount, that.amount) &&
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
        return Objects.hash(amount, currency, description, date, category, accountFrom, accountTo, type);
    }

    /**
     * Getter for amount
     *
     * @return Double
     */

    public Double getAmount() {
        return amount;
    }

    /**
     * Setter for amount
     *
     * @param amount for amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Getter for currency
     *
     * @return String
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Setter for currency
     *
     * @param currency for currency
     */

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Getter for description
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     *
     * @param description for description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for date
     *
     * @return String
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter for date
     *
     * @param date for date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter for category
     *
     * @return String
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter for category
     *
     * @param category for category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Getter for accountFrom
     *
     * @return String
     */
    public String getAccountFrom() {
        return accountFrom;
    }

    /**
     * Setter for accountFrom
     *
     * @param accountFrom for accountFrom
     */
    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    /**
     * Getter for accountTo
     *
     * @return String
     */
    public String getAccountTo() {
        return accountTo;
    }

    /**
     * Setter for accountTo
     *
     * @param accountTo for accountTo
     */
    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    /**
     * Getter for type
     *
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type
     *
     * @param type for type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Getter for personEmail
     *
     * @return String
     */
    public String getPersonEmail() {
        return personEmail;
    }

    /**
     * Setter for personEmail
     *
     * @param personEmail for personEmail
     */
    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }
}
