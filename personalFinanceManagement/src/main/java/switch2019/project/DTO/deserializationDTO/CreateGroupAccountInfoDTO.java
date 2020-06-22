package switch2019.project.DTO.deserializationDTO;

/*
    this DTO contains all the information necessary to create an Account and associate it with a Group.
    description - Group description, necessary to find the GroupID and therefore the Group where the Account will be created.
    personEmail - Person email, necessary to find the PersonID and therefore find if the Person is an admin on the Group where the Account will be created.
    categoryDenomination - this String will become the Denomination which is essentially the name of the Account that will be created.
    accountDescription - this String will become the Description of the Account that will be created.
     */

import java.util.Objects;

public class CreateGroupAccountInfoDTO {

    private String personEmail;
    private String accountDenomination;
    private String accountDescription;

    public CreateGroupAccountInfoDTO() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreateGroupAccountInfoDTO that = (CreateGroupAccountInfoDTO) o;
        return Objects.equals(personEmail, that.personEmail) &&
                Objects.equals(accountDenomination, that.accountDenomination) &&
                Objects.equals(accountDescription, that.accountDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEmail, accountDenomination, accountDescription);
    }

    /**
     * getter for personEmail
     *
     * @return
     */
    public String getPersonEmail() {
        return personEmail;
    }



    /**
     * getter for accountDenomination
     *
     * @return
     */
    public String getAccountDenomination() {
        return accountDenomination;
    }

    /**
     * getter for accountDescription
     *
     * @return
     */
    public String getAccountDescription() {
        return accountDescription;
    }

    /**
     * setter for the personEmail
     *
     * @param personEmail
     */
    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }


    /**
     * setter for accountDenomination
     *
     * @param accountDenomination
     */
    public void setAccountDenomination(String accountDenomination) {
        this.accountDenomination = accountDenomination;
    }

    /**
     * setter accountDescription
     *
     * @param accountDescription
     */
    public void setAccountDescription(String accountDescription) {
        this.accountDescription = accountDescription;
    }

}
