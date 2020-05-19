package switch2019.project.DTO.serviceDTO;

import java.util.Objects;

public class CreatePersonalTransactionDTO {

    /**
     * This DTO contains all the information necessary to create an Transaction and associate it with a Person.
     * <p>
     * personEmail - Person email necessary to find the Person ID and therefore associate it with the created account.
     * amount-this String will become the amountValue of the MonetaryValue
     * description
     * date
     * category
     * accountFrom
     * accountTo
     * type
     **/


    private final String personEmail;
    private final double amount;
    private final String currency;
    private final String description;
    private final String category;
    private final String accountFrom;
    private final String accountTo;
    private final String type;

    /**
     * Constructor for PersonalTranscationDTO
     *
     * @param personEmail
     * @param amount
     * @param currency
     * @param description
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     */

    public CreatePersonalTransactionDTO(String personEmail, double amount, String currency, String description, String category, String accountFrom, String accountTo, String type) {
        this.personEmail = personEmail;
        this.amount = amount;
        this.currency = currency;
        this.description = description;
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
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(personEmail, that.personEmail) &&
                Objects.equals(currency, that.currency) &&
                Objects.equals(description, that.description) &&
                Objects.equals(category, that.category) &&
                Objects.equals(accountFrom, that.accountFrom) &&
                Objects.equals(accountTo, that.accountTo) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEmail, amount, currency, description, category, accountFrom, accountTo, type);
    }

    /**
     * get Person Email
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
