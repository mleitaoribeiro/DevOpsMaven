package switch2019.project.DTO;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateGroupAccountDTO)) return false;
        CreateGroupAccountDTO that = (CreateGroupAccountDTO) o;
        return Objects.equals(personEmail, that.personEmail) &&
                Objects.equals(groupDescription, that.groupDescription) &&
                Objects.equals(accountDenomination, that.accountDenomination) &&
                Objects.equals(accountDescription, that.accountDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personEmail, groupDescription, accountDenomination, accountDescription);
    }
}