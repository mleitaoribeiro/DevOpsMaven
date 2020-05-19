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
    private final String amount;
    private final String description;
    private final String date;
    private final String category;
    private final String accountFrom;
    private final String accountTo;
    private final String type;

    /**
     * Constructor for PersonalTranscationDTO
     *
     * @param personEmail
     * @param amount
     * @param description
     * @param date
     * @param category
     * @param accountFrom
     * @param accountTo
     * @param type
     */

    public CreatePersonalTransactionDTO(String personEmail, String amount, String description, String date, String category, String accountFrom, String accountTo, String type) {
        this.personEmail = personEmail;
        this.amount = amount;
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
                Objects.equals(description, that.description) &&
                Objects.equals(date, that.date) &&
                Objects.equals(category, that.category) &&
                Objects.equals(accountFrom, that.accountFrom) &&
                Objects.equals(accountTo, that.accountTo) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEmail, amount, description, date, category, accountFrom, accountTo, type);
    }

    public String getPersonEmail() {
        return personEmail;

    }

    public String getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getDate() {
        return date;
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
