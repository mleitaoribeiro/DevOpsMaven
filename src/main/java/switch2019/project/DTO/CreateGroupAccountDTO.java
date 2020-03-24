package switch2019.project.DTO;

public class CreateGroupAccountDTO {
    private String personEmail;
    private String groupDescription;
    private String accountDenomination;
    private String accountDescription;

    public CreateGroupAccountDTO(String personEmail, String groupDescription,
                                 String accountDenomination, String accountDescription) {
        this.personEmail = personEmail;
        this.groupDescription = groupDescription;
        this.accountDenomination = accountDenomination;
        this.accountDescription = accountDescription;
    }

    /**
     * get Person Email
     * @return personID
     */
    public String getPersonEmail() {
        return personEmail;
    }

    /**
     * get Group Description
     * @return personID
     */
    public String getGroupDescription() { return groupDescription; }

    /**
     * get Account Denomination
     * @return accountDenomination
     */
    public String getAccountDenomination() {
        return accountDenomination;
    }

    /**
     * get Accoun tDescription
     * @return accountDescription
     */
    public String getAccountDescription() {
        return accountDescription;
    }
}