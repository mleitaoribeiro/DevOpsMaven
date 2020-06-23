package switch2019.project.DTO.serviceDTO;

import java.util.Objects;

public class CreateGroupAccountDTO {
    private final String personEmail;
    private final String groupDescription;
    private final String accountDenomination;
    private final String accountDescription;

    public CreateGroupAccountDTO(String personEmail, String groupDescription, String accountDenomination, String accountDescription) {
        this.personEmail = personEmail;
        this.groupDescription = groupDescription;
        this.accountDenomination = accountDenomination;
        this.accountDescription = accountDescription;
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

    public String getPersonEmail() {
        return personEmail;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public String getAccountDenomination() {
        return accountDenomination;
    }

    public String getAccountDescription() {
        return accountDescription;
    }

}