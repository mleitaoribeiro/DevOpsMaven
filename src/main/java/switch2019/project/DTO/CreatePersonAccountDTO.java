package switch2019.project.DTO;

public class CreatePersonAccountDTO {

    /**
     *
     * This DTO contains all the information necessary to create an Account and associate it with a Person.
     *
     * personEmail - Person email necessary to find the Person ID and therefore associate it with the created account.
     * accountDenomination - this String will become the denominationValue of the Denomination which is essentially the name of the Account that will be created.
     * accountDescription - this String will become the descriptionValue of the Description which is essentially a description of the Account that will be created.
     *
     **/


    private String personEmail;
    private String accountDenomination;
    private String accountDescription;


    public CreatePersonAccountDTO (String personEmail, String accountDenomination, String accountDescription) {
        this.personEmail = personEmail;
        this.accountDenomination = accountDenomination;
        this.accountDescription = accountDescription;
    }


    /** Getters used to obtain the attribute Strings, outside of this class: */


    public String getPersonEmail() {
        return personEmail;
    }

    public String getAccountDenomination() {
        return accountDenomination;
    }

    public String getAccountDescription() {
        return accountDescription;
    }




}
