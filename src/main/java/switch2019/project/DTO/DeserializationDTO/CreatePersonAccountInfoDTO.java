package switch2019.project.DTO.DeserializationDTO;

import java.util.Objects;

public class CreatePersonAccountInfoDTO {
    /**
     * This DTO contains all the information necessary to create an Account and associate it with a Person.
     * <p>
     * personEmail - Person email necessary to find the Person ID and therefore associate it with the created account.
     * accountDenomination - this String will become the denominationValue of the Denomination which is essentially the name of the Account that will be created.
     * accountDescription - this String will become the descriptionValue of the Description which is essentially a description of the Account that will be created.
     **/


    private String personEmail;
    private String accountDenomination;
    private String accountDescription;

    public CreatePersonAccountInfoDTO() {
    }


    public CreatePersonAccountInfoDTO(String personEmail, String accountDenomination, String accountDescription) {
        this.personEmail = personEmail;
        this.accountDenomination = accountDenomination;
        this.accountDescription = accountDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreatePersonAccountInfoDTO that = (CreatePersonAccountInfoDTO) o;
        return Objects.equals(personEmail, that.personEmail) &&
                Objects.equals(accountDenomination, that.accountDenomination) &&
                Objects.equals(accountDescription, that.accountDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEmail, accountDenomination, accountDescription);
    }


    /**
     * Getters used to obtain the attribute Strings, outside of this class:
     */


    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public String getAccountDenomination() {
        return accountDenomination;
    }

    public void setAccountDenomination(String accountDenomination) {
        this.accountDenomination = accountDenomination;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }
}

