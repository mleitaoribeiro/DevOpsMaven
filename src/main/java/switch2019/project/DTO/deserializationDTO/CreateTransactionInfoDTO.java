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
     * @return amount
     */

    public Double getAmount() {
        return amount;
    }

    /**
     * Setter for amount
     *
     * @param amount
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Getter for currency
     *
     * @return currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Setter for currency
     *
     * @param currency
     */

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     * Getter for description
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Getter for date
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Setter for date
     *
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Getter for category
     *
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Setter for category
     *
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Getter for accountFrom
     *
     * @return accountFrom
     */
    public String getAccountFrom() {
        return accountFrom;
    }

    /**
     * Setter for accountFrom
     *
     * @param accountFrom
     */
    public void setAccountFrom(String accountFrom) {
        this.accountFrom = accountFrom;
    }

    /**
     * Getter for accountTo
     *
     * @return accountTo
     */
    public String getAccountTo() {
        return accountTo;
    }

    /**
     * Setter for accountTo
     *
     * @param accountTo
     */
    public void setAccountTo(String accountTo) {
        this.accountTo = accountTo;
    }

    /**
     * Getter for type
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
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
     * Setter for personEmail
     *
     * @param personEmail
     */
    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }
}
